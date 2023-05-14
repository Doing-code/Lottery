package cn.forbearance.lottery.infrastructure.dao;

import cn.forbearance.lottery.infrastructure.po.StrategyDetail;
import org.apache.ibatis.annotations.Mapper;

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
}
