package cn.forbearance.lottery.domain.strategy.service.draw.impl;

import cn.forbearance.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.forbearance.lottery.domain.strategy.model.req.DrawReq;
import cn.forbearance.lottery.domain.strategy.model.res.DrawResult;
import cn.forbearance.lottery.domain.strategy.repository.IStrategyRepository;
import cn.forbearance.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import cn.forbearance.lottery.domain.strategy.service.draw.DrawBase;
import cn.forbearance.lottery.domain.strategy.service.draw.IDrawExec;
import cn.forbearance.lottery.infrastructure.po.Award;
import cn.forbearance.lottery.infrastructure.po.Strategy;
import cn.forbearance.lottery.infrastructure.po.StrategyDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cristina
 */
@Service("drawExec")
public class DrawExecImpl extends DrawBase implements IDrawExec {
    private final Logger log = LoggerFactory.getLogger(DrawExecImpl.class);

    @Resource
    private IStrategyRepository strategyRepository;

    @Override
    public DrawResult doDrawExec(DrawReq req) {
        log.info("执行策略抽奖开始，strategyId：{}", req.getStrategyId());

        // 获取抽奖策略配置
        StrategyRich strategyRich = strategyRepository.queryStrategyRich(req.getStrategyId());
        Strategy strategy = strategyRich.getStrategy();
        List<StrategyDetail> strategyDetailList = strategyRich.getStrategyDetailList();

        // 校验和初始化数据
        checkAndInitRateTuple(req.getStrategyId(), strategy.getStrategyMode(), strategyDetailList);

        // 根据抽奖策略方式抽奖
        IDrawAlgorithm drawAlgorithm = drawAlgorithms.get(strategy.getStrategyMode());
        String awardId = drawAlgorithm.randomDraw(req.getStrategyId(), new ArrayList<>());

        // 获取奖品信息
        Award award = strategyRepository.queryAwardInfo(awardId);
        log.info("执行策略抽奖完成，中奖用户：{} 奖品ID：{} 奖品名称：{}", req.getuId(), awardId, award.getAwardName());

        return new DrawResult(req.getuId(), req.getStrategyId(), awardId, award.getAwardName());
    }
}
