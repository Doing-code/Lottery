package cn.forbearance.lottery.test;

import cn.forbearance.lottery.common.Constants;
import cn.forbearance.lottery.domain.support.ids.IdGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author cristina
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IdTest {

    private Logger logger = LoggerFactory.getLogger(IdTest.class);

    @Resource
    private Map<Constants.Ids, IdGenerator> idGenerators;

    @Test
    public void test_ids() {
        logger.info("雪花算法策略，生成ID：{}", idGenerators.get(Constants.Ids.SnowFlake).nextId());
        logger.info("雪花算法策略，生成ID：{}", idGenerators.get(Constants.Ids.SnowFlake).nextId());

        logger.info("日期算法策略，生成ID：{}", idGenerators.get(Constants.Ids.ShortCode).nextId());
        logger.info("日期算法策略，生成ID：{}", idGenerators.get(Constants.Ids.ShortCode).nextId());

        logger.info("随机算法策略，生成ID：{}", idGenerators.get(Constants.Ids.RandomNumeric).nextId());
        logger.info("随机算法策略，生成ID：{}", idGenerators.get(Constants.Ids.RandomNumeric).nextId());
    }

}
