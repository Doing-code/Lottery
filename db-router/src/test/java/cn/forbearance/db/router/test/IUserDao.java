package cn.forbearance.db.router.test;

import cn.forbearance.db.router.annotation.DbRouter;

/**
 * @author cristina
 */
public interface IUserDao {

    @DbRouter(key = "userId")
    void insertUser(String req);

}
