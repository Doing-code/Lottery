package cn.forbearance.db.router.strategy;

/**
 * 路由策略
 *
 * @author cristina
 */
public interface IDbRouterStrategy {

    /**
     * 路由计算
     *
     * @param dbKeyAttr 路由字段
     */
    void doRouter(String dbKeyAttr);

    /**
     * 手动设置分库路由
     *
     * @param dbIdx
     */
    void setDbKey(int dbIdx);

    /**
     * 手动设置分表路由
     *
     * @param tbIdx
     */
    void setTbKey(int tbIdx);

    /**
     * 分库数
     *
     * @return
     */
    int dbCount();

    /**
     * 分表数
     *
     * @return
     */
    int tbCount();

    /**
     * 清除路由
     */
    void clear();
}
