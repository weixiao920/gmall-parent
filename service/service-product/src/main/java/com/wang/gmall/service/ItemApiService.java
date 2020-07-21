package com.wang.gmall.service;

import com.wang.gmall.model.product.BaseCategoryView;
import com.wang.gmall.model.product.SkuInfo;
import com.wang.gmall.model.product.SpuSaleAttr;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

    /**
     * 获取价格，从数据库查，保持价格的准确性
     * @param skuId
     * @return
     */
    BigDecimal getPrice(Long skuId);

    /**
     * 获取当前商品spu对应的销售属性
     * @param skuId
     * @param spuId
     * @return
     */
    List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(Long skuId, Long spuId);

    /**
     * 获取销售属性对应的id为key,value为skuId
     * @param spuId
     * @return
     */
    List<Map<String, Object>> getSkuValueIdsMap(Long spuId);
}
