package cn.forbearance.lottery.infrastructure.dao;

import cn.forbearance.lottery.infrastructure.po.Award;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author cristina
 */
@Mapper
public interface IAwardDao {

    /**
     * 按照奖品id 查询奖品信息
     *
     * @param awardId
     * @return
     */
    Award queryAwardInfo(String awardId);
}
