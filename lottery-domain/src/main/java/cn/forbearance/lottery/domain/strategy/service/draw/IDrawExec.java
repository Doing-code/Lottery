package cn.forbearance.lottery.domain.strategy.service.draw;

import cn.forbearance.lottery.domain.strategy.model.req.DrawReq;
import cn.forbearance.lottery.domain.strategy.model.res.DrawResult;

/**
 * @author cristina
 */
public interface IDrawExec {

    /**
     * #
     *
     * @param req
     * @return
     */
    DrawResult doDrawExec(DrawReq req);
}
