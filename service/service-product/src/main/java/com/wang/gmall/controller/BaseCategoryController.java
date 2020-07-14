package com.wang.gmall.controller;

import com.wang.gmall.model.product.BaseCategory1;
import com.wang.gmall.model.product.BaseCategory2;
import com.wang.gmall.model.product.BaseCategory3;
import com.wang.gmall.result.Result;
import com.wang.gmall.service.BaseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 微笑
 * @date 2020/7/13 19:38
 */


@RestController
@RequestMapping("/admin/product")
@CrossOrigin
public class BaseCategoryController {

    @Autowired
    private BaseCategoryService categoryService;

    @RequestMapping("getCategory1")
    public Result getCategory1(){
        //获取一级分类
        List<BaseCategory1> category1List = categoryService.getCategory1();

        return Result.ok(category1List);
    }

    @RequestMapping("getCategory2/{category1Id}")
    public Result getCategory2(@PathVariable String category1Id){
        //获取二级分类
        List<BaseCategory2> category2List = categoryService.getCategory2(category1Id);

        return Result.ok(category2List);
    }

    @RequestMapping("getCategory3/{category2Id}")
    public Result getCategory3(@PathVariable String category2Id){
        //获取三级分类
        List<BaseCategory3> category3List = categoryService.getCategory3(category2Id);

        return Result.ok(category3List);
    }

}
