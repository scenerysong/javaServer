package VC.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
			good.setGoodsID(rs.getString("goodid"));
			good.setGoodsNum(rs.getString("goodnumber"));
			good.setProductName(rs.getString("goodname"));
			good.setValue(rs.getString("goodvalue"));
			
			goods.add(good);
		}
		
		return goods;

	}
}
