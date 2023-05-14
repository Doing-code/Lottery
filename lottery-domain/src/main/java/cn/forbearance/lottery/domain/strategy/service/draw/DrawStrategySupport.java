package cn.forbearance.lottery.domain.strategy.service.draw;

import cn.forbearance.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.forbearance.lottery.domain.strategy.repository.IStrategyRepository;
import cn.forbearance.lottery.infrastructure.po.Award;

import javax.annotation.Resource;

/**
 * 抽奖策略数据支撑，提供通用的数据服务
 *
 * @author cristina
 */
public class DrawStrategySupport extends DrawConfig {

    @Resource
    protected IStrategyRepository strategyRepository;

    protected StrategyRich queryStrategyRich(Long strategyId) {
        return strategyRepository.queryStrategyRich(strategyId);
    }

    protected Award queryAwardInfo(String awardId) {
        return strategyRepository.queryAwardInfo(awardId);
    }
}
