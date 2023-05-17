package cn.forbearance.lottery.domain.support.ids.policy;

import cn.forbearance.lottery.domain.support.ids.IdGenerator;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author cristina
 */
@Component
public class SnowFlake implements IdGenerator, InitializingBean {

    private Snowflake snowflake;

    @Override
    public long nextId() {
        return snowflake.nextId();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 0 ~ 31 位，可以采用配置的方式使用
        long workerId;
        try {
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        } catch (Exception e) {
            workerId = NetUtil.getLocalhostStr().hashCode();
        }

        workerId = workerId >> 16 & 31;

        long dataCenterId = 1L;
        snowflake = IdUtil.getSnowflake(workerId, dataCenterId);
    }
}
