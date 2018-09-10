package VC.common;

import java.util.List;

/**
 * 
 * @author song
 *商店模块所使用的信息
 */
public class GoodsMessage extends Message{
	
	private static final long serialVersionUID = 1023509898510044636L;
	private String productName;
	private String value;
	private String goodsID;
	private String goodsNum;
	private String balance;
	
	private List<Goods> Goodslist;
	private List<String> GoodsName;
	private List<String> Num;
	
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

	public List<Goods> getGoodslist() {
		return Goodslist;
	}

	public void setGoodslist(List<Goods> goodslist) {
		Goodslist = goodslist;
	}

	public List<String> getGoodsName() {
		return GoodsName;
	}

	public void setGoodsName(List<String> goodsName) {
		GoodsName = goodsName;
	}

	public List<String> getNum() {
		return Num;
	}

	public void setNum(List<String> num) {
		Num = num;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}
	


}
