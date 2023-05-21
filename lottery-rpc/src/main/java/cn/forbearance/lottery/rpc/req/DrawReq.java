package cn.forbearance.lottery.rpc.req;

import java.io.Serializable;

/**
 * 抽奖参数
 *
 * @author cristina
 */
public class DrawReq implements Serializable {

    private static final long serialVersionUID = 1140166053349720447L;

    /**
     * 用户ID
     */
    private String uId;
    /**
     * 活动ID
     */
    private Long activityId;

    public DrawReq() {
    }

    public DrawReq(String uId, Long activityId) {
        this.uId = uId;
        this.activityId = activityId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
