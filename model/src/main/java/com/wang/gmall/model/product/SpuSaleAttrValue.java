package com.wang.gmall.model.product;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wang.gmall.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * <p>
 * SpuSaleAttrValue
 * </p>
 *
 * @author 叶瑟
 */
@Data
@ApiModel(description = "销售属性值")
@TableName("spu_sale_attr_value")
public class SpuSaleAttrValue extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "商品id")
	@TableField("spu_id")
	private Long spuId;

	@ApiModelProperty(value = "销售属性id")
	@TableField("base_sale_attr_id")
	private Long baseSaleAttrId;

	@ApiModelProperty(value = "销售属性值名称")
	@TableField("sale_attr_value_name")
	private String saleAttrValueName;

	@ApiModelProperty(value = "销售属性名称(冗余)")
	@TableField("sale_attr_name")
	private String saleAttrName;

	// 是否是默认选中状态
//	@TableField("sale_attr_name")
//	String isChecked;
	@TableField(exist = false)
	String isChecked;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getSpuId() {
		return spuId;
	}

	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}

	public Long getBaseSaleAttrId() {
		return baseSaleAttrId;
	}

	public void setBaseSaleAttrId(Long baseSaleAttrId) {
		this.baseSaleAttrId = baseSaleAttrId;
	}

	public String getSaleAttrValueName() {
		return saleAttrValueName;
	}

	public void setSaleAttrValueName(String saleAttrValueName) {
		this.saleAttrValueName = saleAttrValueName;
	}

	public String getSaleAttrName() {
		return saleAttrName;
	}

	public void setSaleAttrName(String saleAttrName) {
		this.saleAttrName = saleAttrName;
	}

	public String getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}
}

