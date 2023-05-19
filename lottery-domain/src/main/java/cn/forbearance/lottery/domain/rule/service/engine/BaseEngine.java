package cn.forbearance.lottery.domain.rule.service.engine;

import cn.forbearance.lottery.common.Constants;
import cn.forbearance.lottery.domain.rule.model.aggregates.TreeRuleRich;
import cn.forbearance.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.forbearance.lottery.domain.rule.model.res.EngineResult;
import cn.forbearance.lottery.domain.rule.model.vo.TreeNodeVo;
import cn.forbearance.lottery.domain.rule.model.vo.TreeRootVo;
import cn.forbearance.lottery.domain.rule.service.logic.LogicFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 规则引擎基类
 *
 * @author cristina
 */
public abstract class BaseEngine extends EngineConfig implements EngineFilter {

    private final Logger log = LoggerFactory.getLogger(BaseEngine.class);

    @Override
    public EngineResult process(DecisionMatterReq matter) {
        throw new RuntimeException("未实现规则引擎服务");
    }

    protected TreeNodeVo engineDecisionMaker(TreeRuleRich treeRuleRich, DecisionMatterReq matter) {
        TreeRootVo treeRoot = treeRuleRich.getTreeRoot();
        Map<Long, TreeNodeVo> treeNodeMap = treeRuleRich.getTreeNodeMap();

        // 规则树根ID
        Long rootNodeId = treeRoot.getTreeRootNodeId();
        TreeNodeVo treeNodeInfo = treeNodeMap.get(rootNodeId);

        // 节点类型[NodeType]；1子叶、2果实
        while (Constants.NodeType.STEM.equals(treeNodeInfo.getNodeType())) {
            String ruleKey = treeNodeInfo.getRuleKey();
            LogicFilter logicFilter = logicFilterMap.get(ruleKey);
            String matterValue = logicFilter.matterValue(matter);
            Long nextNode = logicFilter.filter(matterValue, treeNodeInfo.getTreeNodeLineInfoList());
            treeNodeInfo = treeNodeMap.get(nextNode);
            log.info("决策树引擎=>{} userId：{} treeId：{} treeNode：{} ruleKey：{} matterValue：{}", treeRoot.getTreeName(), matter.getUserId(), matter.getTreeId(), treeNodeInfo.getTreeNodeId(), ruleKey, matterValue);
        }
        return treeNodeInfo;
    }
}
