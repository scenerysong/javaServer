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
	public User getUserInf(String Users) throws SQLException{
		
		sql = "select * from login";
		ps = ct.prepareStatement(sql);
		rs = ps.executeQuery();
		
		User inf = new User();
		
		while(rs.next()) {
			
			inf.setBirthday(rs.getString("birthday"));
			inf.setPersonname(rs.getString("personname"));
			inf.setRace(rs.getString("race"));
			inf.setSex(rs.getString("sex"));
			inf.setUsername("Users");
		}
		
		return inf;
	}
	
	public boolean modifyinf(String Users, User inf) throws SQLException {
		sql = "update login set personname = ?, sex = ?, race = ?, birthday = ?"
				+ "where usrname = Users";
		ps = ct.prepareStatement(sql);
		ps.setString(1, inf.getPersonname());
		ps.setString(2, inf.getSex());
		ps.setString(3, inf.getRace());
		ps.setString(4, inf.getBirthday());
		
		if(ps.executeUpdate() > 0) return true;
		return false;
	}
}




