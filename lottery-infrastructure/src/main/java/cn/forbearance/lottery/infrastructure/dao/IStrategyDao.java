package cn.forbearance.lottery.infrastructure.dao;

import cn.forbearance.lottery.infrastructure.po.Strategy;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author cristina
 */
@Mapper
public interface IStrategyDao {

    /**
     * 按照抽奖策略id 查询抽奖策略
     *
     * @param strategyId
     * @return
     */
    Strategy queryStrategy(Long strategyId);

    /**
     * 插入
     * @param req
     */
    void insert(Strategy req);
}
