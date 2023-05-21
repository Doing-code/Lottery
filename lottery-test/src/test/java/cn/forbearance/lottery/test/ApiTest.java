package cn.forbearance.lottery.test;

import cn.forbearance.lottery.rpc.ILotteryActivityBooth;
import cn.forbearance.lottery.rpc.res.DrawRes;
import com.alibaba.fastjson.JSON;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author cristina
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Reference(interfaceClass = ILotteryActivityBooth.class, url = "dubbo://127.0.0.1:20880")
    private ILotteryActivityBooth activityBooth;

    @Test
    public void test_rpc() {
        ActivityReq req = new ActivityReq();
        req.setActivityId(100001L);
        DrawRes result = activityBooth.queryActivityById(req);
        logger.info("测试结果：{}", JSON.toJSONString(result));
    }

}
