package com.extlight.springcloud.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 商品类
 * 
 * @author MoonlightL
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Goods {

	private String goodsId;

	private String name;

	private String descr;

	// 测试端口
	private int port;

	@Override
	public String toString() {
		return "Goods [goodsId=" + goodsId + ", name=" + name + ", descr=" + descr + ", port=" + port + "]";
	}

	public Goods(String goodsId, String name, String descr, int port) {
		super();
		this.goodsId = goodsId;
		this.name = name;
		this.descr = descr;
		this.port = port;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
