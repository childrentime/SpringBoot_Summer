package com.summer.service;

import com.summer.entity.PropertyValue;
import com.summer.util.PageUtil;

import java.util.List;

public interface PropertyValueService {
    boolean add(PropertyValue propertyValue);

    boolean addList(List<PropertyValue> propertyValueList);
    boolean update(PropertyValue propertyValue);
    boolean deleteList(Integer[] propertyValue_id_list);

    List<PropertyValue> getList(PropertyValue propertyValue, PageUtil pageUtil);
    PropertyValue get(Integer propertyValue_id);
    Integer getTotal(PropertyValue propertyValue);
}
