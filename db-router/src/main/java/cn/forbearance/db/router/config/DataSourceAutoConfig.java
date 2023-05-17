package cn.forbearance.db.router.config;

import cn.forbearance.db.router.DbRouterConfig;
import cn.forbearance.db.router.DbRouterJoinPoint;
import cn.forbearance.db.router.dynamic.DynamicDataSource;
import cn.forbearance.db.router.dynamic.DynamicPlugin;
import cn.forbearance.db.router.strategy.IDbRouterStrategy;
import cn.forbearance.db.router.strategy.impl.DbRouterStrategyHashCode;
import cn.forbearance.db.router.utils.PropertyUtil;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 数据源配置解析
 *
 * @author cristina
 */
@Configuration
public class DataSourceAutoConfig implements EnvironmentAware {

    private final String prefix = "mini-db-router.jdbc.datasource.";

    private final String dataSourcePrefix = ",";

    /**
     * 数据源组
     */
    private Map<String, Map<String, Object>> dataSourceGroup = new HashMap<>();

    /**
     * 默认数据源
     */
    private Map<String, Object> defaultDataSource;

    /**
     * 分库数量
     */
    private int dbCount;

    /**
     * 分表数量
     */
    private int tbCount;

    /**
     * 路由字段
     */
    private String routerKey;

    @Bean(name = "db-router-point")
    @ConditionalOnMissingBean
    public DbRouterJoinPoint point(DbRouterConfig dbRouterConfig, IDbRouterStrategy dbRouterStrategy) {
        return new DbRouterJoinPoint(dbRouterConfig, dbRouterStrategy);
    }

    @Bean
    public DbRouterConfig dbRouterConfig() {
        return new DbRouterConfig(dbCount, tbCount, routerKey);
    }

    @Bean
    public Interceptor plugin() {
        return new DynamicPlugin();
    }

    /**
     * 将整个项目配置的所有数据库设置到 AbstractRoutingDataSource
     *
     * @return
     */
    @Bean
    public DataSource dataSource() {
        // 创建数据源
        Map<Object, Object> targetDataSources = new HashMap<>();
        for (String dbInfo : dataSourceGroup.keySet()) {
            Map<String, Object> objMap = dataSourceGroup.get(dbInfo);
            targetDataSources.put(dbInfo, new DriverManagerDataSource(objMap.get("url").toString(),
                    objMap.get("username").toString(),
                    objMap.get("password").toString()));
        }

        // 设置数据源
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(new DriverManagerDataSource(defaultDataSource.get("url").toString(),
                defaultDataSource.get("username").toString(),
                defaultDataSource.get("password").toString()));

        return dynamicDataSource;
    }

    @Bean
    public IDbRouterStrategy dbRouterStrategy(DbRouterConfig dbRouterConfig) {
        return new DbRouterStrategyHashCode(dbRouterConfig);
    }

    @Bean
    public TransactionTemplate transactionTemplate(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);

        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(dataSourceTransactionManager);
        transactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRED");
        return transactionTemplate;
    }

    @Override
    public void setEnvironment(Environment environment) {
        dbCount = Integer.parseInt(Objects.requireNonNull(environment.getProperty(prefix + "dbCount")));
        tbCount = Integer.parseInt(Objects.requireNonNull(environment.getProperty(prefix + "tbCount")));
        routerKey = environment.getProperty(prefix + "routerKey");

        // 分库分表数据源
        String dataSources = environment.getProperty(prefix + "list");
        assert dataSources != null;
        for (String dbInfo : dataSources.split(dataSourcePrefix)) {
            Map<String, Object> dataSourceProps = PropertyUtil.handle(environment, prefix + dbInfo, Map.class);
            dataSourceGroup.put(dbInfo, dataSourceProps);
        }

        // 默认数据源
        String defaultData = environment.getProperty(prefix + "default");
        defaultDataSource = PropertyUtil.handle(environment, prefix + defaultData, Map.class);
    }
}
