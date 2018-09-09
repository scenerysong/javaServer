package VC.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import VC.common.Course;
import VC.common.User;
import VC.server.db.DBstart;

public class StuDAO extends DBstart{

	public StuDAO() throws ClassNotFoundException, SQLException {
		super();
	}
		// TODO Auto-generated constructor stub
	//get the personal information of a user in database 
	public User getUserInf(String Users) throws SQLException{
		
		sql = "select * from login where usrname = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, Users);
		rs = ps.executeQuery();
		
		User inf = new User();
		
		while(rs.next()) {
			
			inf.setBirthday(rs.getString("birthday"));
			System.out.println(rs.getString("personname"));
			inf.setPersonname(rs.getString("personname"));
			inf.setRace(rs.getString("race"));
			inf.setSex(rs.getString("sex"));
			inf.setUsername("Users");
		}
		
		return inf;
	}
	
	// update the personal information of a user in database
	public boolean modifyinf(String Users, User inf) throws SQLException {
		sql = "update login set personname = ?, sex = ?, race = ?, birthday = ?"
				+ "where usrname = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, inf.getPersonname());
		ps.setString(2, inf.getSex());
		ps.setString(3, inf.getRace());
		ps.setString(4, inf.getBirthday());
		ps.setString(5, Users);
		
		if(ps.executeUpdate() > 0) return true;
		return false;
	}
}




