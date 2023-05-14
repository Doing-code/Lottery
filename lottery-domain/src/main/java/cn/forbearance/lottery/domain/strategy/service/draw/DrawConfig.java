package cn.forbearance.lottery.domain.strategy.service.draw;

import cn.forbearance.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cristina
 */
public class DrawConfig {

    @Resource
    private IDrawAlgorithm defaultRateRandomDrawAlgorithm;

    @Resource
    private IDrawAlgorithm singleRateRandomDrawAlgorithm;

    protected static Map<Integer, IDrawAlgorithm> drawAlgorithms = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        drawAlgorithms.put(1, defaultRateRandomDrawAlgorithm);
        drawAlgorithms.put(2, singleRateRandomDrawAlgorithm);
    }
}
