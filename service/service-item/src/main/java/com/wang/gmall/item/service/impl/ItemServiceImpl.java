package com.wang.gmall.item.service.impl;

import com.wang.gmall.item.service.ItemService;
import com.wang.gmall.model.product.BaseCategoryView;
import com.wang.gmall.model.product.SkuInfo;
import com.wang.gmall.product.client.ProductFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 微笑
 * @date 2020/7/19 9:28
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    public Map<String, Object> getItem(Long skuId) {
        Map<String,Object> map = new HashMap(16);
        //通过feign远程调用Product服务获取商品sku信息
        SkuInfo skuInfo = productFeignClient.getSkuInfo(skuId);
        //通过feign远程调用Product服务获取商品分类新
        BaseCategoryView categoryView = productFeignClient.getCategoryView(skuInfo.getCategory3Id());

        map.put("skuInfo", skuInfo);
        map.put("categoryView",categoryView);
        map.put("price",skuInfo.getPrice());

        return map;
    }
}
