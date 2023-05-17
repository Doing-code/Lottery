package cn.forbearance.db.router.dynamic;

import cn.forbearance.db.router.DbContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源获取，每当切换数据源，都要从这个里面进行获取
 *
 * <pre>
 *     AbstractRoutingDataSource 实现多数据源切换的核心逻辑是：在程序运行时通过AOP切面动态
 *          切换当前线程绑定的数据源的对象，即通过数据库事务上下文来实现的；
 *     三个核心方法：
 *          setTargetDataSources：
 *                 设置整个项目配置的所有数据库，key 是动态切换的唯一标识，value是数据源对象
 *          setDefaultTargetDataSource：默认数据源对象
 *          determineCurrentLookupKey：获取要使用数据源的 key
*      determineTargetDataSource：获取当前线程对应的数据源
 * </pre>
 *
 * @author cristina
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return "db" + DbContextHolder.getDBKey();
    }
}
