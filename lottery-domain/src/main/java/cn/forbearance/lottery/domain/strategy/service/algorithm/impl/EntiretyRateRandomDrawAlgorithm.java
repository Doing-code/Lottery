package cn.forbearance.lottery.domain.strategy.service.algorithm.impl;

import cn.forbearance.lottery.domain.strategy.model.vo.AwardRateInfo;
import cn.forbearance.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * 总体概率：必中奖策略抽奖，排掉已经中奖的概率，重新计算中奖范围
 *
 * @author cristina
 */
@Component("entiretyRateRandomDrawAlgorithm")
public class EntiretyRateRandomDrawAlgorithm extends BaseAlgorithm {

    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
        BigDecimal differenceDenominator = BigDecimal.ZERO;
        ArrayList<AwardRateInfo> differenceAwardRates = new ArrayList<>();

        // 排除不在抽奖范围的奖品id集合
        for (AwardRateInfo awardRateInfo : awardRateInfoMap.get(strategyId)) {
            if (excludeAwardIds.contains(awardRateInfo.getAwardId())) {
                continue;
            }
            differenceAwardRates.add(awardRateInfo);
            differenceDenominator = differenceDenominator.add(awardRateInfo.getAwardRate());
        }

        // 前置判断
        if (differenceAwardRates.size() == 0) return null;
        if (differenceAwardRates.size() == 1) return differenceAwardRates.get(0).getAwardId();

        // 获取随机概率
        int randomVal = new SecureRandom().nextInt(100) + 1;

        String awardId = "";
        int cursorVal = 0;
        // 循环获取奖品
        for (AwardRateInfo awardRateInfo : differenceAwardRates) {
            int rateVal = awardRateInfo.getAwardRate().divide(differenceDenominator, 2, BigDecimal.ROUND_UP).multiply(DEFAULT_SHARE).intValue();
            if (randomVal <= (cursorVal + rateVal)) {
                awardId = awardRateInfo.getAwardId();
                break;
            }
            cursorVal += rateVal;
        }
        // 返回中将结果
        return awardId;
    }
}
