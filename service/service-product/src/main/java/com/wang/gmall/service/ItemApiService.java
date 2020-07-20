package com.wang.gmall.service;

import com.wang.gmall.model.product.BaseCategoryView;
import com.wang.gmall.model.product.SkuInfo;

/**
 * @author 微笑
 * @date 2020/7/19 9:40
 */
public interface ItemApiService {

    /**
     * 获取商品sku信息
     * @param skuId
     * @return
     */
    SkuInfo getSkuInfo(Long skuId);

    /**
     * 通过创建好的视图获取分类信息
     * @param category3Id
     * @return
     */
    BaseCategoryView getCategoryView(Long category3Id);
}
