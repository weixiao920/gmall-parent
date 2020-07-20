package com.wang.gmall.item.client;

import com.wang.gmall.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * @author 微笑
 * @date 2020/7/18 14:27
 */
@FeignClient("service-item")
public interface ItemFeignClient {

    /**
     * 获取商品sku详细信息
     * @param skuId
     * @return
     */
    @GetMapping("/api/item/{skuId}")
    Map<String,Object> getItem(@PathVariable("skuId") Long skuId);



}
