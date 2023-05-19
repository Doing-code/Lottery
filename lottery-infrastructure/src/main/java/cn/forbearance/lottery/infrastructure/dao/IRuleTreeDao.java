package cn.forbearance.lottery.infrastructure.dao;

import cn.forbearance.lottery.infrastructure.po.RuleTree;
import org.apache.ibatis.annotations.Mapper;

/**
 * 规则树配置
 *
 * @author cristina
 */
@Mapper
public interface IRuleTreeDao {

    /**
     * 规则树查询
     *
     * @param id ID
     * @return 规则树
     */
    RuleTree queryRuleTreeByTreeId(Long id);

    /**
     * 规则树简要信息查询
     *
     * @param treeId 规则树ID
     * @return 规则树
     */
    RuleTree queryTreeSummaryInfo(Long treeId);
}
