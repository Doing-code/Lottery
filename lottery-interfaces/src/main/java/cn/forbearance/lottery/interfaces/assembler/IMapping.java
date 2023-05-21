package cn.forbearance.lottery.interfaces.assembler;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Stream;

/**
 * MapStruct 对象映射接口
 *
 * @author cristina
 */
@MapperConfig
public interface IMapping<S, T> {

    /**
     * 映射同名属性
     *
     * @param var1 源
     * @return 结果
     */
    @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    T sourceToTarget(S var1);

    /**
     * 映射同名属性，反向
     *
     * @param var1 源
     * @return 结果
     */
    @InheritInverseConfiguration(name = "sourceToTarget")
    S targetToSource(T var1);

    /**
     * 映射同名属性，集合形式
     *
     * @param var1 源
     * @return 结果
     */
    @InheritConfiguration(name = "sourceToTarget")
    List<T> sourceToTarget(List<S> var1);

    /**
     * 反向，映射同名属性，集合形式
     *
     * @param var1 源
     * @return 结果
     */
    @InheritConfiguration(name = "targetToSource")
    List<S> targetToSource(List<T> var1);

    /**
     * 映射同名属性，集合流形式
     *
     * @param stream 源
     * @return 结果
     */
    List<T> sourceToTarget(Stream<S> stream);

    /**
     * 反向，映射同名属性，集合流形式
     *
     * @param stream 源
     * @return 结果
     */
    List<S> targetToSource(Stream<T> stream);
}
