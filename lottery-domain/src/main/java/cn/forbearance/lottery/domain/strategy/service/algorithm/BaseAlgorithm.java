package cn.forbearance.lottery.domain.strategy.service.algorithm;

import cn.forbearance.lottery.domain.strategy.model.vo.AwardRateInfo;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 共用的算法逻辑
 *
 * @author cristina
 */
public abstract class BaseAlgorithm implements IDrawAlgorithm {

    /**
     * 斐波那契散列增量，逻辑：黄金分割点：(√5 - 1) / 2 = 0.6180339887，Math.pow(2, 32) * 0.6180339887 = 0x61c88647
     */
    private final int HASH_INCREMENT = 0x61c88647;

    /**
     * 初始化数组长度
     */
    private final int RATE_TUPLE_LENGTH = 128;

    /**
     * 存放概率与奖品对应的散列结果：strategyId -> rateTuple
     */
    protected Map<Long, String[]> rateTupleMap = new ConcurrentHashMap<>();

    /**
     * 奖品区间概率：strategyId -> [awardId->begin、awardId->end]
     */
    protected Map<Long, List<AwardRateInfo>> awardRateInfoMap = new ConcurrentHashMap<>();

    protected final BigDecimal DEFAULT_SHARE = new BigDecimal(100);

    @Override
    public void initRateTuple(Long strategyId, List<AwardRateInfo> awardRateInfos) {
        // 保存奖品概率信息
        awardRateInfoMap.put(strategyId, awardRateInfos);
        String[] rateTuple = rateTupleMap.computeIfAbsent(strategyId, k -> new String[RATE_TUPLE_LENGTH]);

        int cursorVal = 0;
        for (AwardRateInfo awardRateInfo : awardRateInfos) {
            int rateVal = awardRateInfo.getAwardRate().multiply(DEFAULT_SHARE).intValue();
            for (int i = cursorVal + 1; i <= (rateVal + cursorVal); i++) {
                // 填充概率范围值
                rateTuple[hashIdx(i)] = awardRateInfo.getAwardId();
            }
            cursorVal += rateVal;
        }
    }

    @Override
    public boolean isExistRateTuple(Long strategyId) {
        return rateTupleMap.containsKey(strategyId);
    }

    /**
     * 裴波那契（Fibonacci）散列法，计算哈希索引下标值
     *
     * @param val
     * @return
     */
    protected int hashIdx(int val) {
        int hashCode = val * HASH_INCREMENT + HASH_INCREMENT;
        return hashCode & (RATE_TUPLE_LENGTH - 1);
    }

    /**
     * 随机值
     *
     * @param bound
     * @return
     */
    protected int generateSecureRandomCode(int bound) {
        return new SecureRandom().nextInt(bound) + 1;
    }
}
