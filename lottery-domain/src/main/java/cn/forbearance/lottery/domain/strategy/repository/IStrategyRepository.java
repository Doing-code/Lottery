package cn.forbearance.lottery.domain.strategy.repository;

import cn.forbearance.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.forbearance.lottery.infrastructure.po.Award;

/**
 * @author cristina
 */
public interface IStrategyRepository {

    /**
     * 按照抽奖策略id 查询抽奖策略信息
     *
     * @param strategyId
     * @return
     */
    StrategyRich queryStrategyRich(Long strategyId);

    /**
     * 按照奖品id 查询奖品
     *
     * @param awardId
     * @return
     */
    Award queryAwardInfo(String awardId);

}
