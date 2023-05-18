package cn.forbearance.lottery.application.process;

import cn.forbearance.lottery.application.process.req.DrawProcessReq;
import cn.forbearance.lottery.application.process.res.DrawProcessResult;

/**
 * 活动抽奖流程编排
 *
 * @author cristina
 */
public interface IActivityProcess {

    /**
     * 执行抽奖流程
     *
     * @param req 抽奖请求
     * @return 抽奖结果
     */
    DrawProcessResult doDrawProcess(DrawProcessReq req);
}
