package cn.forbearance.lottery.rpc;

import cn.forbearance.lottery.rpc.req.ActivityReq;
import cn.forbearance.lottery.rpc.res.ActivityRes;

/**
 * @author cristina
 */
public interface IActivityBooth {

    ActivityRes queryActivityById(ActivityReq req);
}
