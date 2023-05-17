package cn.forbearance.lottery.domain.activity.service.deploy.impl;

import cn.forbearance.lottery.domain.activity.model.aggregates.ActivityConfigRich;
import cn.forbearance.lottery.domain.activity.model.req.ActivityConfigReq;
import cn.forbearance.lottery.domain.activity.model.vo.ActivityVo;
import cn.forbearance.lottery.domain.activity.model.vo.AwardVo;
import cn.forbearance.lottery.domain.activity.model.vo.StrategyDetailVo;
import cn.forbearance.lottery.domain.activity.model.vo.StrategyVo;
import cn.forbearance.lottery.domain.activity.repository.IActivityRepository;
import cn.forbearance.lottery.domain.activity.service.deploy.IActivityDeploy;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部署活动配置接口
 *
 * @author cristina
 */
@Service
public class ActivityDeployImpl implements IActivityDeploy {

    private final Logger log = LoggerFactory.getLogger(ActivityDeployImpl.class);

    @Resource
    private IActivityRepository activityRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createActivity(ActivityConfigReq req) {
        log.info("创建活动配置开始，activityId：{}", req.getActivityId());
        ActivityConfigRich activityConfigRich = req.getActivityConfigRich();

        try {
            ActivityVo activity = activityConfigRich.getActivity();
            activityRepository.addActivity(activity);

            List<AwardVo> awards = activityConfigRich.getAwardList();
            activityRepository.addAward(awards);

            StrategyVo strategy = activityConfigRich.getStrategy();
            activityRepository.addStrategy(strategy);

            List<StrategyDetailVo> strategyDetails = activityConfigRich.getStrategy().getStrategyDetailList();
            activityRepository.addStrategyDetails(strategyDetails);
        } catch (DuplicateKeyException e) {
            log.error("创建活动配置失败，唯一索引冲突 activityId：{} reqJson：{}", req.getActivityId(), JSON.toJSONString(req), e);
            throw e;
        }
        log.info("创建活动配置完成，activityId：{}", req.getActivityId());
    }

    @Override
    public void updateActivity(ActivityConfigReq req) {

    }
}
