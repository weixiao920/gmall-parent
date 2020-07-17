package com.wang.gmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.gmall.mapper.*;
import com.wang.gmall.model.product.*;
import com.wang.gmall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 微笑
 * @date 2020/7/14 18:36
 */
@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    private SpuInfoMapper spuInfoMapper;

    @Autowired
    private SpuImageMapper spuImageMapper;

    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;

    @Autowired
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;

    @Autowired
    private BaseTrademarkMapper trademarkMapper;

    @Autowired
    private BaseSaleAttrMapper saleAttrMapper;


    @Override
    public IPage<SpuInfo> getSpuPage(Page infoPage, String category3Id) {

        QueryWrapper<SpuInfo> spuInfoQueryWrapper = new QueryWrapper<>();
        spuInfoQueryWrapper.eq("category3_id",category3Id);

        //获取spu属性并分页
        IPage iPage = spuInfoMapper.selectPage(infoPage, spuInfoQueryWrapper);
        return  iPage;
    }

    @Override
    public List<BaseSaleAttr> getBaseSaleAttrList() {
        //获取销售属性
        return saleAttrMapper.selectList(null);
    }

    @Override
    public List<BaseTrademark> getTrademarkList() {
        //获取品牌属性
        return trademarkMapper.selectList(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSpuInfo(SpuInfo spuInfo) {
        //保存spuInfo表
        spuInfoMapper.insert(spuInfo);
        //保存成功，主键返回策略得到id
        Long spuId = spuInfo.getId();

        //保存spu_sale_attr
        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();

        for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
            //插入spuId到销售属性表
            spuSaleAttr.setSpuId(spuId);
            spuSaleAttrMapper.insert(spuSaleAttr);

            //保存spu_sale_attr_value
            List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
            for (SpuSaleAttrValue spuSaleAttrValue : spuSaleAttrValueList) {
                //插入spuId到属性值表
                spuSaleAttrValue.setSpuId(spuId);
                //插入销售属性名称到销售属性的值表
                spuSaleAttrValue.setSaleAttrName(spuSaleAttr.getSaleAttrName());

                spuSaleAttrValueMapper.insert(spuSaleAttrValue);
            }

        }
        //保存 spu_image
        List<SpuImage> spuImageList = spuInfo.getSpuImageList();

        for (SpuImage spuImage : spuImageList) {
            spuImage.setSpuId(spuId);
            spuImageMapper.insert(spuImage);
        }
    }

    @Override
    public List<SpuSaleAttr> getSpuSaleAttrList(Long spuId) {
        QueryWrapper<SpuSaleAttr> spuSaleAttrQueryWrapper = new QueryWrapper<>();
        spuSaleAttrQueryWrapper.eq("spu_id",spuId);
        //获取这个spuId的销售属性
        List<SpuSaleAttr> spuSaleAttrList = spuSaleAttrMapper.selectList(spuSaleAttrQueryWrapper);



        //获取这spuId和相同baseSaleAttrId下的的销售属性的值
        for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
            QueryWrapper<SpuSaleAttrValue> spuSaleAttrValueQueryWrapper = new QueryWrapper<>();
            spuSaleAttrValueQueryWrapper.eq("spu_id",spuId);
            spuSaleAttrValueQueryWrapper.eq("base_sale_attr_id",spuSaleAttr.getBaseSaleAttrId());

            List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttrValueMapper.selectList(spuSaleAttrValueQueryWrapper);
            spuSaleAttr.setSpuSaleAttrValueList(spuSaleAttrValueList);
        }

        return spuSaleAttrList;
    }

    @Override
    public List<SpuImage> getSpuImageList(Long spuId) {
        QueryWrapper<SpuImage> spuImageQueryWrapper = new QueryWrapper<>();
        spuImageQueryWrapper.eq("spu_id",spuId);

        //获取这个spuId的图片集合
        List<SpuImage> spuImageList = spuImageMapper.selectList(spuImageQueryWrapper);
        return spuImageList;
    }
}
