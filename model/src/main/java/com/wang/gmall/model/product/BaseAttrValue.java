package com.wang.gmall.model.product;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wang.gmall.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * BaseAttrValue
 * </p>
 *
 * @author 叶瑟
 */
@Data
@ApiModel(description = "平台属性值")
@TableName("base_attr_value")
public class BaseAttrValue extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "属性值名称")
	@TableField("value_name")
	private String valueName;

	@ApiModelProperty(value = "属性id")
	@TableField("attr_id")
	private Long attrId;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getValueName() {
		return valueName;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

	public Long getAttrId() {
		return attrId;
	}

	public void setAttrId(Long attrId) {
		this.attrId = attrId;
	}
}

