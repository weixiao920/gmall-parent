package com.wang.gmall.item.service.impl;

import com.alibaba.fastjson.JSON;
import com.wang.gmall.item.service.ItemService;
import com.wang.gmall.model.product.BaseCategoryView;
import com.wang.gmall.model.product.SkuInfo;
import com.wang.gmall.model.product.SkuSaleAttrValue;
import com.wang.gmall.model.product.SpuSaleAttr;
import com.wang.gmall.product.client.ProductFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
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
        //获取价格
        BigDecimal price = productFeignClient.getPrice(skuId);
        //获取销售属性值，并对当前skuId下的属性值添加个选择状态，isChecked=1
        Long spuId = skuInfo.getSpuId();
        List<SpuSaleAttr> spuSaleAttrList = productFeignClient.getSpuSaleAttrListCheckBySku(skuId,spuId);

        //获取销售属性对应的id为key,value为skuId
        List<Map<String,Object>> mapList = productFeignClient.getSkuValueIdsMap(spuId);

        HashMap<String, String> valueMap = new HashMap<>(16);
        for (Map<String, Object> stringObjectMap : mapList) {
            String k = stringObjectMap.get("value_ids")+"";
            String v = stringObjectMap.get("sku_id")+"";

            valueMap.put(k,v);

        }

        map.put("skuInfo", skuInfo);
        map.put("categoryView",categoryView);
        map.put("price",price);
        map.put("spuSaleAttrList",spuSaleAttrList);
        map.put("valuesSkuJson", JSON.toJSONString(valueMap));
        return map;
    }
}
