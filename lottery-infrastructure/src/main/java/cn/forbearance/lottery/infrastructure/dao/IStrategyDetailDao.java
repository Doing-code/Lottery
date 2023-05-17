package cn.forbearance.lottery.infrastructure.dao;

import cn.forbearance.lottery.infrastructure.po.StrategyDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cristina
 */
@Mapper
public interface IStrategyDetailDao {

    /**
     * 按照抽奖策略id 查询抽奖策略明细
     *
     * @param strategyId
     * @return
     */
    List<StrategyDetail> queryStrategyDetailList(Long strategyId);

    /**
     * 查询无库存的奖品
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
    int deductStock(@Param("strategyId") Long strategyId, @Param("awardId") String awardId);

    /**
     * 批量插入
     *
     * @param req
     */
    void insertBatch(List<StrategyDetail> req);
}
