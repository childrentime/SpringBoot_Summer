package com.summer.service.impl;

import com.summer.dao.ProductMapper;
import com.summer.entity.Product;
import com.summer.service.ProductService;
import com.summer.util.OrderUtil;
import com.summer.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean add(Product product) {
        return productMapper.insertOne(product)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean update(Product product) {
        return productMapper.updateOne(product)>0;
    }

    @Override
    //获取分类下的产品集合
    public List<Product> getList(Product product, Byte[] product_isEnabled_array,OrderUtil orderUtil, PageUtil pageUtil) {
        return productMapper.select(product, product_isEnabled_array, orderUtil, pageUtil);
    }

    @Override
    public List<Product> getTitle(Product product, PageUtil pageUtil) {
        return productMapper.selectTitle(product, pageUtil);
    }

    @Override
    public Product get(Integer product_Id) {
        return productMapper.selectOne(product_Id);
    }

    @Override
    public Integer getTotal(Product product,Byte[] product_isEnabled_array) {
        return productMapper.selectTotal(product,product_isEnabled_array);
    }

    @Override
    public List<Product> getMoreList(Product product, Byte[] bytes, OrderUtil orderUtil, PageUtil pageUtil, String[] product_name_split) {
        return productMapper.selectMoreList(product, bytes, orderUtil, pageUtil, product_name_split);
    }

    @Override
    public Integer getMoreListTotal(Product product, Byte[] bytes, String[] product_name_split) {
        return productMapper.selectMoreListTotal(product, bytes, product_name_split);
    }
}
