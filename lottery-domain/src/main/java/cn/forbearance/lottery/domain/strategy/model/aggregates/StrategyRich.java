package cn.forbearance.lottery.domain.strategy.model.aggregates;

import cn.forbearance.lottery.domain.strategy.model.vo.StrategyBriefVo;
import cn.forbearance.lottery.domain.strategy.model.vo.StrategyDetailBriefVo;

import java.util.List;

/**
 * @author cristina
 */
public class StrategyRich {

    /**
     * 策略ID
     */

    private Long strategyId;
    /**
     * 策略配置
     */
    private StrategyBriefVo strategy;
    /**
     * 策略明细
     */
    private List<StrategyDetailBriefVo> strategyDetailList;

    public StrategyRich() {
    }

    public StrategyRich(Long strategyId, StrategyBriefVo strategy, List<StrategyDetailBriefVo> strategyDetailList) {
        this.strategyId = strategyId;
        this.strategy = strategy;
        this.strategyDetailList = strategyDetailList;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public StrategyBriefVo getStrategy() {
        return strategy;
    }

    public void setStrategy(StrategyBriefVo strategy) {
        this.strategy = strategy;
    }

    public List<StrategyDetailBriefVo> getStrategyDetailList() {
        return strategyDetailList;
    }

    public void setStrategyDetailList(List<StrategyDetailBriefVo> strategyDetailList) {
        this.strategyDetailList = strategyDetailList;
    }

}
