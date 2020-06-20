package com.extlight.springcloud.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单类
 * @author MoonlightL
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	
	private String orderId;
	
	private String goodsId;
	
	private int num;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Order(String orderId, String goodsId, int num) {
		super();
		this.orderId = orderId;
		this.goodsId = goodsId;
		this.num = num;
	}
	
}
