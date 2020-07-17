package com.wang.gmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.gmall.mapper.SkuAttrValueMapper;
import com.wang.gmall.mapper.SkuImageMapper;
import com.wang.gmall.mapper.SkuInfoMapper;
import com.wang.gmall.mapper.SkuSaleAttrValueMapper;
import com.wang.gmall.model.product.*;
import com.wang.gmall.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author 微笑
 * @date 2020/7/17 14:14
 */
@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuInfoMapper skuInfoMapper;

    @Autowired
    private SkuAttrValueMapper skuAttrValueMapper;

    @Autowired
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;

    @Autowired
    private SkuImageMapper skuImageMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSkuInfo(SkuInfo skuInfo) {
        //保存商品skuInfo
        skuInfoMapper.insert(skuInfo);
        Long skuId = skuInfo.getId();
        Long spuId = skuInfo.getSpuId();
        //保存商品sku_sale_attr_value
        List<SkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
        for (SkuSaleAttrValue skuSaleAttrValue : skuSaleAttrValueList) {
            skuSaleAttrValue.setSkuId(skuId);
            skuSaleAttrValue.setSpuId(spuId);

            skuSaleAttrValueMapper.insert(skuSaleAttrValue);
        }
        //保存商品sku_attr_value
        List<SkuAttrValue> skuAttrValueList = skuInfo.getSkuAttrValueList();
        for (SkuAttrValue skuAttrValue : skuAttrValueList) {
            skuAttrValue.setSkuId(skuId);

            skuAttrValueMapper.insert(skuAttrValue);
        }
        //保存商品sku_image
        List<SkuImage> skuImageList = skuInfo.getSkuImageList();
        for (SkuImage skuImage : skuImageList) {
            skuImage.setSkuId(skuId);

            skuImageMapper.insert(skuImage);
        }

    }

    @Override
    public IPage<SkuInfo> selectPage(Page page) {

        return skuInfoMapper.selectPage(page,null);
    }

    @Override
    public void onSale(Long skuId) {


//        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);
//        skuInfo.setIsSale(1);
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setIsSale(1);
        skuInfo.setId(skuId);

        skuInfoMapper.updateById(skuInfo);
    }

    @Override
    public void cancelSale(Long skuId) {
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setIsSale(0);
        skuInfo.setId(skuId);

        skuInfoMapper.updateById(skuInfo);
    }
}
