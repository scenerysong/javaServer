package VC.common;

public class GoodsMessage extends Message{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5787400267788454272L;
	private String productName;
	private String value;
	private String goodsID;
	private String goodsNum;
	
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

}
