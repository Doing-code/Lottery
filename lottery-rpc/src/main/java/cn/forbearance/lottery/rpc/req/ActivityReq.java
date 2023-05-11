package cn.forbearance.lottery.rpc.req;

import java.io.Serializable;

/**
 * @author cristina
 */
public class ActivityReq implements Serializable {

    private Long activityId;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
