package com.wang.gmall.product.client;

import com.wang.gmall.model.product.BaseCategoryView;
import com.wang.gmall.model.product.SkuInfo;
import com.wang.gmall.model.product.SpuSaleAttr;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

    /**
     * 从数据库获取这个skuId的价格
     * @param skuId
     * @return
     */
    @GetMapping("/api/product/inner/getPrice/{skuId}")
    BigDecimal getPrice(@PathVariable  Long skuId);

    /**
     * 获取这个商品spu下的销售属性，并且对当前skuId下的属性值添加选中状态 isCheck=1
     * @param skuId
     * @param spuId
     * @return
     */
    @GetMapping("/api/product/inner/getSpuSaleAttrListCheckBySku/{skuId}/{spuId}")
    List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(@PathVariable Long skuId,@PathVariable Long spuId);

    /**
     * 获取销售属性对应的id为key,value为skuId
     * @param spuId
     * @return
     */
    @GetMapping("/api/product/inner/getSkuValueIdsMap/{spuId}")
    List<Map<String, Object>> getSkuValueIdsMap(@PathVariable Long spuId);
}
