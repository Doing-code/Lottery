package cn.forbearance.lottery.domain.activity.repository;

import cn.forbearance.lottery.common.Constants;
import cn.forbearance.lottery.domain.activity.model.req.PartakeReq;
import cn.forbearance.lottery.domain.activity.model.vo.*;

import java.util.List;

/**
 * 活动仓库服务(活动表、奖品表、策略表、策略明细表)
 *
 * @author cristina
 */
public interface IActivityRepository {

    /**
     * 添加活动配置
     *
     * @param activity
     */
    void addActivity(ActivityVo activity);

    /**
     * 添加奖品配置
     *
     * @param award
     */
    void addAward(List<AwardVo> award);

    /**
     * 添加抽奖策略配置
     *
     * @param strategy
     */
    void addStrategy(StrategyVo strategy);

    /**
     * 添加抽奖策略明细配置
     *
     * @param strategyDetails
     */
    void addStrategyDetails(List<StrategyDetailVo> strategyDetails);

    /**
     * 变更活动状态
     *
     * @param activityId
     * @param beforeState
     * @param afterState
     * @return
     */
    boolean alterStatus(Long activityId, Enum<Constants.ActivityState> beforeState, Enum<Constants.ActivityState> afterState);

    /**
     * 查询活动账单信息【库存、状态、日期、个人参与次数】
     *
     * @param req 参与活动请求
     * @return 活动账单
     */
    ActivityBillVo queryActivityBill(PartakeReq req);

    /**
     * 扣减活动库存
     *
     * @param activityId 活动ID
     * @return 扣减结果
     */
    int subtractionActivityStock(Long activityId);
}
