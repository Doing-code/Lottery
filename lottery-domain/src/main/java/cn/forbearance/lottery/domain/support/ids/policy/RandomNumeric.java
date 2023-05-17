package cn.forbearance.lottery.domain.support.ids.policy;

import cn.forbearance.lottery.domain.support.ids.IdGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

/**
 * @author cristina
 */
@Component
public class RandomNumeric implements IdGenerator {

    @Override
    public long nextId() {
        return Long.parseLong(RandomStringUtils.randomNumeric(11));
    }
}
