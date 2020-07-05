package com.summer.service;

import com.summer.entity.Category;
import com.summer.util.PageUtil;

import java.util.List;

public interface CategoryService {
    boolean add(Category category);
    boolean update(Category category);
    //获取产品分类列表
    List<Category> getList(String category_name, PageUtil pageUtil);
    Category get(Integer category_id);
    Integer getTotal(String category_name);
}
