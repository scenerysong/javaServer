package VC.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import VC.common.Book;
import VC.common.Course;
import VC.server.db.DBstart;

public class LibraryDAO extends DBstart {

	public LibraryDAO() throws ClassNotFoundException, SQLException {
		super();
	}

	public List<Book> getBookByBookname(String bookname) throws SQLException {

		paras = new String[1];
		paras[0] = null;
		paras[0] = bookname;
		sql = "select * from books where bookname = ?";
		ps = ct.prepareStatement(sql);
		for (int i = 0; i < paras.length; i++) {
			ps.setString(i + 1, paras[i]);
		}
		rs = ps.executeQuery();

		Book book = new Book();
		List<Book> booklist = new ArrayList<Book>();

		while (rs.next()) {

			book = new Book();
			book.setBookName(rs.getString("bookname"));
			book.setBookAuthor(rs.getString("bookpublisher"));
			book.setBookPublisher(rs.getString("bookauthor"));

			booklist.add(book);
		}
		return booklist;
	}

	// borrow the book for the User.
	public boolean BorrowBook(String Users, String BookName) throws SQLException {
		sql = "select * from books where bookname = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, BookName);
		rs = ps.executeQuery();

		/* ；课程名称默认不重复 */
		Book book = new Book();
		if (rs.next()) {
			// book.setBookID(rs.getString("ID"));
			book.setBookName(rs.getString("bookname"));
			book.setBookAuthor(rs.getString("bookauthor"));
			book.setBookPublisher(rs.getString("bookpublisher"));
			sql = "insert into BookUser (bookname, bookpublisher, bookauthor, User) values (?, ?, ?, ?)";
			ps = ct.prepareStatement(sql);
			// ps.setString(1, book.getBookID());
			ps.setString(1, book.getBookName());
			ps.setString(2, book.getBookPublisher());
			ps.setString(3, book.getBookAuthor());
			ps.setString(4, Users);
			if (ps.executeUpdate() > 0)
				return true;
			else
				return false;
		} else
			return false;
	}

	// return all the books the user has borrowed
	public List<Book> GetAllMyBook(String Users) throws SQLException {
		sql = "select * from BookUser where User = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, Users);
		rs = ps.executeQuery();

		Book bk = new Book();
		List<Book> Bk = new ArrayList<Book>();

		while (rs.next()) {
			bk = new Book();

			// bk.setBookID(rs.getString("ID"));
			bk.setBookName(rs.getString("bookname"));
			bk.setBookPublisher(rs.getString("bookpublisher"));
			bk.setBookAuthor("bookauthor");

			Bk.add(bk);
		}
		return Bk;
	}

	public List<Book> getAllBook() throws SQLException {

		sql = "select * from books";
		ps = ct.prepareStatement(sql);
		rs = ps.executeQuery();

		Book bk = new Book();
		List<Book> bks = new ArrayList<Book>();

		while (rs.next()) {

			bk = new Book();
			bk.setBookName(rs.getString("bookname"));
			bk.setBookAuthor(rs.getString("bookauthor"));
			bk.setBookPublisher(rs.getString("bookpublisher"));

			bks.add(bk);
		}

		return bks;
	}

	// delete the record of borrowed book
	public boolean returnbook(String Users, String bookname) throws SQLException {
		sql = "delete from BookUser where User = ? and bookname = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, Users);
		ps.setString(2, bookname);
		if (ps.executeUpdate() > 0)
			return true;
		else
			return false;
	}
}
