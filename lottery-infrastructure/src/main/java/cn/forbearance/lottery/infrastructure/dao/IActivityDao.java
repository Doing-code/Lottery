package cn.forbearance.lottery.infrastructure.dao;

import cn.forbearance.lottery.infrastructure.po.Activity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author cristina
 */
@Mapper
public interface IActivityDao {

    /**
     * #
     *
     * @param req
     */
    void insert(Activity req);

    /**
     * #
     *
     * @param activityID
     * @return
     */
    Activity queryActivityById(Long activityID);
}
