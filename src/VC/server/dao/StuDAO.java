package VC.server.dao;

import java.sql.SQLException;
import VC.common.User;
import VC.server.db.DBstart;

public class StuDAO extends DBstart{

	public StuDAO() throws ClassNotFoundException, SQLException {
		super();
	}
		// TODO Auto-generated constructor stub
	public User getUserInf(String Users) throws SQLException{
		
		sql = "select * from login where usrname = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, Users);
		rs = ps.executeQuery();
		
		User inf = new User();
		
		while(rs.next()) {
			
			inf.setBirthday(rs.getString("birthday"));
			inf.setPersonname(rs.getString("personname"));
			System.out.print(rs.getString("personname") + " this is in dao, 1");
			System.out.println(inf.getPersonname() + " another one");
			inf.setRace(rs.getString("race"));
			inf.setSex(rs.getString("sex"));
			inf.setUsername("Users");
		}
		
		return inf;
	}
	
	public boolean modifyinf(String Users, User inf) throws SQLException {
		sql = "update login set personname = ?, sex = ?, race = ?, birthday = ?"
				+ " where usrname = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, inf.getPersonname());
		ps.setString(2, inf.getSex());
		ps.setString(3, inf.getRace());
		ps.setString(4, inf.getBirthday());
		ps.setString(5, inf.getUsername());
		
		if(ps.executeUpdate() > 0) return true;
		return false;
	}
}




