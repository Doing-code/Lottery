package cn.forbearance.lottery.application.process.res;

import cn.forbearance.lottery.common.Result;

/**
 * @author cristina
 */
public class RuleQuantificationCrowdResult extends Result {

    private static final long serialVersionUID = -4928640766002101822L;

    /**
     * 活动ID
     */
    private Long activityId;

    public RuleQuantificationCrowdResult(String code, String info) {
        super(code, info);
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
