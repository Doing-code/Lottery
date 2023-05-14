package cn.forbearance.lottery.domain.strategy.repository.impl;

import cn.forbearance.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.forbearance.lottery.domain.strategy.repository.IStrategyRepository;
import cn.forbearance.lottery.infrastructure.dao.IAwardDao;
import cn.forbearance.lottery.infrastructure.dao.IStrategyDao;
import cn.forbearance.lottery.infrastructure.dao.IStrategyDetailDao;
import cn.forbearance.lottery.infrastructure.po.Award;
import cn.forbearance.lottery.infrastructure.po.Strategy;
import cn.forbearance.lottery.infrastructure.po.StrategyDetail;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cristina
 */
@Component
public class StrategyRepository implements IStrategyRepository {

    @Resource
    private IStrategyDao strategyDao;

    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Resource
    private IAwardDao awardDao;

    @Override
    public StrategyRich queryStrategyRich(Long strategyId) {
        Strategy strategy = strategyDao.queryStrategy(strategyId);
        List<StrategyDetail> strategyDetails = strategyDetailDao.queryStrategyDetailList(strategyId);
        return new StrategyRich(strategyId, strategy, strategyDetails);
    }

    @Override
    public Award queryAwardInfo(String awardId) {
        return awardDao.queryAwardInfo(awardId);
    }

    @Override
    public List<String> queryNoStockStrategyAwards(Long strategyId) {
        return strategyDetailDao.queryNoStockStrategyAwards(strategyId);
    }

    @Override
    public boolean deductStock(Long strategyId, String awardId) {
        int count = strategyDetailDao.deductStock(strategyId, awardId);
        return count == 1;
    }
}
