package VC.server.dao;

/**
 * class {@code ShopDAO} is the subclass of class {@link VC.server.db.DBstart} for database.
 * <p>it is used to get the information from database according to the requirements and do some simple data processing if needed.
 * including methods for maintaining the information of goods.
 * 
 * @author Guangwei Xiong
 * @author Linsong Wang 
 * 
 * @version 1.0
*/

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

	/**
	 * 
	 * get all of the goods in the database
	 * 
	 * <p><pre>{@code
	 * show how to use this method
	 * 
	 * List<Goods> goods = new ArrayList<Goods>();
	 * ShopDAO shopdao = new ShopDAO();
	 * 
	 * goods = shopdao.getAllGoods();
	 * }</pre>
	 * 
	 * @return the information of all goods in database
	 * @throws SQLException
	 */
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
	/**
	 * add the good to the shopping cart.
	 * 
	 * <p><pre>{@code
	 * show how to use this method
	 * 
	 * String User = "lsx";
	 * String goodname = "potatochips";
	 * String number = "1";
	 * ShopDAO shopdao = new ShopDAO();
	 * 
	 * flag = shopdao.addtoshoppingcart(User, goodname, number);
	 * } </pre>
	 * 
	 * @param User
	 * @param goodname
	 * @param number
	 * @return the result of operation
	 * @throws SQLException
	 */
	public boolean addtoshoppingcart(String User, String goodname, String number) throws SQLException {
		sql = "select * from goods where goodname = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, goodname);
		rs = ps.executeQuery();

		if (rs.next()) {
			sql = "insert into shoppingcart (User, goodname, goodvalue, goodnumber, goodid) values (?, ?, ?, ?, ?)";
			ps = ct.prepareStatement(sql);
			ps.setString(5, rs.getString("goodid"));
			ps.setString(1, User);
			ps.setString(2, rs.getString("goodname"));
			ps.setString(3, rs.getString("goodvalue"));
			ps.setString(4, number);
			if (ps.executeUpdate() > 0)
				return true;
			else
				return false;
		} else
			return false;
	}

	/**
	 * 
	 * return all goods the user has put into shopping cart
	 * 
	 * <p><pre>{@code
	 * show how to use this method
	 * 
	 * List<Goods> Gds = new ArrayList<Goods>();
	 * String User = "lsx";
	 * ShopDAO shopdao = new ShopDAO();
	 * 
	 * Gds = shopdao.GetMyshoppingcart(User);
	 * } </pre>
	 * 
	 * @param Users
	 * @return the goods in shopping cart
	 * @throws SQLException
	 */
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

	/**
	 * 
	 * delete the record of good
	 * 
	 * <p><pre>{@code
	 * show how to use this method
	 * 
	 * String User = "lsx";
	 * String goodname = "potatochips";
	 * ShopDAO shopdao = new ShopDAO();
	 * 
	 * flag = shopdao.deletegoodfromcart(User, goodname);
	 * } </pre>
	 * 
	 * @param Users
	 * @param goodname
	 * @return the result of operation
	 * @throws SQLException
	 */
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
	
	/**
	 * 
	 * maintain the data after the payment including removing the cart and maintain the balance of users and number of goods.  
	 * 
	 * <p><pre>{@code
	 * show how to use this method
	 * 
	 * String User = "lsx";
	 * String goodname = "potatochips";
	 * String goodnumber = "1";
	 * ShopDAO shopdao = new ShopDAO();
	 * 
	 * flag = shopdao.deletegoodfromcart(User, goodname, goodnumber);
	 * } </pre>
	 * 
	 * @param Users
	 * @param goodname
	 * @param goodnumber
	 * @return the result of operation
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean payforgoodincart(String Users, String goodname, String goodnumber) throws SQLException, ClassNotFoundException {
		if (deletegoodfromcart(Users, goodname) == false)
			return false;
		if (payforgood(Users, goodname, goodnumber) == false)
			return false;
		return true;
	}

	
	/**
	 * 
	 * search the good through a given name 
	 * 
	 * <p><pre>{@code
	 * show how to use this method
	 * 
	 * String goodname = "potatochips";
	 * ShopDAO shopdao = new ShopDAO();
	 * 
	 * flag = shopdao.getgoodBygoodname(goodname);
	 * } </pre>
	 * 
	 * @param goodname
	 * @return the information of good
	 * @throws SQLException
	 */
	//
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
	/**
	 * 
	 * update the number of  a good in database
	 * 
	 * <p><pre>{@code
	 * show how to use this method
	 * 
	 * String goodname = "potatochips";
	 * String goodnumber = "goodnumber";
	 * ShopDAO shopdao = new ShopDAO();
	 * 
	 * flag = shopdao.setNumber(goodname, goodnumber);
	 * }</pre>
	 *  
	 * @param goodname
	 * @param number
	 * @return the result of  operation
	 * @throws SQLException
	 */
	
	public boolean setNumber(String goodname, String number) throws SQLException {
		sql = "update goods set goodnumber = ? where goodname = ?"; 
		ps = ct.prepareStatement(sql);
		ps.setString(1, number);
		ps.setString(2, goodname);
		
		return ps.executeUpdate() > 0;
	}
	
	/**
	 * 
	 * maintain the balance and the number of goods
	 * 
	 *  <p><pre>{@code
	 * show how to use this method
	 * 
	 * String User = "wls";
	 * String goodname = "potatochips";
	 * String goodnumber = "goodnumber";
	 * ShopDAO shopdao = new ShopDAO();
	 * 
	 * flag = shopdao.payforgood(User, goodname, goodnumber);
	 * }</pre>
	 * 
	 * @param Users
	 * @param goodname
	 * @param goodnumber
	 * @return the result of operation
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean payforgood(String Users, String goodname, String goodnumber) throws SQLException, ClassNotFoundException {
		LoginDAO logindao = new LoginDAO();
		
		//deduct the balance of a user needing payment service
		
		System.out.println(goodnumber);
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
		

		return false;
	}
}
