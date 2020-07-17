package com.wang.gmall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.gmall.model.product.SkuInfo;
import com.wang.gmall.model.product.SpuImage;
import com.wang.gmall.model.product.SpuSaleAttr;

import java.util.List;

/**
 * @author 微笑
 * @date 2020/7/17 14:13
 */
public interface SkuService {

    /**
     * 保存商品的sku
     * @param skuInfo
     */
    void saveSkuInfo(SkuInfo skuInfo);

    /**
     * 查询所有sku并分页
     * @param page
     * @return
     */
    IPage<SkuInfo> selectPage(Page page);

    /**
     * 商品上架
     * @param skuId
     */
    void onSale(Long skuId);

    /**
     * 商品下架
     * @param skuId
     */
    void cancelSale(Long skuId);
}
