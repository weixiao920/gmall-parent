package com.wang.gmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wang.gmall.mapper.BaseCategory1Mapper;
import com.wang.gmall.mapper.BaseCategory2Mapper;
import com.wang.gmall.mapper.BaseCategory3Mapper;
import com.wang.gmall.model.product.BaseCategory1;
import com.wang.gmall.model.product.BaseCategory2;
import com.wang.gmall.model.product.BaseCategory3;
import com.wang.gmall.service.BaseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 微笑
 * @date 2020/7/14 11:49
 */
@Service
public class BaseCategoryServiceImpl implements BaseCategoryService {

    @Autowired
    private BaseCategory1Mapper category1Mapper;

    @Autowired
    private BaseCategory2Mapper category2Mapper;

    @Autowired
    private BaseCategory3Mapper category3Mapper;


    @Override
    public List<BaseCategory1> getCategory1() {
        //获取一级分类
        return category1Mapper.selectList(null);
    }

    @Override
    public List<BaseCategory2> getCategory2(String category1Id) {
        QueryWrapper<BaseCategory2> category2QueryWrapper = new QueryWrapper<>();
        category2QueryWrapper.eq("category1_id",category1Id);
        //获取二级分类
        return category2Mapper.selectList(category2QueryWrapper);
    }

    @Override
    public List<BaseCategory3> getCategory3(String category2Id) {
        QueryWrapper<BaseCategory3> category3QueryWrapper = new QueryWrapper<>();
        category3QueryWrapper.eq("category2_id",category2Id);
        //获取三级分类
        return category3Mapper.selectList(category3QueryWrapper);
    }
}
