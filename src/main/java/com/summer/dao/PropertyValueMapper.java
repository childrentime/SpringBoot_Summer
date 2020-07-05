package com.summer.dao;

import com.summer.entity.PropertyValue;
import com.summer.util.PageUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PropertyValueMapper {
    Integer insertOne(@Param("propertyValue") PropertyValue propertyValue);

    Integer insertList(@Param("propertyValue_list") List<PropertyValue> propertyValueList);
    Integer updateOne(@Param("propertyValue") PropertyValue propertyValue);
    Integer deleteList(@Param("propertyValue_id_list") Integer[] propertyValue_id_list);

    List<PropertyValue> select(@Param("propertyValue") PropertyValue propertyValue, @Param("pageUtil") PageUtil pageUtil);
    PropertyValue selectOne(@Param("propertyValue_id") Integer propertyValue_id);
    Integer selectTotal(@Param("propertyValue") PropertyValue propertyValue);
}
