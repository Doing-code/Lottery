package cn.forbearance.lottery.infrastructure.repository;

import cn.forbearance.lottery.common.Constants;
import cn.forbearance.lottery.domain.activity.model.vo.*;
import cn.forbearance.lottery.domain.activity.repository.IActivityRepository;
import cn.forbearance.lottery.infrastructure.dao.IActivityDao;
import cn.forbearance.lottery.infrastructure.dao.IAwardDao;
import cn.forbearance.lottery.infrastructure.dao.IStrategyDao;
import cn.forbearance.lottery.infrastructure.dao.IStrategyDetailDao;
import cn.forbearance.lottery.infrastructure.po.Activity;
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
public class ActivityRepository implements IActivityRepository {

    @Resource
    private IActivityDao activityDao;
    @Resource
    private IAwardDao awardDao;
    @Resource
    private IStrategyDao strategyDao;
    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Override
    public void addActivity(ActivityVo activity) {
        Activity req = new Activity();
        BeanUtils.copyProperties(activity, req);
        activityDao.insert(req);
    }

    @Override
    public void addAward(List<AwardVo> awardList) {
        List<Award> req = new ArrayList<>();
        for (AwardVo awardVO : awardList) {
            Award award = new Award();
            BeanUtils.copyProperties(awardVO, award);
            req.add(award);
        }
        awardDao.insertBatch(req);
    }

    @Override
    public void addStrategy(StrategyVo strategy) {
        Strategy req = new Strategy();
        BeanUtils.copyProperties(strategy, req);
        strategyDao.insert(req);
    }

    @Override
    public void addStrategyDetails(List<StrategyDetailVo> strategyDetailList) {
        List<StrategyDetail> req = new ArrayList<>();
        for (StrategyDetailVo strategyDetailVO : strategyDetailList) {
            StrategyDetail strategyDetail = new StrategyDetail();
            BeanUtils.copyProperties(strategyDetailVO, strategyDetail);
            req.add(strategyDetail);
        }
        strategyDetailDao.insertBatch(req);
    }

    @Override
    public boolean alterStatus(Long activityId, Enum<Constants.ActivityState> beforeState, Enum<Constants.ActivityState> afterState) {
        AlterStateVo alterStateVO = new AlterStateVo(activityId, ((Constants.ActivityState) beforeState).getCode(), ((Constants.ActivityState) afterState).getCode());
        int count = activityDao.alterState(alterStateVO);
        return 1 == count;
    }
}
