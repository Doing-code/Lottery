package cn.forbearance.db.router.strategy.impl;

import cn.forbearance.db.router.DbContextHolder;
import cn.forbearance.db.router.DbRouterConfig;
import cn.forbearance.db.router.strategy.IDbRouterStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 哈希路由
 * <p>
 * 通过 @DbRouter 指定POJO字段进行哈希散列得到 idx
 * <p>
 * 通过 idx 计算出需要保存到那个库和哪张表，并设置到当前线程
 *
 * @author cristina
 */
public class DbRouterStrategyHashCode implements IDbRouterStrategy {

    private final Logger log = LoggerFactory.getLogger(DbRouterStrategyHashCode.class);

    private final DbRouterConfig dbRouterConfig;

    public DbRouterStrategyHashCode(DbRouterConfig dbRouterConfig) {
        this.dbRouterConfig = dbRouterConfig;
    }

    @Override
    public void doRouter(String dbKeyAttr) {
        int size = dbCount() * tbCount();

        // 扰动函数；在 JDK 的 HashMap 中，对于一个元素的存放，需要进行哈希散列。而为了让散列更加均匀，所以添加了扰动函数
        int idx = (size - 1) & (dbKeyAttr.hashCode() ^ (dbKeyAttr.hashCode() >>> 16));

        // 库表索引；相当于是把一个长条的桶，切割成段，对应分库分表中的库编号和表编号
        int dbIdx = idx / dbRouterConfig.getTbCount() + 1;
        int tbIdx = idx - dbRouterConfig.getTbCount() * (dbIdx - 1);

        // 设置到 ThreadLocal
        setDbKey(dbIdx);
        setTbKey(tbIdx);

        log.info("数据库路由 dbIdx：{} tbIdx：{}", dbIdx, tbIdx);
    }

    @Override
    public void setDbKey(int dbIdx) {
        DbContextHolder.setDBKey(String.format("%02d", dbIdx));
    }

    @Override
    public void setTbKey(int tbIdx) {
        DbContextHolder.setTBKey(String.format("%03d", tbIdx));
    }

    @Override
    public int dbCount() {
        return dbRouterConfig.getDbCount();
    }

    @Override
    public int tbCount() {
        return dbRouterConfig.getTbCount();
    }

    @Override
    public void clear() {
        DbContextHolder.clearDBKey();
        DbContextHolder.clearTBKey();
    }
}
