package VC.server.dao;

import java.sql.SQLException;

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
}
