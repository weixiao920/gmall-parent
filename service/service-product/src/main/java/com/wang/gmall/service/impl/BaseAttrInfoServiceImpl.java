package com.wang.gmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wang.gmall.mapper.BaseAttrInfoMapper;
import com.wang.gmall.mapper.BaseAttrValueMapper;
import com.wang.gmall.model.product.BaseAttrInfo;
import com.wang.gmall.model.product.BaseAttrValue;
import com.wang.gmall.service.BaseAttrInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 微笑
 * @date 2020/7/14 14:16
 */
@Service
public class BaseAttrInfoServiceImpl implements BaseAttrInfoService {

    @Autowired
    private BaseAttrInfoMapper attrInfoMapper;

    @Autowired
    private BaseAttrValueMapper attrValueMapper;

    @Override
    public List<BaseAttrInfo> getAttrInfo(String category3Id) {

        QueryWrapper<BaseAttrInfo> attrInfoQueryWrapper = new QueryWrapper<>();
        attrInfoQueryWrapper.eq("category_id",category3Id);
        //说明是几级分类下的
        attrInfoQueryWrapper.eq("category_level",3);
        //获取平台属性
        List<BaseAttrInfo> attrInfoList = attrInfoMapper.selectList(attrInfoQueryWrapper);

        for (BaseAttrInfo attrInfo : attrInfoList) {
            //再获取这个平台属性下的value
            QueryWrapper<BaseAttrValue> attrValueQueryWrapper = new QueryWrapper<>();
            attrValueQueryWrapper.eq("attr_id",attrInfo.getId());

            List<BaseAttrValue> attrValueList = attrValueMapper.selectList(attrValueQueryWrapper);

            attrInfo.setAttrValueList(attrValueList);
        }

        return attrInfoList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAttrInfo(BaseAttrInfo attrInfo) {

        Long attrId = attrInfo.getId();
        if(attrId != null && attrId > 0){
            //修改平台属性
            attrInfoMapper.updateById(attrInfo);
            QueryWrapper<BaseAttrValue> attrValueQueryWrapper = new QueryWrapper<>();
            attrValueQueryWrapper.eq("attr_id",attrId);
            //删除这个平台属性的值，再插入，实现更新
            attrValueMapper.delete(attrValueQueryWrapper);
        }else{
            //添加平台属性
            attrInfoMapper.insert(attrInfo);
            attrId = attrInfo.getId();
        }


        List<BaseAttrValue> attrValueList = attrInfo.getAttrValueList();
        if(attrValueList != null && attrValueList.size() > 0) {
            for (BaseAttrValue attrValue : attrValueList) {
                //设置平台属性的attrId
                attrValue.setAttrId(attrId);
                //添加平台属性的值
                attrValueMapper.insert(attrValue);
            }
        }
    }

    @Override
    public List<BaseAttrValue> getAttrValueList(String attrId) {

        QueryWrapper<BaseAttrValue> attrValueQueryWrapper = new QueryWrapper<>();
        attrValueQueryWrapper.eq("attr_id",attrId);

        return attrValueMapper.selectList(attrValueQueryWrapper);
    }
}
