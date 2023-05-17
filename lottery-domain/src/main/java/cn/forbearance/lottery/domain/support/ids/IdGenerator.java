package cn.forbearance.lottery.domain.support.ids;

/**
 * @author cristina
 */
public interface IdGenerator {

    /**
     * 获取 id，基于雪花算法
     * 1、雪花算法：用于生成订单号
     * 2、日期算法：用于生成活动编号，数字串较短，但指定时间内不能生成太多
     * 3、随机算法：用于生成策略id
     *
     * @return
     */
    long nextId();
}
