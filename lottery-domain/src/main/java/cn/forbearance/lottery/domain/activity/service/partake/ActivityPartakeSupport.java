package cn.forbearance.lottery.domain.activity.service.partake;

import cn.forbearance.lottery.domain.activity.model.req.PartakeReq;
import cn.forbearance.lottery.domain.activity.model.vo.ActivityBillVo;
import cn.forbearance.lottery.domain.activity.repository.IActivityRepository;

import javax.annotation.Resource;

/**
 * 通用数据服务
 * @author cristina
 */
public class ActivityPartakeSupport {

    @Resource
    protected IActivityRepository activityRepository;

    protected ActivityBillVo queryActivityBill(PartakeReq req){
        return activityRepository.queryActivityBill(req);
    }
}
