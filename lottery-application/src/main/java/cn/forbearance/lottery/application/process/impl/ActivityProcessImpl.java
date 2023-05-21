package cn.forbearance.lottery.application.process.impl;

import cn.forbearance.lottery.application.process.IActivityProcess;
import cn.forbearance.lottery.application.process.req.DrawProcessReq;
import cn.forbearance.lottery.application.process.res.DrawProcessResult;
import cn.forbearance.lottery.application.process.res.RuleQuantificationCrowdResult;
import cn.forbearance.lottery.common.Constants;
import cn.forbearance.lottery.domain.activity.model.req.PartakeReq;
import cn.forbearance.lottery.domain.activity.model.res.PartakeResult;
import cn.forbearance.lottery.domain.activity.model.vo.DrawOrderVo;
import cn.forbearance.lottery.domain.activity.service.partake.IActivityPartake;
import cn.forbearance.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.forbearance.lottery.domain.rule.model.res.EngineResult;
import cn.forbearance.lottery.domain.rule.service.engine.EngineFilter;
import cn.forbearance.lottery.domain.strategy.model.req.DrawReq;
import cn.forbearance.lottery.domain.strategy.model.res.DrawResult;
import cn.forbearance.lottery.domain.strategy.model.vo.DrawAwardVo;
import cn.forbearance.lottery.domain.strategy.service.draw.IDrawExec;
import cn.forbearance.lottery.domain.support.ids.IdGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 活动抽奖流程编排
 *
 * @author cristina
 */
@Service
public class ActivityProcessImpl implements IActivityProcess {

    @Resource
    private IActivityPartake activityPartake;

    @Resource
    private IDrawExec drawExec;

    @Resource
    private Map<Constants.Ids, IdGenerator> idGenerators;

    @Resource(name = "ruleEngineHandle")
    private EngineFilter engineFilter;

    @Override
    public DrawProcessResult doDrawProcess(DrawProcessReq req) {
        // 1. 领取活动
        PartakeResult partakeResult = activityPartake.doPartake(new PartakeReq(req.getuId(), req.getActivityId()));
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(partakeResult.getCode())) {
            return new DrawProcessResult(partakeResult.getCode(), partakeResult.getInfo());
        }
        Long strategyId = partakeResult.getStrategyId();
        Long takeId = partakeResult.getTakeId();

        // 2. 执行抽奖
        DrawResult drawResult = drawExec.doDrawExec(new DrawReq(req.getuId(), strategyId, String.valueOf(takeId)));
        if (Constants.DrawState.FAIL.getCode().equals(drawResult.getDrawState())) {
            return new DrawProcessResult(Constants.ResponseCode.LOSING_DRAW.getCode(), Constants.ResponseCode.LOSING_DRAW.getInfo());
        }
        DrawAwardVo drawAwardVo = drawResult.getDrawAwardVo();

        // 3. 结果落库
        activityPartake.recordDrawOrder(buildDrawOrderVO(req, strategyId, takeId, drawAwardVo));

        // TODO 4. 发送MQ，触发发奖流程

        // 5. 返回结果
        return new DrawProcessResult(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo(), drawAwardVo);
    }

    @Override
    public RuleQuantificationCrowdResult doRuleQuantificationCrowd(DecisionMatterReq req) {
        // 1. 量化决策
        EngineResult engineResult = engineFilter.process(req);

        if (!engineResult.isSuccess()) {
            return new RuleQuantificationCrowdResult(Constants.ResponseCode.RULE_ERR.getCode(),Constants.ResponseCode.RULE_ERR.getInfo());
        }

        // 2. 封装结果
        RuleQuantificationCrowdResult ruleQuantificationCrowdResult = new RuleQuantificationCrowdResult(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
        ruleQuantificationCrowdResult.setActivityId(Long.valueOf(engineResult.getNodeValue()));

        return ruleQuantificationCrowdResult;
    }

    private DrawOrderVo buildDrawOrderVO(DrawProcessReq req, Long strategyId, Long takeId, DrawAwardVo drawAwardVo) {
        long orderId = idGenerators.get(Constants.Ids.SnowFlake).nextId();
        DrawOrderVo drawOrderVO = new DrawOrderVo();
        drawOrderVO.setuId(req.getuId());
        drawOrderVO.setTakeId(takeId);
        drawOrderVO.setActivityId(req.getActivityId());
        drawOrderVO.setOrderId(orderId);
        drawOrderVO.setStrategyId(strategyId);
        drawOrderVO.setStrategyMode(drawAwardVo.getStrategyMode());
        drawOrderVO.setGrantType(drawAwardVo.getGrantType());
        drawOrderVO.setGrantDate(drawAwardVo.getGrantDate());
        drawOrderVO.setGrantState(Constants.GrantState.INIT.getCode());
        drawOrderVO.setAwardId(drawAwardVo.getAwardId());
        drawOrderVO.setAwardType(drawAwardVo.getAwardType());
        drawOrderVO.setAwardName(drawAwardVo.getAwardName());
        drawOrderVO.setAwardContent(drawAwardVo.getAwardContent());
        return drawOrderVO;
    }
}
