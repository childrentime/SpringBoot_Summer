package com.summer.service.impl;

import com.summer.dao.ProductImageMapper;
import com.summer.entity.ProductImage;
import com.summer.service.ProductImageService;
import com.summer.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService{
    @Autowired
    private ProductImageMapper productImageMapper;

    public void setProductImageMapper(ProductImageMapper productImageMapper) {
        this.productImageMapper = productImageMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean add(ProductImage productImage) {
        return productImageMapper.insertOne(productImage)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean addList(List<ProductImage> productImageList) {
        return productImageMapper.insertList(productImageList) > 0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean update(ProductImage productImage) {
        return productImageMapper.updateOne(productImage)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean deleteList(Integer[] productImage_id_list) {
        return productImageMapper.deleteList(productImage_id_list)>0;
    }

    @Override
    //根据id和type返回商品图片集合
    public List<ProductImage> getList(Integer product_id, Byte productImage_type, PageUtil pageUtil) {
        return productImageMapper.select(product_id,productImage_type,pageUtil);
    }

    @Override
    public ProductImage get(Integer productImage_id) {
        return productImageMapper.selectOne(productImage_id);
    }

    @Override
    public Integer getTotal(Integer product_id, Byte productImage_type) {
        return productImageMapper.selectTotal(product_id,productImage_type);
    }
}
