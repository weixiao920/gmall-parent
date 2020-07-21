package com.wang.gmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wang.gmall.mapper.*;
import com.wang.gmall.model.product.BaseCategoryView;
import com.wang.gmall.model.product.SkuImage;
import com.wang.gmall.model.product.SkuInfo;
import com.wang.gmall.model.product.SpuSaleAttr;
import com.wang.gmall.service.ItemApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author 微笑
 * @date 2020/7/19 9:40
 */
@Service
public class ItemApiServiceImpl implements ItemApiService {

    @Autowired
    private SkuInfoMapper skuInfoMapper;

    @Autowired
    private BaseCategoryViewMapper categoryViewMapper;

    @Autowired
    private SkuImageMapper skuImageMapper;

    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;

    @Autowired
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;


    @Override
    public SkuInfo getSkuInfo(Long skuId) {

        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);

        //获取这个skuId的图片信息
        QueryWrapper<SkuImage> skuImageQueryWrapper = new QueryWrapper<>();
        skuImageQueryWrapper.eq("sku_id",skuId);

        List<SkuImage> skuImageList = skuImageMapper.selectList(skuImageQueryWrapper);

        skuInfo.setSkuImageList(skuImageList);


        return skuInfo;
    }

    @Override
    public BaseCategoryView getCategoryView(Long category3Id) {

        //通过视图获取分类信息
        return categoryViewMapper.selectById(category3Id);
    }

    @Override
    public BigDecimal getPrice(Long skuId) {

        //获取商品sku信息，返回价格
        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);

        return skuInfo.getPrice();
    }

    @Override
    public List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(Long skuId, Long spuId) {

        //获取该商品下spu销售属性
        return spuSaleAttrMapper.selectSpuSaleAttrListCheckBySku(skuId,spuId);
    }

    @Override
    public List<Map<String, Object>> getSkuValueIdsMap(Long spuId) {
        //获取销售属性对应的id为key,value为skuId
        return skuSaleAttrValueMapper.selectSkuValueIdsMap(spuId);
    }
}
