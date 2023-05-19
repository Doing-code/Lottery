package cn.forbearance.lottery.domain.strategy.service.draw;

import cn.forbearance.lottery.common.Constants;
import cn.forbearance.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.forbearance.lottery.domain.strategy.model.req.DrawReq;
import cn.forbearance.lottery.domain.strategy.model.res.DrawResult;
import cn.forbearance.lottery.domain.strategy.model.vo.*;
import cn.forbearance.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cristina
 */
public abstract class AbstractDrawBase extends DrawStrategySupport implements IDrawExec {

    private final Logger log = LoggerFactory.getLogger(AbstractDrawBase.class);

    @Override
    public DrawResult doDrawExec(DrawReq req) {
        // 1、获取抽奖策略数据
        StrategyRich strategyRich = queryStrategyRich(req.getStrategyId());
        StrategyBriefVo strategy = strategyRich.getStrategy();

        // 2、校验抽奖策略是否初始化
        checkAndInitRateTuple(req.getStrategyId(), strategy.getStrategyMode(), strategyRich.getStrategyDetailList());

        // 3、获取不在抽奖范围内的奖项，包括：库存为空、风控策略、临时调整等
        List<String> excludeAwardIds = queryExcludeAwardIds(req.getStrategyId());

        // 执行抽奖算法
        String awardId = drawAlgorithm(req.getStrategyId(), drawAlgorithmGroup.get(strategy.getStrategyMode()), excludeAwardIds);

        // 包装中间结果
        return buildDrawResult(req.getuId(), req.getStrategyId(), awardId);
    }

    /**
     * 获取不在抽奖范围内的奖项，包括：库存为空、风控策略、临时调整等。这类数据是含有业务逻辑的，所以需要由具体的实现方决定
     *
     * @param strategyId
     * @return
     */
    protected abstract List<String> queryExcludeAwardIds(Long strategyId);

    /**
     * 执行抽奖算法
     *
     * @param strategyId
     * @param drawAlgorithm
     * @param excludeAwardIds
     * @return
     */
    protected abstract String drawAlgorithm(Long strategyId, IDrawAlgorithm drawAlgorithm, List<String> excludeAwardIds);

    /**
     * 保证抽奖解雇
     *
     * @param uId
     * @param strategyId
     * @param awardId
     * @return
     */
    private DrawResult buildDrawResult(String uId, Long strategyId, String awardId) {
        if (null == awardId) {
            log.info("执行策略抽奖完成【未中奖】，用户：{} 策略ID：{}", uId, strategyId);
            return new DrawResult(uId, strategyId, Constants.DrawState.FAIL.getCode());
        }
        AwardBriefVo award = queryAwardInfo(awardId);
        DrawAwardVo drawAwardVo = new DrawAwardVo(award.getAwardId(), award.getAwardType(), award.getAwardName(), award.getAwardContent());
        log.info("执行策略抽奖完成【已中奖】，用户：{} 策略ID：{} 奖品ID：{} 奖品名称：{}", uId, strategyId, awardId, award.getAwardName());

        return new DrawResult(uId, strategyId, Constants.DrawState.SUCCESS.getCode(), drawAwardVo);
    }

    /**
     * 校验抽奖策略是否初始化
     *
     * @param strategyId
     * @param strategyMode
     * @param strategyDetails
     */
    public void checkAndInitRateTuple(Long strategyId, Integer strategyMode, List<StrategyDetailBriefVo> strategyDetails) {
        // 非单项概率，不必要执行
//        if (Constants.StrategyMode.SINGLE.getCode().equals(strategyMode)) return;

        IDrawAlgorithm drawAlgorithm = drawAlgorithmGroup.get(strategyMode);
        // 已经初始化过了，不必重复初始化
        if (drawAlgorithm.isExistRateTuple(strategyId)) return;

        // 解析并初始化中奖概率数据到散列表
        ArrayList<AwardRateVo> awardRateVos = new ArrayList<>(strategyDetails.size());
        for (StrategyDetailBriefVo strategyDetail : strategyDetails) {
            awardRateVos.add(new AwardRateVo(strategyDetail.getAwardId(), strategyDetail.getAwardRate()));
        }
        drawAlgorithm.initRateTuple(strategyId, awardRateVos);
    }
}
