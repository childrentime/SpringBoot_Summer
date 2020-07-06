package com.summer.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface LastIDMapper {
    //SELECT LAST_INSERT_ID() 即为获取最后插入的ID值
    int selectLastID();
}
