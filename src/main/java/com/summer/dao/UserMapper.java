package com.summer.dao;

import com.summer.entity.User;
import com.summer.util.OrderUtil;
import com.summer.util.PageUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    Integer insertOne(@Param("user") User user);
    Integer updateOne(@Param("user") User user);

    List<User> select(@Param("user") User user, @Param("orderUtil") OrderUtil orderUtil, @Param("pageUtil") PageUtil pageUtil);
    User selectOne(@Param("user_id") Integer user_id);
    User selectByLogin(@Param("user_name") String user_name, @Param("user_password") String user_password);
    Integer selectTotal(@Param("user") User user);
}
