package com.summer.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface LastIDMapper {
    int selectLastID();
}
