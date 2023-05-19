package cn.forbearance.lottery.domain.rule.repository;

import cn.forbearance.lottery.domain.rule.model.aggregates.TreeRuleRich;

/**
 * 规则信息仓储服务
 *
 * @author cristina
 */
public interface IRuleRepository {

    /**
     * 查询规则决策树配置
     *
     * @param treeId
     * @return
     */
    TreeRuleRich queryTreeRuleRich(Long treeId);
}
