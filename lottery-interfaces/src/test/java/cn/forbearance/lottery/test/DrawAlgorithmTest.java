package cn.forbearance.lottery.test;

import cn.forbearance.lottery.common.Constants;
import cn.forbearance.lottery.domain.award.model.req.GoodsReq;
import cn.forbearance.lottery.domain.award.model.res.DistributionRes;
import cn.forbearance.lottery.domain.award.service.factory.DistributionGoodsFactory;
import cn.forbearance.lottery.domain.award.service.goods.IDistributionGoods;
import cn.forbearance.lottery.domain.strategy.model.req.DrawReq;
import cn.forbearance.lottery.domain.strategy.model.res.DrawResult;
import cn.forbearance.lottery.domain.strategy.model.vo.AwardRateVo;
import cn.forbearance.lottery.domain.strategy.model.vo.DrawAwardVo;
import cn.forbearance.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import cn.forbearance.lottery.domain.strategy.service.draw.IDrawExec;
import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cristina
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DrawAlgorithmTest {

    protected Logger log = LoggerFactory.getLogger(DrawAlgorithmTest.class);

    @Resource(name = "singleRateRandomDrawAlgorithm")
    private IDrawAlgorithm randomDrawAlgorithm;

    @Resource
    private DistributionGoodsFactory distributionGoodsFactory;


    @Before
    public void init() {
        // 奖品信息
        List<AwardRateVo> strategyList = new ArrayList<>();
        strategyList.add(new AwardRateVo("一等奖：IMac", new BigDecimal("0.05")));
        strategyList.add(new AwardRateVo("二等奖：iphone", new BigDecimal("0.15")));
        strategyList.add(new AwardRateVo("三等奖：ipad", new BigDecimal("0.20")));
        strategyList.add(new AwardRateVo("四等奖：AirPods", new BigDecimal("0.25")));
        strategyList.add(new AwardRateVo("五等奖：充电宝", new BigDecimal("0.35")));

        // 初始数据
        randomDrawAlgorithm.initRateTuple(100001L, strategyList);
    }

    @Test
    public void test_randomDrawAlgorithm() {

        List<String> excludeAwardIds = new ArrayList<>();
        excludeAwardIds.add("二等奖：iphone");
        excludeAwardIds.add("四等奖：AirPods");

        for (int i = 0; i < 20; i++) {
            System.out.println("中奖结果：" + randomDrawAlgorithm.randomDraw(100001L, excludeAwardIds));
        }

    }

    @Resource
    private IDrawExec drawExec;


    @Test
    public void test_drawExec() {
        drawExec.doDrawExec(new DrawReq("小傅哥", 10001L));
        drawExec.doDrawExec(new DrawReq("小佳佳", 10001L));
        drawExec.doDrawExec(new DrawReq("小蜗牛", 10001L));
        drawExec.doDrawExec(new DrawReq("八杯水", 10001L));
    }


}
