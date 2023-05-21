package cn.forbearance.lottery.interfaces.assembler;

import cn.forbearance.lottery.domain.strategy.model.vo.DrawAwardVo;
import cn.forbearance.lottery.rpc.dto.AwardDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * 对象转换配置
 *
 * @author cristina
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AwardMapping extends IMapping<DrawAwardVo, AwardDto> {

    /**
     * #
     *
     * @param var1 源
     * @return
     */
    @Mapping(target = "userId", source = "uId")
    @Override
    AwardDto sourceToTarget(DrawAwardVo var1);

    /**
     * #
     *
     * @param var1 源
     * @return
     */
    @Override
    DrawAwardVo targetToSource(AwardDto var1);
}
