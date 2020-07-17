package com.wang.gmall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.gmall.model.product.*;

import java.util.List;

/**
 * @author 微笑
 * @date 2020/7/14 18:35
 */
public interface SpuService {

    /**
     * 获取spu并且分页展示
     * @param infoPage
     * @param category3Id
     * @return
     */
    IPage<SpuInfo> getSpuPage(Page infoPage, String category3Id);

    /**
     * 获取销售属性
     * @return
     */
    List<BaseSaleAttr> getBaseSaleAttrList();

    /**
     * 获取品牌属性
     * @return
     */
    List<BaseTrademark> getTrademarkList();

    /**
     * 保存spuInfo
     * @param spuInfo
     */
    void saveSpuInfo(SpuInfo spuInfo);

    /**
     * 获取spuId的销售属性列表
     * @param spuId
     * @return
     */
    List<SpuSaleAttr> getSpuSaleAttrList(Long spuId);

    /**
     * 获取spuId的图片集合
     * @param spuId
     * @return
     */
    List<SpuImage> getSpuImageList(Long spuId);
}
