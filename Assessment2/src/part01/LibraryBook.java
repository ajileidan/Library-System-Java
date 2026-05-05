package part01;

public class LibraryBook extends Book implements Lendable {
	private int id;
	private static int nextId = 1;
	private BookStatus status;
	private String image;
	private int loanCount;
	
	//The constructor
	public LibraryBook(String title, String author, String isbn, BookType type, int edition, String summary,
						double price, String image) {
		
		super(title, author, isbn, type, edition, summary, price);
		this.id = nextId++;
		setStatus(BookStatus.AVAILABLE);
		setImage(image);
		this.loanCount = 0;
	}
	//The Setters
	public void setStatus(BookStatus status) {
		this.status = status;
	}
	public void setImage(String image) {
		this.image = image;
	}
	//The Getters
	public int getId() {
		return id;
	}
	public BookStatus getStatus() {
		return status;
	}
	public String getImage() {
		return image;
	}
	public int getLoanCount() {
		return loanCount;
	}
	@Override 
	public boolean checkout() {
		if (status == BookStatus.AVAILABLE) {
			status = BookStatus.ON_LOAN;
			loanCount++;
			return true;
		} else {
			return false;
		}
	}
	@Override
	public boolean checkIn() {
		if (status == BookStatus.ON_LOAN) {
			status = BookStatus.AVAILABLE;
			return true;
		} else { return false; }
	}
	//toString method
	@Override
	public String toString() {
		return "ID: " + id + ", "+ super.tostring() + ", Status: " + status + 
				", Loan Count: " + loanCount + ", Image: " + image;
	}
}
