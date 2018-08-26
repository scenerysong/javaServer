package VC.server.db;

import java.io.File;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class DBstart {
	
	private static final String HXTT_ACCESS_JDBC_DRIVER = "com.hxtt.sql.access.AccessDriver";
	private static final String ACCESS_DRIVER = HXTT_ACCESS_JDBC_DRIVER;
	private static String url;
	static String user = "";
	static String password = null;
	
	public Connection ct = null;
	public ResultSet rs=null;
	public PreparedStatement ps=null;
	public String sql;
	public String[] paras;
	
	static {
		String dbpath = new File("").getAbsolutePath().replace('\\', '/') + "/DatabaseTest.accdb";
		// url = "jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb,
		// *.accdb)};DBQ="+dbpath;
		// url = "jdbc:ucanaccess://" + dbpath;
		url = "jdbc:access:/" + dbpath;
		// System.out.println(url);
	}
	
	public DBstart() throws ClassNotFoundException, SQLException {
		
		Class.forName(ACCESS_DRIVER);
		setCt(DriverManager.getConnection(url, user, password));
	}

	
	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public Connection getCt() {
		return ct;
	}

	public void setCt(Connection ct) {
		this.ct = ct;
	}

	public PreparedStatement getPs() {
		return ps;
	}

	public void setPs(PreparedStatement ps) {
		this.ps = ps;
	}


	public String getSql() {
		return sql;
	}


	public void setSql(String sql) {
		this.sql = sql;
	}

}
