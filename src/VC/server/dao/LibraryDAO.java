package VC.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import VC.common.Book;
import VC.server.db.DBstart;

public class LibraryDAO extends DBstart{

	
	public LibraryDAO() throws ClassNotFoundException, SQLException {
		super();
	}
	
	public List<Book> getBookByBookname(String bookname) throws SQLException {
		
		paras = new String[1];
		paras[0] = bookname;
		sql = "select * from library where bookname = ?";
		ps = ct.prepareStatement(sql);
		
		for(int i=0;i<paras.length;i++)
		{
			ps.setString(i+1, paras[i]);
		}
		
		//ps.setString(1, bookname);
		rs = ps.executeQuery();
		
		String BookN = null;
		String BookP = null;
		String BookA = null;
		Book book = new Book();
		List<Book> booklist = new ArrayList<Book>();
		
		//test
		/*
		if(!rs.next())
			System.out.println("false");
		*/
		
		while(rs.next()) {
			
			//BookN = rs.getString("bookname");
			//BookP = rs.getString("bookpublisher");
			//BookA = rs.getString("bookauthor");

			book = new Book();
			
			book.setBookName(rs.getString("bookname"));
			book.setBookAuthor(rs.getString("bookpublisher"));
			book.setBookPublisher(rs.getString("bookauthor"));
			
			//test
			//System.out.println(book.toString());
			booklist.add(book);
		}
		
		//test
		//System.out.println(booklist.get(0).toString());
		/*
		 * 这里遇到一个很奇怪的问题  一开始CountProvince用的是依赖注入的方式  
		 * 并没有在每次的循环中使用new对象的方式  但是每次循环也是同样设置了不同的值 
		 * 但是后期测试的时候发现循环这个list始终只能得到第一次插入的数据 为什么呢？
		 * 原因在这里  因为依赖注入的原因 
		 * 那么也就是相当于我们只new了一次  
		 * 后面的数据尽管在不停的改变但是他在内存中的id唯一  
		 * 而list.add 是引用  所以一直都会根据id去寻找  
		 * 也就是第一次new出来的那个
		 */
		return booklist;
		
	}
}
