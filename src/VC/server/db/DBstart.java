package VC.server.db;

import java.io.File;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

/**
 * 
 * @author xgw
 *数据库的连接,DAO操作调用此类进行数据库的连接即可
 */
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
	
	/*
	 * 说明此处五个公有成员
	 * sql:用于数据库执行的命令语句,*代表需要搜寻的,from(表名),?表示输入的变量
	 * paras:储存输入变量,方法中使用paras依次替换sql语句中的?
	 * ct:用于连接数据库的变量
	 * ps:使用的完整SQL语句
	 * rs:执行之后得到的结果
	 */
	
	static {
		String dbpath = new File("").getAbsolutePath().replace('\\', '/') + "/vCampus.accdb";
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
