package VC.common;

import java.util.List;

public class BookMessage extends Message{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1428457839957205171L;
	private String bookname;
	private String bookauthor;
	private Book book;
	
	private List<Book> bookdata;
	
	public BookMessage(){
		
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


	public List<Book> getBookdata() {
		return bookdata;
	}

	public void setBookdata(List<Book> bookdata) {
		this.bookdata = bookdata;
	}
	
	
}
