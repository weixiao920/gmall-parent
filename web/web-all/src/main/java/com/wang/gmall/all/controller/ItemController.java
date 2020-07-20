package com.wang.gmall.all.controller;

import com.wang.gmall.item.client.ItemFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author 微笑
 * @date 2020/7/18 11:49
 */
@Controller
@RequestMapping
public class ItemController {

    @Autowired
    private ItemFeignClient itemFeignClient;
    /**
     * sku详情页面
     * @param skuId
     * @param model
     * @return
     */
    @RequestMapping("{skuId}.html")
    public String getItem(@PathVariable Long skuId, Model model) {

        //使用feign远程调用获取详情信息服务
        Map<String, Object> map = itemFeignClient.getItem(skuId);
        model.addAllAttributes(map);
        return "item/index";
    }

}
