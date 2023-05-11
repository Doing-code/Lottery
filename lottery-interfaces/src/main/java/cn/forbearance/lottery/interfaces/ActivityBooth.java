package cn.forbearance.lottery.interfaces;

import cn.forbearance.lottery.common.Constants;
import cn.forbearance.lottery.common.Result;
import cn.forbearance.lottery.infrastructure.dao.IActivityDao;
import cn.forbearance.lottery.infrastructure.po.Activity;
import cn.forbearance.lottery.rpc.IActivityBooth;
import cn.forbearance.lottery.rpc.dto.ActivityDto;
import cn.forbearance.lottery.rpc.req.ActivityReq;
import cn.forbearance.lottery.rpc.res.ActivityRes;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * @author cristina
 */
@Service
public class ActivityBooth implements IActivityBooth {

    @Resource
    private IActivityDao activityDao;

    @Override
    public ActivityRes queryActivityById(ActivityReq req) {
        Activity activity = activityDao.queryActivityById(req.getActivityId());

        ActivityDto activityDto = new ActivityDto();
        activityDto.setActivityId(activity.getActivityId());
        activityDto.setActivityName(activity.getActivityName());
        activityDto.setActivityDesc(activity.getActivityDesc());
        activityDto.setBeginDateTime(activity.getBeginDateTime());
        activityDto.setEndDateTime(activity.getEndDateTime());
        activityDto.setStockCount(activity.getStockCount());
        activityDto.setTakeCount(activity.getTakeCount());

        return new ActivityRes(new Result(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo()), activityDto);

    }
}
