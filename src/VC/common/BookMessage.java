package VC.common;

import java.util.List;

/**
 * 设置、获取书籍的属性
 * 
 * @author lin
 */
public class BookMessage extends Message {

	private static final long serialVersionUID = 3978440929024014494L;
	private String bookname;
	private String bookauthor;
	private Book book;

	private List<Book> booklist;

	public BookMessage() {

	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getBookauthor() {
		return bookauthor;
	}

	public void setBookauthor(String bookauthor) {
		this.bookauthor = bookauthor;
	}

	public List<Book> getBooklist() {
		return booklist;
	}

	public void setBooklist(List<Book> booklist) {
		this.booklist = booklist;
	}

}
