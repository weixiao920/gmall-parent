package com.wang.gmall.service;

import com.wang.gmall.model.product.BaseAttrInfo;
import com.wang.gmall.model.product.BaseAttrValue;

import java.util.List;

/**
 * @author 微笑
 * @date 2020/7/14 14:16
 */
public interface BaseAttrInfoService {

    /**
     * 根据分类id获取平台属性
     * @param category3Id
     * @return
     */
    List<BaseAttrInfo> getAttrInfo(String category3Id);

    /**
     * 保存平台属性
     * @param attrInfo
     */
    void saveAttrInfo(BaseAttrInfo attrInfo);

    /**
     * 根据平台属性id获取平台属性值
     * @param attrId
     * @return
     */
    List<BaseAttrValue> getAttrValueList(String attrId);
}
