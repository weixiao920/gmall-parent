package com.wang.gmall.product.client;

import com.wang.gmall.model.product.BaseCategoryView;
import com.wang.gmall.model.product.SkuInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 微笑
 * @date 2020/7/18 14:32
 */
@FeignClient("service-product")
public interface ProductFeignClient {

    /**
     * 调用service-product获取商品sku信息
     * inner : 内部服务相互调用
     * @param skuId
     * @return
     */
    @GetMapping("/api/product/inner/getSkuInfo/{skuId}")
    SkuInfo getSkuInfo(@PathVariable("skuId") Long skuId);

    /**
     * 调用service-product获取分类
     * @param category3Id
     * @return
     */
    @GetMapping("/api/product/inner/getCategoryView/{category3Id}")
    BaseCategoryView getCategoryView(@PathVariable Long category3Id);

}
