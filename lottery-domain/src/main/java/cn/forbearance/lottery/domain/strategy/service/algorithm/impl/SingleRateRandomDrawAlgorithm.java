package cn.forbearance.lottery.domain.strategy.service.algorithm.impl;

import cn.forbearance.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 单项随机概率抽奖，抽到一个已经排掉的奖品则未中奖
 *
 * @author cristina
 */
@Component("singleRateRandomDrawAlgorithm")
public class SingleRateRandomDrawAlgorithm extends BaseAlgorithm {

    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
        // 获取策略对应的元组
        String[] rateTuple = rateTupleMap.get(strategyId);
        assert rateTuple != null;

        // 随机索引
        int randomVal = generateSecureRandomCode(100);
        int idx = hashIdx(randomVal);

        // 返回结果
        String awardId = rateTuple[idx];
        if (excludeAwardIds.contains(awardId)) {
            return null;
        }
        return awardId;
    }
}
