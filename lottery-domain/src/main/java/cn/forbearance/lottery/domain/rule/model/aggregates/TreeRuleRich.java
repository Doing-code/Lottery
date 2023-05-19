package cn.forbearance.lottery.domain.rule.model.aggregates;

import cn.forbearance.lottery.domain.rule.model.vo.TreeNodeVo;
import cn.forbearance.lottery.domain.rule.model.vo.TreeRootVo;

import java.util.Map;

/**
 * 规则树聚合
 *
 * @author cristina
 */
public class TreeRuleRich {

    /**
     * 树根信息
     */
    private TreeRootVo treeRoot;
    /**
     * 树节点ID -> 子节点
     */
    private Map<Long, TreeNodeVo> treeNodeMap;

    public TreeRootVo getTreeRoot() {
        return treeRoot;
    }

    public void setTreeRoot(TreeRootVo treeRoot) {
        this.treeRoot = treeRoot;
    }

    public Map<Long, TreeNodeVo> getTreeNodeMap() {
        return treeNodeMap;
    }

    public void setTreeNodeMap(Map<Long, TreeNodeVo> treeNodeMap) {
        this.treeNodeMap = treeNodeMap;
    }
}
