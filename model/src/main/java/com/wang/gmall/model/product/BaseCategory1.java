package com.wang.gmall.model.product;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wang.gmall.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 叶瑟
 */
@Data
@ApiModel(description = "商品一级分类")
@TableName("base_category1")
public class BaseCategory1 extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类名称")
    @TableField("name")
    private String name;

}
