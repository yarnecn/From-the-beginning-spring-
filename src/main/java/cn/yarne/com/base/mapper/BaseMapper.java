package cn.yarne.com.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.yarne.com.base.model.SysUser;

public interface BaseMapper<T, V> {

    long countByExample(V example);

    int deleteByExample(V example);

    int deleteByPrimaryKey(Integer id);

    int insert(T record);

    int insertSelective(T record);

    List<T> selectByExample(V example);

    T selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") T record, @Param("example") V example);

    int updateByExample(@Param("record") T record, @Param("example") V example);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
