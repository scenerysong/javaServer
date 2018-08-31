package VC.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import VC.common.Book;
import VC.common.Course;
import VC.server.db.DBstart;

public class LibraryDAO extends DBstart{

	
	public LibraryDAO() throws ClassNotFoundException, SQLException {
		super();
	}
	
	public List<Book> getBookByBookname(String bookname) throws SQLException {
		
		/*
		 * æ‰§è¡Œè¯­å�¥éƒ¨åˆ†
		 * æ­¤å¤„parasæ ¹æ�®æ‰€éœ€è¦�çš„æ•°é‡�æ�¥new
		 * sqlæŸ¥é˜…ç›¸å…³æ–‡æ¡£å�¯çŸ¥é�“
		 * å�Žç»­æ‰§è¡Œ
		 */
		paras = new String[1];
		paras[0] = null;
		paras[0] = bookname;
		sql = "select * from library where bookname = ?";
		ps = ct.prepareStatement(sql);
		for(int i=0;i<paras.length;i++)
		{
			ps.setString(i+1, paras[i]);
		}
		rs = ps.executeQuery();

		/*
		 * ç»“æžœè½¬åŒ–éƒ¨åˆ†
		 * ç»“æžœå­˜åœ¨rsé‡Œ,ä½¿ç”¨ä¸‹é�¢æ–¹æ³•å…¨éƒ¨ä»¥Listå�–å‡º
		 */
		Book book = new Book();
		List<Book> booklist = new ArrayList<Book>();
		
		while(rs.next()) {

			book = new Book();
			book.setBookName(rs.getString("bookname"));
			book.setBookAuthor(rs.getString("bookpublisher"));
			book.setBookPublisher(rs.getString("bookauthor"));
			
			booklist.add(book);
		}
		/*
		 * è¿™é‡Œä¸€å¼€å§‹ç”¨çš„æ˜¯ä¾�èµ–æ³¨å…¥çš„æ–¹å¼�  
		 * å¹¶æ²¡æœ‰åœ¨æ¯�æ¬¡çš„å¾ªçŽ¯ä¸­ä½¿ç”¨newå¯¹è±¡çš„æ–¹å¼�  ä½†æ˜¯æ¯�æ¬¡å¾ªçŽ¯ä¹Ÿæ˜¯å�Œæ ·è®¾ç½®äº†ä¸�å�Œçš„å€¼ 
		 * ä½†æ˜¯å�ŽæœŸæµ‹è¯•çš„æ—¶å€™å�‘çŽ°å¾ªçŽ¯è¿™ä¸ªlistå§‹ç»ˆå�ªèƒ½å¾—åˆ°ç¬¬ä¸€æ¬¡æ�’å…¥çš„æ•°æ�® ä¸ºä»€ä¹ˆå‘¢ï¼Ÿ
		 * åŽŸå› åœ¨è¿™é‡Œ  å› ä¸ºä¾�èµ–æ³¨å…¥çš„åŽŸå›  
		 * é‚£ä¹ˆä¹Ÿå°±æ˜¯ç›¸å½“äºŽæˆ‘ä»¬å�ªnewäº†ä¸€æ¬¡  
		 * å�Žé�¢çš„æ•°æ�®å°½ç®¡åœ¨ä¸�å�œçš„æ”¹å�˜ä½†æ˜¯ä»–åœ¨å†…å­˜ä¸­çš„idå”¯ä¸€  
		 * è€Œlist.add æ˜¯å¼•ç”¨  æ‰€ä»¥ä¸€ç›´éƒ½ä¼šæ ¹æ�®idåŽ»å¯»æ‰¾  
		 * ä¹Ÿå°±æ˜¯ç¬¬ä¸€æ¬¡newå‡ºæ�¥çš„é‚£ä¸ª
		 * ç±»ä¼¼çš„,å§‹ç»ˆå¾—åˆ°æœ€å�Žä¸€æ¬¡çš„æ•°æ�®çš„åŽŸå› æ˜¯
		 * å®ƒæŒ‡å�‘çš„æ˜¯é‚£ä¸ªæŒ‡é’ˆ,ç„¶å�Žæˆ‘ä»¬æ”¹å�˜äº†çš„æ˜¯å†…å®¹çš„å€¼
		 * æŒ‡é’ˆæ²¡æœ‰å�˜åŒ–,æ‰€ä»¥çœ‹èµ·æ�¥æŒ‡å�‘ä¸�å�Œçš„å€¼,å…¶å®žç”¨çš„æ˜¯ä¸€æ ¹æŒ‡é’ˆ
		 */
		return booklist;
	}
	
	
	// borrow the book for the User.
	public boolean BorrowBook(String Users, String BookName) throws SQLException {
		sql = "select * from course where bookname = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, BookName);
		rs = ps.executeQuery();
		
		/* ；课程名称默认不重复*/
		Book book = new Book();
		if(rs.next()) {
			book.setBookID(rs.getString("ID"));
			book.setBookName(rs.getString("bookname"));
			book.setBookAuthor(rs.getString("bookauthor"));
			book.setBookPublisher(rs.getString("bookpublisher"));
			sql = "insert into BorrowedBook values (?, ?, ?, ?, ?)";
			ps = ct.prepareStatement(sql);
			ps.setString(1, book.getBookID());
			ps.setString(2, book.getBookName());
			ps.setString(3, book.getBookPublisher());
			ps.setString(4, book.getBookAuthor());
			ps.setString(5, Users);
			if(ps.executeUpdate() > 0) return true;
			else return false;
		}
		else return false;
	}
	
	// return all the books the user has borrowed
	public List<Book> GetAllMyBook(String Users) throws SQLException {
		sql = "select * from BorrowedbBook where User = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, Users);
		rs = ps.executeQuery();
		
		Book bk = new Book();
		List<Book> Bk = new ArrayList<Book>();
		
		while(rs.next()) {
			bk = new Book();
			
			bk.setBookID(rs.getString("ID"));
			bk.setBookName(rs.getString("bookname"));
			bk.setBookPublisher(rs.getString("bookpublisher"));
			bk.setBookAuthor("bookauthor");
			
			Bk.add(bk);
		}
		return Bk;
	}
	
	
	//delete the record of borrowed book
	public boolean returnbook(String Users, String bookname) throws SQLException {
		sql = "delete from BorrowedBook where User = ? and bookname = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, Users);
		ps.setString(2, bookname);
		if(ps.executeUpdate() > 0) return true;
		else return false;
	}
}
