package com.wang.gmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wang.gmall.mapper.BaseCategoryViewMapper;
import com.wang.gmall.mapper.SkuImageMapper;
import com.wang.gmall.mapper.SkuInfoMapper;
import com.wang.gmall.model.product.BaseCategoryView;
import com.wang.gmall.model.product.SkuImage;
import com.wang.gmall.model.product.SkuInfo;
import com.wang.gmall.service.ItemApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
