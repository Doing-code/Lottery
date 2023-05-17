package cn.forbearance.lottery.infrastructure.dao;

import cn.forbearance.lottery.domain.activity.model.vo.AlterStateVo;
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

    /**
     * 变更活动状态
     *
     * @param alterStateVO
     * @return
     */
    int alterState(AlterStateVo alterStateVO);

    /**
     * 扣减活动库存
     * @param activityId 活动ID
     * @return 更新数量
     */
    int subtractionActivityStock(Long activityId);

}
