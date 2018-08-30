package VC.common;

import java.util.List;

public class GoodsMessage extends Message{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4636492175363052477L;
	private String productName;
	private String value;
	private String goodsID;
	private String goodsNum;
	
	private List<Goods> Goodslist;
	
	
	public GoodsMessage(){
		
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = productName;
	}
	public String getGoodsID() {
		return goodsID;
	}
	public void setGoodsID(String goodsID) {
		this.goodsID = goodsID;
	}
	public String getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}

	public List<Goods> getGoodslist() {
		return Goodslist;
	}

	public void setGoodslist(List<Goods> goodslist) {
		Goodslist = goodslist;
	}

}
