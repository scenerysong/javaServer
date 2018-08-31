package VC.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import VC.common.Book;
import VC.common.Goods;
import VC.server.db.DBstart;

public class ShopDAO extends DBstart{

	public ShopDAO() throws ClassNotFoundException, SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Goods> getAllGoods() throws SQLException{
		
		sql = "select * from goods";
		ps = ct.prepareStatement(sql);
		rs = ps.executeQuery();
		
		Goods good = new Goods();
		List<Goods> goods = new ArrayList<Goods>();
		
		while(rs.next()) {
			
			good = new Goods();
			good.setGoodsID(rs.getString("ID"));
			good.setGoodsNum(rs.getString("goodnumber"));
			good.setProductName(rs.getString("goodname"));
			good.setValue(rs.getString("goodvalue"));
			
			goods.add(good);
		}
		
		return goods;

	}
	public List<Goods> getGoodBygoodname(String goodname) throws SQLException {
		
		/*
		 * æ‰§è¡Œè¯­å�¥éƒ¨åˆ†
		 * æ­¤å¤„parasæ ¹æ�®æ‰€éœ€è¦�çš„æ•°é‡�æ�¥new
		 * sqlæŸ¥é˜…ç›¸å…³æ–‡æ¡£å�¯çŸ¥é�“
		 * å�Žç»­æ‰§è¡Œ
		 */
		paras = new String[1];
		paras[0] = null;
		paras[0] = goodname;
		sql = "select * from goods where goodname = ?";
		ps = ct.prepareStatement(sql);
		for(int i=0;i<paras.length;i++)
		{
			ps.setString(i+1, paras[i]);
		}
		rs = ps.executeQuery();

		/*
		 * ç»“æžœè½¬åŒ–éƒ¨åˆ†
		 * ç»“æžœå­˜åœ¨rsé‡Œ,ä½¿ç”¨ä¸‹é�¢æ–¹æ³•å…¨éƒ¨ä»¥Listå�–å‡º
		 */
		Goods Gd = new Goods();
		List<Goods> Gds = new ArrayList<Goods>();
		
		while(rs.next()) {

			Gd = new Goods();
			Gd.setGoodsID(rs.getString("ID"));
			Gd.setGoodsNum(rs.getString("goodnumber"));
			Gd.setValue(rs.getString("goodvalue"));
			
			Gds.add(Gd);
		}
		/*
		 * è¿™é‡Œä¸€å¼€å§‹ç”¨çš„æ˜¯ä¾�èµ–æ³¨å…¥çš„æ–¹å¼�  
		 * å¹¶æ²¡æœ‰åœ¨æ¯�æ¬¡çš„å¾ªçŽ¯ä¸­ä½¿ç”¨newå¯¹è±¡çš„æ–¹å¼�  ä½†æ˜¯æ¯�æ¬¡å¾ªçŽ¯ä¹Ÿæ˜¯å�Œæ ·è®¾ç½®äº†ä¸�å�Œçš„å€¼ 
		 * ä½†æ˜¯å�ŽæœŸæµ‹è¯•çš„æ—¶å€™å�‘çŽ°å¾ªçŽ¯è¿™ä¸ªlistå§‹ç»ˆå�ªèƒ½å¾—åˆ°ç¬¬ä¸€æ¬¡æ�’å…¥çš„æ•°æ�® ä¸ºä»€ä¹ˆå‘¢ï¼Ÿ
		 * åŽŸå› åœ¨è¿™é‡Œ  å› ä¸ºä¾�èµ–æ³¨å…¥çš„åŽŸå›  
		 * é‚£ä¹ˆä¹Ÿå°±æ˜¯ç›¸å½“äºŽæˆ‘ä»¬å�ªnewäº†ä¸€æ¬¡  
		 * å�Žé�¢çš„æ•°æ�®å°½ç®¡åœ¨ä¸�å�œçš„æ”¹å�˜ä½†æ˜¯ä»–åœ¨å†…å­˜ä¸­çš„idå”¯ä¸€  
		 * è€Œlist.add æ˜¯å¼•ç”¨  æ‰€ä»¥ä¸€ç›´éƒ½ä¼šæ ¹æ�®idåŽ»å¯»æ‰¾  
		 * ä¹Ÿå°±æ˜¯ç¬¬ä¸€æ¬¡newå‡ºæ�¥çš„é‚£ä¸ª
		 * ç±»ä¼¼çš„,å§‹ç»ˆå¾—åˆ°æœ€å�Žä¸€æ¬¡çš„æ•°æ�®çš„åŽŸå› æ˜¯
		 * å®ƒæŒ‡å�‘çš„æ˜¯é‚£ä¸ªæŒ‡é’ˆ,ç„¶å�Žæˆ‘ä»¬æ”¹å�˜äº†çš„æ˜¯å†…å®¹çš„å€¼
		 * æŒ‡é’ˆæ²¡æœ‰å�˜åŒ–,æ‰€ä»¥çœ‹èµ·æ�¥æŒ‡å�‘ä¸�å�Œçš„å€¼,å…¶å®žç”¨çš„æ˜¯ä¸€æ ¹æŒ‡é’ˆ
		 */
		return Gds;
	}
	
	
	// add the good to the shopping cart.
	public boolean addtoshoppingcart(String User, String goodname, String number) throws SQLException {
		sql = "select * from goods where goodname = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, goodname);
		rs = ps.executeQuery();
		
		Goods gd = new Goods();
		if(rs.next()) {
			sql = "insert into BorrowedBook values (?, ?, ?, ?, ?)";
			ps = ct.prepareStatement(sql);
			ps.setString(1, rs.getString("ID"));
			ps.setString(2, User);
			ps.setString(3, rs.getString("goodname"));
			ps.setString(4, rs.getString("goodvalue"));
			ps.setString(5, number);
			if(ps.executeUpdate() > 0) return true;
			else return false;
		}
		else return false;
	}
	
	// return all goods the user has put into shopping cart
	public List<Goods> GetMyshoppingcart(String Users) throws SQLException {
		sql = "select * from shoppingcart where user = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, Users);
		rs = ps.executeQuery();
		
		Goods Gd = new Goods();
		List<Goods> Gds = new ArrayList<Goods>();
		
		while(rs.next()) {
			Gd = new Goods();
			
			Gd.setGoodsID(rs.getString("ID"));
			Gd.setGoodsNum(rs.getString("number"));
			Gd.setProductName(rs.getString("goodname"));
		    Gd.setValue("goodvalue");
			
			Gds.add(Gd);
		}
		return Gds;
	}
	
	
	//delete the record of good
	public boolean deletegoodfromcart(String Users, String goodname) throws SQLException {
		sql = "delete from shoppingcart where user = ? and goodname = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, Users);
		ps.setString(2, goodname);
		if(ps.executeUpdate() > 0) return true;
		else return false;
	}
	
	//**We need to add the modification for the rest of the goods and the money of users.
	public boolean payforgoodincart(String Users, String goodname) throws SQLException {
		sql = "delete from shoppingcart where user = ? and goodname = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, Users);
		ps.setString(2, goodname);
		if(ps.executeUpdate() > 0) return true;
		else return false;
	}
	
}
