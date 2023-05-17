package cn.forbearance.db.router.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 路由策略，分表标记
 *
 * 配置分表信息，配置后会通过数据库路由组件把sql语句添加上分表字段，比如表 user 修改为 user_003
 *
 * @author cristina
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DbRouterStrategy {

    /**
     * 是否分表，默认 false
     *
     * @return
     */
    boolean splitTable() default false;
}
