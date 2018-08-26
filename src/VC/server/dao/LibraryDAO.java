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
		 * ��������һ������ֵ�����  һ��ʼCountProvince�õ�������ע��ķ�ʽ  
		 * ��û����ÿ�ε�ѭ����ʹ��new����ķ�ʽ  ����ÿ��ѭ��Ҳ��ͬ�������˲�ͬ��ֵ 
		 * ���Ǻ��ڲ��Ե�ʱ����ѭ�����listʼ��ֻ�ܵõ���һ�β�������� Ϊʲô�أ�
		 * ԭ��������  ��Ϊ����ע���ԭ�� 
		 * ��ôҲ�����൱������ֻnew��һ��  
		 * ��������ݾ����ڲ�ͣ�ĸı䵫�������ڴ��е�idΨһ  
		 * ��list.add ������  ����һֱ�������idȥѰ��  
		 * Ҳ���ǵ�һ��new�������Ǹ�
		 */
		return booklist;
		
	}
}
