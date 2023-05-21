package cn.forbearance.lottery.rpc;

import cn.forbearance.lottery.rpc.req.DrawReq;
import cn.forbearance.lottery.rpc.req.QuantificationDrawReq;
import cn.forbearance.lottery.rpc.res.DrawRes;

/**
 * 抽奖活动
 *
 * @author cristina
 */
public interface ILotteryActivityBooth {

    /**
     * 指定抽奖活动
     *
     * @param req
     * @return
     */
    DrawRes doDraw(DrawReq req);

    /**
     * 量化人群抽奖
     *
     * @param req
     * @return
     */
    DrawRes doQuantificationDraw(QuantificationDrawReq req);
}
