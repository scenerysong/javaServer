package VC.server.dao;

/**
 * class {@code LoginDAO} is the subclass of class {@link VC.server.db.DBstart} for database.
 * <p>it is used to get the information from database according to the requirements and do some simple data processing if needed.
 * including methods for login and maintaining the basic information of users.
 * 
 * @author Guangwei Xiong
 * @author Linsong Wang 
 * 
 * @version 1.0
*/

import java.sql.SQLException;

import VC.common.User;
import VC.server.db.DBstart;

public class LoginDAO extends DBstart{

	public LoginDAO() throws ClassNotFoundException, SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * get the password of a user 
	 * 
	 * <p><pre>{@code
	 * show how to use this method
	 * 
	 * String User = "wls";
	 * LoginDAO logindao = new LoginDAO();
	 * 
	 * flag = login.getPasswd(User);
	 * }
	 * 
	 * @param usrname
	 * @return the password of user given
	 * @throws SQLException
	 */
	public String getPasswd(String usrname) throws SQLException {
		
		String pwd = null;
		paras = new String[1];
		paras[0] = usrname;
		sql = "select * from login where usrname = ?";
		ps = ct.prepareStatement(sql);
		for(int i=0;i<paras.length;i++)
		{
			ps.setString(i+1, paras[i]);
		}
		rs = ps.executeQuery();

		while(rs.next()) {
			pwd = rs.getString("passwd");
		}
		
		return pwd;
	}
	
	
	/**
	 * 
	 * add a user into database
	 * 
	 * <p><pre>{@code
	 * show how to use this method
	 * 
	 * String User = "wls";
	 * String passwd = "233";
	 * String usertype = "superuser";
	 * LoginDAO logindao = new LoginDAO();
	 * 
	 * flag = login.addUser(User, passwd, usertype);
	 * }
	 * 
	 * @param username
	 * @param passwd
	 * @param usertype
	 * @return the result of operation
	 * @throws SQLException
	 */
	public boolean addUser(String username, String passwd, String usertype) throws SQLException {
		sql = "select * from login where usrname = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, username);
		if(rs.next()) return false;
		
		sql = "insert into login (usrname, passwd, balance, Credit, usertype) values (?, ?, ?, ?, ?)";
		ps = ct.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, passwd);
		ps.setString(3, "1000");
		ps.setString(4, "0");
		ps.setString(5, usertype);
		if(ps.executeUpdate() > 0) return true;
		else return false;
	}
	
	/**
	 * 
	 * delete a user in database
	 * 
	 * <p><pre>{@code
	 * show how to use this method
	 * 
	 * String User = "wls";
	 * LoginDAO logindao = new LoginDAO();
	 * 
	 * flag = login.delUser(User);
	 * }
	 * 
	 * @param username
	 * @return the result of operation
	 * @throws SQLException
	 */
	public boolean delUser(String username) throws SQLException {
		sql = "delete from login where usrname = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, username);
		
		if(ps.executeUpdate() > 0) return true;
		else return false;
	}

	
	/**
	 * 
	 * Judge the type of user
	 * 
	 * <p><pre>{@code
	 * show how to use this method
	 * 
	 * String User = "wls";
	 * LoginDAO logindao = new LoginDAO();
	 * 
	 * flag = login.JudgeUserType(User);
	 * }
	 * 
	 * @param username
	 * @return the result of operation
	 * @throws SQLException
	 */
	public boolean JudgeUserType(String username) throws SQLException {
		sql = "select * from login where usrname = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, username);
		rs = ps.executeQuery();
	
		if(rs.next()) {
			if(rs.getString("usertype").equals("superuser")) 
				return true;
			else return false;
		}
		else return false;
	}
	
	
	/**
	 * 
	 * get the balance of a user in database
	 * 
	 * <p><pre>{@code
	 * show how to use this method
	 * 
	 * String User = "wls";
	 * LoginDAO logindao = new LoginDAO();
	 * 
	 * int balance = login.getBalance(User);
	 * }
	 * 
	 * @param username
	 * @return the balance of a user
	 * @throws SQLException
	 */
	public int getBalance(String username) throws SQLException {
		sql = "select * from login where usrname = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, username);
		rs = ps.executeQuery();
		if(rs.next()) return Integer.valueOf(rs.getString("balance")).intValue();
		else return -1; 
	}
	
	/**
	 * 
	 * update the balance of a user in database
	 * 
	 * <p><pre>{@code
	 * show how to use this method
	 * 
	 * String User = "wls";
	 * LoginDAO logindao = new LoginDAO();
	 * 
	 * int balance = login.getBalance(User);
	 * }
	 * 
	 * @param username
	 * @param balance
	 * @return the result of operation
	 * @throws SQLException
	 */
	public boolean setBalance(String username, String balance) throws SQLException {
		sql = "update login set balance = ? where usrname = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(2, username);
		ps.setString(1, String.valueOf(balance));
		return ps.executeUpdate() > 0;
	}
}
