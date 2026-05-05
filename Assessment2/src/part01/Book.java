package part01;

public class Book {
	
	private String title;
	private String author;
	private String isbn;
	private BookType type;
	private int edition;
	private String summary;
	private double price;
	


public Book(String title, String author,String isbn, BookType type, int edition, String summary, double price){
	setTitle(title);
	setAuthor(author);
	setIsbn(isbn);
	setType(type);
	setEdition(edition);
	setSummary(summary);
	setPrice(price);
	}
	public boolean setTitle(String title) {
		if (title.length() >= 5 && title.length() <= 40) {
			this.title = title;
			return true;
			} else { return false;}
	}
	public boolean setAuthor(String author) {
		if (author.length() >= 5 && author.length() <= 40) {
			this.author = author;
			return true;
			} else {return false;}
	}
	public boolean setIsbn(String isbn) {
		if (isbn.length() == 10 && isbn.matches("\\d+")) {
			this.isbn = isbn;
			return true;
			} else {return false;}
	}
	public void setType(BookType type) {
		this.type = type;
	}
	public boolean setEdition(int edition) {
		if (edition >= 1) {
			this.edition = edition;
			return true;
			} else {return false; }
	}
	public boolean setSummary(String summary) {
		if (summary.length() >= 20 && summary.length() <= 150) {
			this.summary = summary;
			return true;
			} else {return false;}
	}
	public void setPrice(double price) {
			this.price = price;
	}
	//The getters
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public String getIsbn() {
		return isbn;
	}
	public BookType getType() {
		return type;
	}
	public int getEdition() {
		return edition;
	}
	public String getSummary() {
		return summary;
	}
	public double getPrice() {
		return price;
	}
	public  String tostring() {
		return "Title: " + title + ", Author: "+author+", ISBN: "+isbn+", Type: " + type.toString() +", Edithion: "+edition+", Summary: "+ summary+ ", Price: "+ price +" £";
}
}