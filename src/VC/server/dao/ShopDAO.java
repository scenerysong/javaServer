package VC.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import VC.common.Book;
import VC.common.Goods;
import VC.server.db.DBstart;

public class ShopDAO extends DBstart {

	public ShopDAO() throws ClassNotFoundException, SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	//get all of the goods in the database
	public List<Goods> getAllGoods() throws SQLException {

		sql = "select * from goods";
		ps = ct.prepareStatement(sql);
		rs = ps.executeQuery();

		Goods good = new Goods();
		List<Goods> goods = new ArrayList<Goods>();

		while (rs.next()) {

			good = new Goods();
			good.setGoodsID(rs.getString("goodid"));
			good.setGoodsNum(rs.getString("goodnumber"));
			good.setProductName(rs.getString("goodname"));
			good.setValue(rs.getString("goodvalue"));

			goods.add(good);
		}

		return goods;

	}

	// add the good to the shopping cart.
	public boolean addtoshoppingcart(String User, String goodname, String number) throws SQLException {
		sql = "select * from goods where goodname = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, goodname);
		rs = ps.executeQuery();

		if (rs.next()) {
			sql = "insert into BorrowedBook values (?, ?, ?, ?, ?)";
			ps = ct.prepareStatement(sql);
			ps.setString(1, rs.getString("goodid"));
			ps.setString(2, User);
			ps.setString(3, rs.getString("goodname"));
			ps.setString(4, rs.getString("goodvalue"));
			ps.setString(5, number);
			if (ps.executeUpdate() > 0)
				return true;
			else
				return false;
		} else
			return false;
	}

	// return all goods the user has put into shopping cart
	public List<Goods> GetMyshoppingcart(String Users) throws SQLException {
		sql = "select * from shoppingcart where user = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, Users);
		rs = ps.executeQuery();

		Goods Gd = new Goods();
		List<Goods> Gds = new ArrayList<Goods>();

		while (rs.next()) {
			Gd = new Goods();

			Gd.setGoodsID(rs.getString("goodid"));
			Gd.setGoodsNum(rs.getString("goodnumber"));
			Gd.setProductName(rs.getString("goodname"));
			Gd.setValue(rs.getString("goodvalue"));

			Gds.add(Gd);
		}
		return Gds;
	}

	// delete the record of good
	public boolean deletegoodfromcart(String Users, String goodname) throws SQLException {
		sql = "delete from shoppingcart where user = ? and goodname = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, Users);
		ps.setString(2, goodname);
		if (ps.executeUpdate() > 0)
			return true;
		else
			return false;
	}

	// **We need to add the modification for the rest of the goods and the money of
	// users.
	public boolean payforgoodincart(String Users, String goodname, String goodnumber) throws SQLException, ClassNotFoundException {
		if (deletegoodfromcart(Users, goodname) == false)
			return false;
		if (payforgood(Users, goodname, goodnumber) == false)
			return false;
		return true;
	}

	//search the good through a given name 
	public Goods getgoodBygoodname(String goodname) throws SQLException {
		sql = "select * from goods where goodname = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, goodname);
		rs = ps.executeQuery();

		Goods gd = new Goods();
		if (rs.next()) {
			gd.setGoodsID(rs.getString("goodid"));
			gd.setGoodsNum(rs.getString("goodnumber"));
			gd.setProductName(rs.getString("goodname"));
			gd.setValue(rs.getString("goodvalue"));
			// return gd;
		}
		return gd;
	}

	//update the number of  a good in database
	public boolean setNumber(String goodname, String number) throws SQLException {
		sql = "update goods set goodnumber = ? where goodname = ?"; 
		ps = ct.prepareStatement(sql);
		ps.setString(1, number);
		ps.setString(2, goodname);
		
		return ps.executeUpdate() > 0;
	}
	
	// update the database after the operation of payment
	public boolean payforgood(String Users, String goodname, String goodnumber) throws SQLException, ClassNotFoundException {
		LoginDAO logindao = new LoginDAO();
		
		//deduct the balance of a user needing payment service
		Goods good = getgoodBygoodname(goodname);
		int onecost = Integer.valueOf(good.getValue()).intValue();
		int money = logindao.getBalance(Users);
		int number = Integer.valueOf(goodnumber).intValue();
		money -= number * onecost;
		if (money < 0) return false;

		if(!logindao.setBalance(Users, String.valueOf(money))) return false;
		
		//update the number of goods in database
		int rest = Integer.valueOf(good.getGoodsNum()).intValue() - number;
		if(!setNumber(goodname, String.valueOf(rest))) return false;
		
		//update the profits of shop 
		sql = "select * from statistic where key = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, "income");
		rs = ps.executeQuery();

		if (rs.next()) {
			String income = rs.getString("value");
			int incomenum = Integer.valueOf(income).intValue() + number * onecost;
			sql = "update statistic set value = ? where key = ?";
			ps = ct.prepareStatement(sql);
			ps.setString(1, String.valueOf(incomenum));
			ps.setString(2, "income");
			if (ps.executeUpdate() > 0) return true;
		}
		return false;
	}
}
