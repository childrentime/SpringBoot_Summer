package com.summer.dao;

import com.summer.entity.Address;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //用于将数据访问层（DAO 层）的类标识为Spring Bean
public interface AddressMapper {
    //@Param传参 用括号里面的参数传给对应的变量 也即是将Address 传给xml中的#{address}
    //在数据库里面插入一条地址
    Integer insertOne(@Param("address") Address address);
    //更新一条地址
    Integer updateOne(@Param("address") Address address);
    //返回省和对应的市的集合
    List<Address> select(@Param("address_name") String address_name, @Param("address_regionId") String address_regionId);
    //根据id选择一个地址
    Address selectOne(@Param("address_areaId") String address_areaId);
    //返回省市区的集合
    List<Address> selectRoot();
}