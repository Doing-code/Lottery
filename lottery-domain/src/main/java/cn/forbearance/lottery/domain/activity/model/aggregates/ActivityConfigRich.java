package cn.forbearance.lottery.domain.activity.model.aggregates;

import cn.forbearance.lottery.domain.activity.model.vo.ActivityVo;
import cn.forbearance.lottery.domain.activity.model.vo.AwardVo;
import cn.forbearance.lottery.domain.activity.model.vo.StrategyVo;

import java.util.List;

/**
 * 活动配置聚合信息
 *
 * @author cristina
 */
public class ActivityConfigRich {

    /**
     * 活动配置
     */
    private ActivityVo activity;

    /**
     * 策略配置(含策略明细)
     */
    private StrategyVo strategy;

    /**
     * 奖品配置
     */
    private List<AwardVo> awardList;

    public ActivityConfigRich() {
    }

    public ActivityConfigRich(ActivityVo activity, StrategyVo strategy, List<AwardVo> awardList) {
        this.activity = activity;
        this.strategy = strategy;
        this.awardList = awardList;
    }

    public ActivityVo getActivity() {
        return activity;
    }

    public void setActivity(ActivityVo activity) {
        this.activity = activity;
    }

    public StrategyVo getStrategy() {
        return strategy;
    }

    public void setStrategy(StrategyVo strategy) {
        this.strategy = strategy;
    }

    public List<AwardVo> getAwardList() {
        return awardList;
    }

    public void setAwardList(List<AwardVo> awardList) {
        this.awardList = awardList;
    }

}
