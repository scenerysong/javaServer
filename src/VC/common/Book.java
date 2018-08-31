package VC.common;

public class Book implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2241529224304883586L;

	
	private String bookID;
	private String bookName;
	private String bookAuthor;
	private String bookPublisher;

	public Book() {
		
	}
	
	public Book(Book book) {
		this.setBookAuthor(book.getBookAuthor());
		this.setBookName(book.getBookName());
		this.setBookPublisher(book.getBookPublisher());
	}
	
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookPublisher() {
		return bookPublisher;
	}

	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}
	
	public String toString() {
		return "Book [bookName=" + bookName + ", bookAuthor=" + bookAuthor
				 + ", bookPublisher=" + bookPublisher ;
	}

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}
	
}
