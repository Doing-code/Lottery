package cn.forbearance.lottery.domain.rule.service.engine.impl;

import cn.forbearance.lottery.domain.rule.model.aggregates.TreeRuleRich;
import cn.forbearance.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.forbearance.lottery.domain.rule.model.res.EngineResult;
import cn.forbearance.lottery.domain.rule.model.vo.TreeNodeVo;
import cn.forbearance.lottery.domain.rule.repository.IRuleRepository;
import cn.forbearance.lottery.domain.rule.service.engine.BaseEngine;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 规则引擎处理器
 *
 * @author cristina
 */
@Service("ruleEngineHandle")
public class RuleEngineHandler extends BaseEngine {

    @Resource
    private IRuleRepository ruleRepository;

    @Override
    public EngineResult process(DecisionMatterReq matter) {
        // 决策规则树
        TreeRuleRich treeRuleRich = ruleRepository.queryTreeRuleRich(matter.getTreeId());
        if (null == treeRuleRich) {
            throw new RuntimeException("Tree Rule is null!");
        }

        // 决策节点
        TreeNodeVo treeNodeInfo = engineDecisionMaker(treeRuleRich, matter);

        // 决策结果
        return new EngineResult(matter.getUserId(), treeNodeInfo.getTreeId(), treeNodeInfo.getTreeNodeId(), treeNodeInfo.getNodeValue());
    }
}
