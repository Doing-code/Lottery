package cn.forbearance.lottery.domain.strategy.repository;

import cn.forbearance.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.forbearance.lottery.infrastructure.po.Award;

import java.util.List;

/**
 * 策略表仓储服务
 *
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

    /**
     * 查询没有库存的奖品
     *
     * @param strategyId
     * @return
     */
    List<String> queryNoStockStrategyAwards(Long strategyId);

    /**
     * 扣减库存
     *
     * @param strategyId
     * @param awardId
     * @return
     */
    boolean deductStock(Long strategyId, String awardId);

}
