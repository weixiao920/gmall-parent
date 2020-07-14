package com.wang.gmall.service;

import com.wang.gmall.model.product.BaseCategory1;
import com.wang.gmall.model.product.BaseCategory2;
import com.wang.gmall.model.product.BaseCategory3;

import java.util.List;

/**
 * @author 微笑
 * @date 2020/7/14 11:48
 */
public interface BaseCategoryService {

    /**
     * 获取商品的一级分类
     * @return
     */
    List<BaseCategory1> getCategory1();

    /**
     * 根据一级分类的id获取二级分类
     * @param category1Id
     * @return
     */
    List<BaseCategory2> getCategory2(String category1Id);

    /**
     * 根据二级分类的id获取二级分类
     * @param category2Id
     * @return
     */
    List<BaseCategory3> getCategory3(String category2Id);
}
