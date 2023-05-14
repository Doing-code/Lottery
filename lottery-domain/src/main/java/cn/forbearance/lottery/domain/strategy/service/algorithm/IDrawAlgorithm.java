package cn.forbearance.lottery.domain.strategy.service.algorithm;

import cn.forbearance.lottery.domain.strategy.model.vo.AwardRateInfo;

import java.util.List;

/**
 * 抽奖算法接口
 *
 * @author cristina
 */
public interface IDrawAlgorithm {

    /**
     * 程序启动时初始化概率元组，在初始化完成后使用过程中不允许修改元组数据
     * <p>
     * 元组数据作用在于将百分比内（0.2、0.3、0.5）的数据，转换为一整条数组上分区数据，如下：
     * <pre>
     *     0.2 = (0 ~ 0.2]
     *     0.3 = (0 + 0.2) ~ (0.2 + 0.3) = (0.2 ~ 0.5]
     *     0.5 = (0 + 0.2 + 0.3) ~ (0.2 + 0.3 + 0.5) = (0.5 ~ 1]
     * </pre>
     * 通过数据拆分为整条后，再根据 0-100 中各个区间的奖品信息，使用裴波那契散列计算出索引位置，把奖品数据存放到元组中。比如：
     * <p>
     * <pre>
     *     1. 把 0.2 转换为 20
     *     2. 20 对应的裴波那契值哈希值：20 * HASH_INCREMENT + HASH_INCREMENT）= -1549107828 HASH_INCREMENT = 0x61c88647
     *     3. 再通过哈希值计算索引位置：hashCode & (rateTuple.length - 1) = 12
     *     4. 那么 tup[12] = 0.2 中将概率对应的奖品
     *     5. 当后续通过随机数获取到 1-100 的值后，可以直接定位到对应的奖品信息，通过这样的方式把轮询算奖的时间复杂度从 O(n) 降低到 O(1)
     * </pre>
     *
     * @param strategyId     策略id
     * @param awardRateInfos 奖品概率配置集合：「值示例：AwardRateInfo.awardRate = 0.04」
     */
    void initRateTuple(Long strategyId, List<AwardRateInfo> awardRateInfos);

    /**
     * 判断是否数据初始化
     *
     * @param strategyId
     * @return
     */
    boolean isExistRateTuple(Long strategyId);

    /**
     * 生成随机数，索引到对应的奖品信息
     *
     * @param strategyId      策略ID
     * @param excludeAwardIds 排除掉已经不能作为抽奖的奖品ID，留给风控和空库存使用
     * @return 中奖结果
     */
    String randomDraw(Long strategyId, List<String> excludeAwardIds);
}
