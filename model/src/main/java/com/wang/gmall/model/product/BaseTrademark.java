package com.wang.gmall.model.product;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wang.gmall.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * BaseTrademark
 * </p>
 *
 * @author 叶瑟
 */
@Data
@ApiModel(description = "商标品牌")
@TableName("base_trademark")
public class BaseTrademark extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "属性值")
	@TableField("tm_name")
	private String tmName;

	@ApiModelProperty(value = "品牌logo的图片路径")
	@TableField("logo_url")
	private String logoUrl;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getTmName() {
		return tmName;
	}

	public void setTmName(String tmName) {
		this.tmName = tmName;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
}

