package com.wang.gmall.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.gmall.model.product.*;
import com.wang.gmall.result.Result;
import com.wang.gmall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 微笑
 * @date 2020/7/14 18:27
 */
@RestController
@RequestMapping("/admin/product")
@CrossOrigin
public class SpuController {

    @Autowired
    private SpuService spuService;

    @RequestMapping("/{pageNum}/{pageSize}")
    public Result index(@PathVariable Long pageNum,
                        @PathVariable Long pageSize,
                        String category3Id){

        Page infoPage = new Page(pageNum,pageSize);
        //获取spu并且分页展示
        IPage<SpuInfo> spuInfoPage = spuService.getSpuPage(infoPage,category3Id);

        return Result.ok(spuInfoPage);
    }

    @RequestMapping("baseSaleAttrList")
    public Result baseSaleAttrList(){
        //获取销售属性
        List<BaseSaleAttr> saleAttrList = spuService.getBaseSaleAttrList();
        return Result.ok(saleAttrList);
    }

    @RequestMapping("baseTrademark/getTrademarkList")
    public Result getTrademarkList(){
        //获取品牌属性
        List<BaseTrademark> trademarkList = spuService.getTrademarkList();
        return Result.ok(trademarkList);
    }

    @RequestMapping("saveSpuInfo")
    public Result saveSpuInfo(@RequestBody SpuInfo spuInfo){
        //保存spuInfo
        spuService.saveSpuInfo(spuInfo);

        return Result.ok();
    }

    @GetMapping("spuSaleAttrList/{spuId}")
    public Result spuSaleAttrList(@PathVariable Long spuId){
        List<SpuSaleAttr> spuSaleAttrList = spuService.getSpuSaleAttrList(spuId);
        return Result.ok(spuSaleAttrList);
    }

    @GetMapping("spuImageList/{spuId}")
    public Result spuImageList(@PathVariable Long spuId){
        List<SpuImage> spuImageList = spuService.getSpuImageList(spuId);
        return Result.ok(spuImageList);
    }
}
