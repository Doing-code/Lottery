package cn.forbearance.lottery.infrastructure.dao;

import cn.forbearance.db.router.annotation.DbRouter;
import cn.forbearance.db.router.annotation.DbRouterStrategy;
import cn.forbearance.lottery.infrastructure.po.UserStrategyExport;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author cristina
 */
@Mapper
@DbRouterStrategy(splitTable = true)
public interface IUserStrategyExportDao {

    /**
     * 新增数据
     *
     * @param userStrategyExport 用户策略
     */
    @DbRouter(key = "uId")
    void insert(UserStrategyExport userStrategyExport);

    /**
     * 查询数据
     *
     * @param uId 用户ID
     * @return 用户策略
     */
    @DbRouter
    UserStrategyExport queryUserStrategyExportByUid(String uId);

    /**
     * 更新发奖状态
     * @param userStrategyExport 发奖信息
     */
    @DbRouter
    void updateUserAwardState(UserStrategyExport userStrategyExport);

    /**
     * 更新发送MQ状态
     * @param userStrategyExport 发送消息
     */
    @DbRouter
    void updateInvoiceMqState(UserStrategyExport userStrategyExport);
}
