package com.wang.gmall.item.controller;

import com.wang.gmall.item.service.ItemService;
import com.wang.gmall.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.applet.Main;

import java.util.Map;

/**
 * @author 微笑
 * @date 2020/7/18 14:13
 */
@RestController
@RequestMapping("/api/item")
public class ItemApiController {

    @Autowired
    private ItemService itemService;

    @GetMapping("{skuId}")
    public Map getItem(@PathVariable("skuId") Long skuId){
        //获取商品详细信息
        Map<String,Object> map = itemService.getItem(skuId);

        return map;
    }
}
