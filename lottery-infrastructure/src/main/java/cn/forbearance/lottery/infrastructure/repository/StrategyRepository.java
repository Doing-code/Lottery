package cn.forbearance.lottery.infrastructure.repository;

import cn.forbearance.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.forbearance.lottery.domain.strategy.model.vo.AwardBriefVo;
import cn.forbearance.lottery.domain.strategy.model.vo.StrategyBriefVo;
import cn.forbearance.lottery.domain.strategy.model.vo.StrategyDetailBriefVo;
import cn.forbearance.lottery.domain.strategy.repository.IStrategyRepository;
import cn.forbearance.lottery.infrastructure.dao.IAwardDao;
import cn.forbearance.lottery.infrastructure.dao.IStrategyDao;
import cn.forbearance.lottery.infrastructure.dao.IStrategyDetailDao;
import cn.forbearance.lottery.infrastructure.po.Award;
import cn.forbearance.lottery.infrastructure.po.Strategy;
import cn.forbearance.lottery.infrastructure.po.StrategyDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cristina
 */
@Component
public class StrategyRepository implements IStrategyRepository {

    @Resource
    private IStrategyDao strategyDao;

    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Resource
    private IAwardDao awardDao;

    @Override
    public StrategyRich queryStrategyRich(Long strategyId) {
        Strategy strategy = strategyDao.queryStrategy(strategyId);
        List<StrategyDetail> strategyDetailList = strategyDetailDao.queryStrategyDetailList(strategyId);

        StrategyBriefVo strategyBriefVO = new StrategyBriefVo();
        BeanUtils.copyProperties(strategy, strategyBriefVO);

        List<StrategyDetailBriefVo> strategyDetailBriefVOList = new ArrayList<>();
        for (StrategyDetail strategyDetail : strategyDetailList) {
            StrategyDetailBriefVo strategyDetailBriefVO = new StrategyDetailBriefVo();
            BeanUtils.copyProperties(strategyDetail, strategyDetailBriefVO);
            strategyDetailBriefVOList.add(strategyDetailBriefVO);
        }

        return new StrategyRich(strategyId, strategyBriefVO, strategyDetailBriefVOList);
    }

    @Override
    public AwardBriefVo queryAwardInfo(String awardId) {
        Award award = awardDao.queryAwardInfo(awardId);

        // 可以使用 BeanUtils.copyProperties(award, awardBriefVO)、或者基于ASM实现的Bean-Mapping，但在效率上最好的依旧是硬编码
        AwardBriefVo awardBriefVO = new AwardBriefVo();
        awardBriefVO.setAwardId(award.getAwardId());
        awardBriefVO.setAwardType(award.getAwardType());
        awardBriefVO.setAwardName(award.getAwardName());
        awardBriefVO.setAwardContent(award.getAwardContent());

        return awardBriefVO;
    }

    @Override
    public List<String> queryNoStockStrategyAwards(Long strategyId) {
        return strategyDetailDao.queryNoStockStrategyAwards(strategyId);
    }

    @Override
    public boolean deductStock(Long strategyId, String awardId) {
        int count = strategyDetailDao.deductStock(strategyId, awardId);
        return count == 1;
    }
}
