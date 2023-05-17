package cn.forbearance.db.router;

/**
 * 数据源基类
 *
 * @author cristina
 */
public class DbRouterBase {

    private String tbIdx;

    public String getTbIdx() {
        return DbContextHolder.getTBKey();
    }
}
