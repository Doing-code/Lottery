package cn.forbearance.lottery.domain.rule.service.engine;

import cn.forbearance.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.forbearance.lottery.domain.rule.model.res.EngineResult;

/**
 * 规则过滤器引擎
 *
 * @author cristina
 */
public interface EngineFilter {

    /**
     * 规则过滤器
     *
     * @param matter
     * @return
     */
    EngineResult process(final DecisionMatterReq matter);
}
