package com.wang.gmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.gmall.model.product.SkuSaleAttrValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 微笑
 * @date 2020/7/17 14:41
 */
public interface SkuSaleAttrValueMapper extends BaseMapper<SkuSaleAttrValue> {

    /**
     * 获取销售属性对应的id为key,value为skuId
     * @param spuId
     * @return
     */
    List<Map<String, Object>> selectSkuValueIdsMap(@Param("spuId") Long spuId);
}
