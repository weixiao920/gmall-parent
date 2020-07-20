package com.wang.gmall.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.gmall.model.product.SkuInfo;
import com.wang.gmall.result.Result;
import com.wang.gmall.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 微笑
 * @date 2020/7/17 14:07
 */
@RestController
@RequestMapping("/admin/product")
@CrossOrigin
public class SkuController {

    @Autowired
    SkuService skuService;

    @RequestMapping("saveSkuInfo")
    public Result saveSkuInfo(@RequestBody SkuInfo skuInfo){
        //保存sku
        skuService.saveSkuInfo(skuInfo);

        return Result.ok();
    }

    @RequestMapping("/list/{pageNum}/{pageSize}")
    public Result index(@PathVariable Long pageNum,
                        @PathVariable Long pageSize){

        Page page = new Page(pageNum,pageSize);
        //查询所有的sku并分页
        IPage<SkuInfo> skuInfoPage =  skuService.selectPage(page);

        return Result.ok(skuInfoPage);
    }

    @RequestMapping("onSale/{skuId}")
    public Result onSale(@PathVariable Long skuId){
        //商品上架
        skuService.onSale(skuId);

        return Result.ok();
    }

    @RequestMapping("cancelSale/{skuId}")
    public Result cancelSale(@PathVariable Long skuId){
        //商品下架
        skuService.cancelSale(skuId);

        return Result.ok();
    }

}
