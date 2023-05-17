package cn.forbearance.lottery.domain.activity.model.res;

import cn.forbearance.lottery.common.Result;

/**
 * 活动参与响应
 *
 * @author cristina
 */
public class PartakeResult extends Result {

    private static final long serialVersionUID = 3702810653299017846L;
    /**
     * 策略ID
     */
    private Long strategyId;

    public PartakeResult(String code, String info) {
        super(code, info);
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }
}
