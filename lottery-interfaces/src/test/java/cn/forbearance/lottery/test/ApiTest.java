package cn.forbearance.lottery.test;

import cn.forbearance.lottery.application.process.IActivityProcess;
import cn.forbearance.lottery.application.process.req.DrawProcessReq;
import cn.forbearance.lottery.application.process.res.DrawProcessResult;
import cn.forbearance.lottery.domain.strategy.model.req.DrawReq;
import cn.forbearance.lottery.domain.strategy.service.draw.IDrawExec;
import cn.forbearance.lottery.infrastructure.dao.IActivityDao;
import cn.forbearance.lottery.infrastructure.po.Activity;
import cn.forbearance.lottery.rpc.IActivityBooth;
import cn.forbearance.lottery.rpc.req.ActivityReq;
import cn.forbearance.lottery.rpc.res.ActivityRes;
import com.alibaba.fastjson.JSON;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author cristina
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Resource
    IActivityDao activityDao;

    @Resource
    private IDrawExec drawExec;

    @Test
    public void test_drawExec() {
        drawExec.doDrawExec(new DrawReq("1", 10001L));
        drawExec.doDrawExec(new DrawReq("2", 10001L));
        drawExec.doDrawExec(new DrawReq("3", 10001L));
        drawExec.doDrawExec(new DrawReq("4", 10001L));
    }


    @Test
    public void test_insert() {
        Activity activity = new Activity();
        activity.setActivityId(100001L);
        activity.setActivityName("测试活动");
        activity.setActivityDesc("仅用于插入数据测试");
        activity.setBeginDateTime(new Date());
        activity.setEndDateTime(new Date());
        activity.setStockCount(100);
        activity.setTakeCount(10);
        activity.setState(0);
        activity.setCreator("forbearance");
        activityDao.insert(activity);
    }

    @Test
    public void test_select() {
        Activity activity = activityDao.queryActivityById(100001L);
        logger.info("测试结果：{}", JSON.toJSONString(activity));
    }

    @Reference(interfaceClass = IActivityBooth.class, url = "dubbo://127.0.0.1:20880")
    private IActivityBooth activityBooth;

    @Test
    public void test_rpc() {
        ActivityReq req = new ActivityReq();
        req.setActivityId(100001L);
        ActivityRes result = activityBooth.queryActivityById(req);
        logger.info("测试结果：{}", JSON.toJSONString(result));
    }

    @Resource
    private IActivityProcess activityProcess;

    @Test
    public void test_doDrawProcess() {
        DrawProcessReq req = new DrawProcessReq();
        req.setuId("fustack");
        req.setActivityId(100001L);
        DrawProcessResult drawProcessResult = activityProcess.doDrawProcess(req);

        logger.info("请求入参：{}", JSON.toJSONString(req));
        logger.info("测试结果：{}", JSON.toJSONString(drawProcessResult));
    }


}
