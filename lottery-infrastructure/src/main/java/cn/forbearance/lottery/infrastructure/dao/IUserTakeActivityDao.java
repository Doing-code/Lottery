package cn.forbearance.lottery.infrastructure.dao;

import cn.forbearance.db.router.annotation.DbRouter;
import cn.forbearance.lottery.infrastructure.po.UserTakeActivity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author cristina
 */
@Mapper
public interface IUserTakeActivityDao {

    /**
     * 插入用户领取活动信息
     *
     * @param userTakeActivity 入参
     */
    @DbRouter(key = "uId")
    void insert(UserTakeActivity userTakeActivity);

}
