package com.wang.gmall.controller;

import com.wang.gmall.model.product.BaseCategoryView;
import com.wang.gmall.model.product.SkuInfo;
import com.wang.gmall.model.product.SpuSaleAttr;
import com.wang.gmall.service.ItemApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author 微笑
 * @date 2020/7/19 9:35
 */
@RestController("service-product")
@RequestMapping("api/product")
public class ItemApiController {

    @Autowired
    private ItemApiService itemApiService;

    @GetMapping("inner/getSkuInfo/{skuId}")
    public SkuInfo getSkuInfo(@PathVariable Long skuId){
        //获取商品sku信息
        SkuInfo skuInfo = itemApiService.getSkuInfo(skuId);
        return skuInfo;
    }

    @GetMapping("inner/getCategoryView/{category3Id}")
    public BaseCategoryView getCategoryView(@PathVariable Long category3Id){
        //获取该商品隶属于哪个分类
        BaseCategoryView categoryView = itemApiService.getCategoryView(category3Id);
        return categoryView;
    }

    @GetMapping("inner/getPrice/{skuId}")
    public BigDecimal getPrice(@PathVariable  Long skuId){
        //从数据库获取价格信息，实时从数据库查，保存准确性
        return itemApiService.getPrice(skuId);
    }

    @GetMapping("inner/getSpuSaleAttrListCheckBySku/{skuId}/{spuId}")
    List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(@PathVariable Long skuId, @PathVariable Long spuId){
        //获取当前商品spu对应的销售属性
        return itemApiService.getSpuSaleAttrListCheckBySku(skuId,spuId);
    }

    @GetMapping("inner/getSkuValueIdsMap/{spuId}")
    List<Map<String, Object>> getSkuValueIdsMap(@PathVariable Long spuId){
        //获取销售属性对应的id为key,value为skuId
        return itemApiService.getSkuValueIdsMap(spuId);
    }
}
