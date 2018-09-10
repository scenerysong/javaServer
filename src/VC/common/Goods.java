package VC.common;

/**
 * 商品的属性
 * @author lin
 */
public class Goods implements java.io.Serializable{
	
	private static final long serialVersionUID = 6836144424744782242L;
	private String productName;
	private String value;
	private String goodsID;
	private String goodsNum;
	
	public Goods() {
		
	}
	
	public Goods(Goods goods) {
		this.setProductName(goods.getProductName());
		this.setValue(goods.getValue());
		this.setGoodsID(goods.getGoodsID());
		this.setGoodsNum(goods.getGoodsNum());
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
		this.value = value;
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
	public String toString() {
		return "Goods [productName=" + productName + ",value=" + value
				 + ", goodsID=" + goodsID+ ", goodsNum=" + goodsNum;
	}
}
