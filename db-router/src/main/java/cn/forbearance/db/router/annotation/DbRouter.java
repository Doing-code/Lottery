package cn.forbearance.db.router.annotation;

import cn.forbearance.db.router.strategy.impl.DbRouterStrategyHashCode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 路由注解
 * <p>
 * '@DBRouter' 未配置情况下走默认字段，routerKey: uId
 *
 * @author cristina
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DbRouter {

    /**
     * 分库分表字段 {@link DbRouterStrategyHashCode#doRouter}
     * <p>
     * 设置路由字段
     * <p>
     * 即按照指定POJO字段进行 hash（使用哈希散列+扰动函数的方式）
     */
    String key() default "";
}
