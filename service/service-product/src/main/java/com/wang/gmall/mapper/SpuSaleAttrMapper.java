package com.wang.gmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.gmall.model.product.SpuSaleAttr;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 微笑
 * @date 2020/7/14 18:39
 */
public interface SpuSaleAttrMapper extends BaseMapper<SpuSaleAttr> {

    /**
     * 获取销售属性，并对当前sku下的销售属性为选择状态， isChecked=1
     * @param skuId
     * @param spuId
     * @return
     */
    List<SpuSaleAttr> selectSpuSaleAttrListCheckBySku(@Param("skuId") Long skuId,@Param("spuId") Long spuId);


}
