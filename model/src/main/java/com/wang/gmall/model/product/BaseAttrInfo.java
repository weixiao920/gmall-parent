package com.wang.gmall.model.product;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wang.gmall.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

/**
 * <p>
 * BaseAttrInfo
 * </p>
 *
 * @author 叶瑟
 */
@Data
@ApiModel(description = "平台属性")
@TableName("base_attr_info")
public class BaseAttrInfo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "属性名称")
	@TableField("attr_name")
	private String attrName;

	@ApiModelProperty(value = "分类id")
	@TableField("category_id")
	private Long categoryId;

	@ApiModelProperty(value = "分类层级")
	@TableField("category_level")
	private Integer categoryLevel;

	//  平台属性值集合 表示非数据库字段
	//  当通过分类Id 查询数据的时候，将平台属性值一起查询出来 {1:n}
	@TableField(exist = false)
	private List<BaseAttrValue> attrValueList;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(Integer categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	public List<BaseAttrValue> getAttrValueList() {
		return attrValueList;
	}

	public void setAttrValueList(List<BaseAttrValue> attrValueList) {
		this.attrValueList = attrValueList;
	}
}

