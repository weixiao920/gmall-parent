package com.wang.gmall.model.activity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 叶瑟
 */
@Data
public class OrderRecode implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;

	private SeckillGoods seckillGoods;

	private Integer num;

	private String orderStr;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public SeckillGoods getSeckillGoods() {
		return seckillGoods;
	}

	public void setSeckillGoods(SeckillGoods seckillGoods) {
		this.seckillGoods = seckillGoods;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getOrderStr() {
		return orderStr;
	}

	public void setOrderStr(String orderStr) {
		this.orderStr = orderStr;
	}
}
