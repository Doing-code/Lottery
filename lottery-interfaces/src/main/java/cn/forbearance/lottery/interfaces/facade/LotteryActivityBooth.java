package cn.forbearance.lottery.interfaces.facade;

import cn.forbearance.lottery.application.process.IActivityProcess;
import cn.forbearance.lottery.application.process.req.DrawProcessReq;
import cn.forbearance.lottery.application.process.res.DrawProcessResult;
import cn.forbearance.lottery.application.process.res.RuleQuantificationCrowdResult;
import cn.forbearance.lottery.common.Constants;
import cn.forbearance.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.forbearance.lottery.domain.strategy.model.vo.DrawAwardVo;
import cn.forbearance.lottery.interfaces.assembler.IMapping;
import cn.forbearance.lottery.rpc.ILotteryActivityBooth;
import cn.forbearance.lottery.rpc.dto.AwardDto;
import cn.forbearance.lottery.rpc.req.DrawReq;
import cn.forbearance.lottery.rpc.req.QuantificationDrawReq;
import cn.forbearance.lottery.rpc.res.DrawRes;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @author cristina
 */
@Controller
public class LotteryActivityBooth implements ILotteryActivityBooth {

    private final Logger log = LoggerFactory.getLogger(LotteryActivityBooth.class);

    @Resource
    private IActivityProcess activityProcess;

    @Resource
    private IMapping<DrawAwardVo, AwardDto> awardMapping;

    @Override
    public DrawRes doDraw(DrawReq req) {
        try {
            log.info("抽奖，开始 uId：{} activityId：{}", req.getuId(), req.getActivityId());

            // 1. 执行抽奖
            DrawProcessResult drawProcessResult = activityProcess.doDrawProcess(new DrawProcessReq(req.getuId(), req.getActivityId()));
            if (!Constants.ResponseCode.SUCCESS.getCode().equals(drawProcessResult.getCode())) {
                log.error("抽奖，失败(抽奖过程异常) uId：{} activityId：{}", req.getuId(), req.getActivityId());
                return new DrawRes(drawProcessResult.getCode(), drawProcessResult.getInfo());
            }

            // 2. 数据转换
            DrawAwardVo drawAwardVO = drawProcessResult.getDrawAwardVo();
            AwardDto awardDTO = awardMapping.sourceToTarget(drawAwardVO);
            awardDTO.setActivityId(req.getActivityId());

            // 3. 封装数据
            DrawRes drawRes = new DrawRes(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
            drawRes.setAwardDTO(awardDTO);

            log.info("抽奖，完成 uId：{} activityId：{} drawRes：{}", req.getuId(), req.getActivityId(), JSON.toJSONString(drawRes));
            return drawRes;
        } catch (Exception e) {
            log.error("抽奖，失败 uId：{} activityId：{} reqJson：{}", req.getuId(), req.getActivityId(), JSON.toJSONString(req), e);
            return new DrawRes(Constants.ResponseCode.UN_ERROR.getCode(), Constants.ResponseCode.UN_ERROR.getInfo());
        }
    }

    @Override
    public DrawRes doQuantificationDraw(QuantificationDrawReq req) {
        try {
            log.info("量化人群抽奖，开始 uId：{} treeId：{}", req.getuId(), req.getTreeId());

            // 1. 执行规则引擎，获取用户可以参与的活动号
            RuleQuantificationCrowdResult result = activityProcess.doRuleQuantificationCrowd(new DecisionMatterReq(req.getuId(), req.getTreeId(), req.getValMap()));
            if (!Constants.ResponseCode.SUCCESS.getCode().equals(result.getCode())) {
                log.error("量化人群抽奖，失败(规则引擎执行异常) uId：{} treeId：{}", req.getuId(), req.getTreeId());
                return new DrawRes(result.getCode(), result.getInfo());
            }

            // 2. 执行抽奖
            Long activityId = result.getActivityId();
            DrawProcessResult drawProcessResult = activityProcess.doDrawProcess(new DrawProcessReq(req.getuId(), activityId));
            if (!Constants.ResponseCode.SUCCESS.getCode().equals(drawProcessResult.getCode())) {
                log.error("量化人群抽奖，失败(抽奖过程异常) uId：{} treeId：{}", req.getuId(), req.getTreeId());
                return new DrawRes(drawProcessResult.getCode(), drawProcessResult.getInfo());
            }

            // 3. 数据转换
            DrawAwardVo drawAwardVO = drawProcessResult.getDrawAwardVo();
            AwardDto awardDTO = awardMapping.sourceToTarget(drawAwardVO);
            awardDTO.setActivityId(activityId);

            // 4. 封装数据
            DrawRes drawRes = new DrawRes(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
            drawRes.setAwardDTO(awardDTO);

            log.info("量化人群抽奖，完成 uId：{} treeId：{} drawRes：{}", req.getuId(), req.getTreeId(), JSON.toJSONString(drawRes));
            return drawRes;
        } catch (Exception e) {
            log.error("量化人群抽奖，失败 uId：{} treeId：{} reqJson：{}", req.getuId(), req.getTreeId(), JSON.toJSONString(req), e);
            return new DrawRes(Constants.ResponseCode.UN_ERROR.getCode(), Constants.ResponseCode.UN_ERROR.getInfo());
        }
    }
}
