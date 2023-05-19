package cn.forbearance.lottery.domain.rule.service.logic;

import cn.forbearance.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.forbearance.lottery.domain.rule.model.vo.TreeNodeLineVo;

import java.util.List;

/**
 * 规则过滤接口
 *
 * @author cristina
 */
public interface LogicFilter {

    /**
     * 逻辑决策器
     *
     * @param matterValue
     * @param treeNodeLineInfos
     * @return
     */
    Long filter(String matterValue, List<TreeNodeLineVo> treeNodeLineInfos);

    /**
     * 获取决策值
     *
     * @param decisionMatter
     * @return
     */
    String matterValue(DecisionMatterReq decisionMatter);
}
