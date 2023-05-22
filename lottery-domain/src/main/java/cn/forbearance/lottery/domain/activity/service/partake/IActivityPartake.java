package cn.forbearance.lottery.domain.activity.service.partake;

import cn.forbearance.lottery.common.Result;
import cn.forbearance.lottery.domain.activity.model.req.PartakeReq;
import cn.forbearance.lottery.domain.activity.model.res.PartakeResult;
import cn.forbearance.lottery.domain.activity.model.vo.DrawOrderVo;

/**
 * 抽奖活动参与接口
 *
 * @author cristina
 */
public interface IActivityPartake {

    /**
     * 参与活动
     *
     * @param req 入参
     * @return 领取结果
     */
    PartakeResult doPartake(PartakeReq req);

    /**
     * 保存奖品单
     *
     * @param drawOrder 奖品单
     * @return 保存结果
     */
    Result recordDrawOrder(DrawOrderVo drawOrder);

    /**
     * 更新发货单MQ状态
     *
     * @param uId     用户ID
     * @param orderId 订单ID
     * @param mqState MQ 发送状态
     */
    void updateInvoiceMqState(String uId, Long orderId, Integer mqState);
}
