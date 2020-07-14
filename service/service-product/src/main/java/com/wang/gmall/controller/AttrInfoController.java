package com.wang.gmall.controller;

import com.wang.gmall.model.product.BaseAttrInfo;
import com.wang.gmall.model.product.BaseAttrValue;
import com.wang.gmall.result.Result;
import com.wang.gmall.service.BaseAttrInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 微笑
 * @date 2020/7/14 14:11
 */
@RestController
@RequestMapping("/admin/product")
@CrossOrigin
public class AttrInfoController {

    @Autowired
    private BaseAttrInfoService attrInfoService;


    @RequestMapping("attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result getAttrInfo(@PathVariable String category1Id,
                              @PathVariable String category2Id,
                              @PathVariable String category3Id){
        //获取平台属性
        List<BaseAttrInfo> attrInfoList = attrInfoService.getAttrInfo(category3Id);

        return Result.ok(attrInfoList);
    }

    @RequestMapping("saveAttrInfo")
    public Result saveAttrInfo(@RequestBody BaseAttrInfo attrInfo){
        //添加平台属性
        attrInfoService.saveAttrInfo(attrInfo);

        return Result.ok();
    }

    @RequestMapping("getAttrValueList/{attrId}")
    public Result getAttrValueList(@PathVariable String attrId){

        List<BaseAttrValue> attrValueList = attrInfoService.getAttrValueList(attrId);

        return Result.ok(attrValueList);
    }
}
