package VC.common;

/**
 * 书籍的属性
 * 
 * @author lin
 */
public class Book implements java.io.Serializable{
	
	private static final long serialVersionUID = -2241529224304883586L;

	private String bookID;
	
	private String bookName;
	
	private String bookAuthor;
	
	private String bookPublisher;
	
	private String bookNum;

	public Book() {
		
	}
	
	public Book(Book book) {
		this.setBookAuthor(book.getBookAuthor());
		this.setBookName(book.getBookName());
		this.setBookPublisher(book.getBookPublisher());
		this.setBookID(book.getBookID());
	}
	public String getBookNum() {
		return bookNum;
	}
	
	public void  setBookNum(String bookNum) {
		this.bookNum=bookNum;
	}
	
	public String getBookID() {
		return bookID;
	}
	
	public void  setBookID(String bookID) {
		this.bookID=bookID;
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
	
}
