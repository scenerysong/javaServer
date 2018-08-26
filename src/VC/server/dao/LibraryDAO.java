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
		
		/*
		 * 执行语句部分
		 * 此处paras根据所需要的数量来new
		 * sql查阅相关文档可知道
		 * 后续执行
		 */
		paras = new String[1];
		paras[0] = bookname;
		sql = "select * from library where bookname = ?";
		ps = ct.prepareStatement(sql);
		for(int i=0;i<paras.length;i++)
		{
			ps.setString(i+1, paras[i]);
		}
		rs = ps.executeQuery();

		/*
		 * 结果转化部分
		 * 结果存在rs里,使用下面方法全部以List取出
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
		 * 这里一开始用的是依赖注入的方式  
		 * 并没有在每次的循环中使用new对象的方式  但是每次循环也是同样设置了不同的值 
		 * 但是后期测试的时候发现循环这个list始终只能得到第一次插入的数据 为什么呢？
		 * 原因在这里  因为依赖注入的原因 
		 * 那么也就是相当于我们只new了一次  
		 * 后面的数据尽管在不停的改变但是他在内存中的id唯一  
		 * 而list.add 是引用  所以一直都会根据id去寻找  
		 * 也就是第一次new出来的那个
		 * 类似的,始终得到最后一次的数据的原因是
		 * 它指向的是那个指针,然后我们改变了的是内容的值
		 * 指针没有变化,所以看起来指向不同的值,其实用的是一根指针
		 */
		return booklist;
	}
}
