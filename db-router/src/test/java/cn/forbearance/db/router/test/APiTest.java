package cn.forbearance.db.router.test;

import cn.forbearance.db.router.annotation.DbRouter;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author cristina
 */
public class APiTest {

    @Test
    public void test_db_hash() {
        String key = "forbearance";

        int dbCount = 2, tbCount = 4;
        int size = dbCount * tbCount;
        // 散列
        int idx = (size - 1) & (key.hashCode() ^ (key.hashCode() >>> 16));

        int dbIdx = idx / tbCount + 1;
        int tbIdx = idx - tbCount * (dbIdx - 1);

        System.out.println(dbIdx);
        System.out.println(tbIdx);

    }

    @Test
    public void test_str_format() {
        System.out.println(String.format("db%02d", 1));
        System.out.println(String.format("_%02d", 25));
    }

    @Test
    public void test_annotation() throws NoSuchMethodException {
        Class<IUserDao> iUserDaoClass = IUserDao.class;
        Method method = iUserDaoClass.getMethod("insertUser", String.class);

        DbRouter dbRouter = method.getAnnotation(DbRouter.class);

        System.out.println(dbRouter.key());

    }

}
