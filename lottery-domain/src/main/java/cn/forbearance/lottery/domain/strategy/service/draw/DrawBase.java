package cn.forbearance.lottery.domain.strategy.service.draw;

import cn.forbearance.lottery.domain.strategy.model.vo.AwardRateInfo;
import cn.forbearance.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import cn.forbearance.lottery.infrastructure.po.StrategyDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cristina
 */
public class DrawBase extends DrawConfig{

    public void checkAndInitRateTuple(Long strategyId, Integer strategyMode, List<StrategyDetail> strategyDetails) {
//        if (strategyMode != 2) return ;

        IDrawAlgorithm drawAlgorithm = drawAlgorithms.get(strategyMode);
        boolean existRateTuple = drawAlgorithm.isExistRateTuple(strategyId);
        if (existRateTuple) return;

        ArrayList<AwardRateInfo> awardRateInfos = new ArrayList<>(strategyDetails.size());
        for (StrategyDetail strategyDetail : strategyDetails) {
            awardRateInfos.add(new AwardRateInfo(strategyDetail.getAwardId(), strategyDetail.getAwardRate()));
        }
        drawAlgorithm.initRateTuple(strategyId, awardRateInfos);
    }
}
