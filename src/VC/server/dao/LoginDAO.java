package VC.server.dao;

import java.sql.SQLException;

import VC.common.User;
import VC.server.db.DBstart;

public class LoginDAO extends DBstart{

	public LoginDAO() throws ClassNotFoundException, SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

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
	
	public boolean addUser(String username, String passwd, String usertype) throws SQLException {
		sql = "select * from login where usrname = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, username);
		if(rs.next()) return false;
		
		sql = "insert into login (usrname, passwd, balance, Credit, usertype) values (?, ?, ?, ?, ?)";
		ps = ct.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, passwd);
		ps.setString(3, "0");
		ps.setString(4, "0");
		ps.setString(5, usertype);
		if(ps.executeUpdate() > 0) return true;
		else return false;
	}
	
	public boolean delUser(String username) throws SQLException {
		sql = "delete from login where usrname = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, username);
		
		if(ps.executeUpdate() > 0) return true;
		else return false;
	}

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
	
	public int getBalance(String username) throws SQLException {
		sql = "select * from login where usrname = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, username);
		rs = ps.executeQuery();
		if(rs.next()) return Integer.valueOf(rs.getString("balance")).intValue();
		else return -1; 
	}
	
	public boolean setBalance(String username, String balance) throws SQLException {
		sql = "update login set balance = ? where usrname = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(2, username);
		ps.setString(1, String.valueOf(balance));
		return ps.executeUpdate() > 0;
	}
}
