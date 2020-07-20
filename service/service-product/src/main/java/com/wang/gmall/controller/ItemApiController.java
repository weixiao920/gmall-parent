package com.wang.gmall.controller;

import com.wang.gmall.model.product.BaseCategoryView;
import com.wang.gmall.model.product.SkuInfo;
import com.wang.gmall.service.ItemApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
