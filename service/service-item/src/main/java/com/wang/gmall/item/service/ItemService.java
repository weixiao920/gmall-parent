package com.wang.gmall.item.service;

import java.util.Map;

/**
 * @author 微笑
 * @date 2020/7/19 9:28
 */
public interface ItemService {

    /**
     * 获取商品详细信息
     * @param skuId
     * @return
     */
    Map<String, Object> getItem(Long skuId);
}
