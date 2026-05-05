package part01;

import java.util.ArrayList;

public class Library {
	private ArrayList<LibraryBook> books;
	
	public  Library() {
		books = new ArrayList<LibraryBook>();
	}
	
	public boolean borrowBook(int id) {
		boolean result = books.get(id).checkout();
		return result;
	}
	public boolean returnBook(int id) {
		boolean result = books.get(id).checkIn();
		return result;
	}
	public LibraryBook[] list() {
		LibraryBook[] result = new LibraryBook[books.size()];
		for(int index=0; index<result.length; index++) {
			result[index] = books.get(index);
		}
		return result;
	}
	public LibraryBook[] list(BookStatus stat) {
		LibraryBook[] result = new LibraryBook[books.size()];
		for(int index=0; index<result.length; index++) {
			if (books.get(index).getStatus() == stat ) {
				result[index] = books.get(index);
			}
		}
		return result;
	}
	public LibraryBook[] mostPopular() {
		LibraryBook[] result = new LibraryBook[books.size()];
		for(int index=0; index<result.length; index++) {
			result[index] = books.get(index);
		}
		//comparing
		for(int j=0; j<result.length; j++) {
		for (int i=0; i<result.length -1; i++ ) {
			if(result[i].getLoanCount()<result[i+1].getLoanCount()) {
				LibraryBook temp = result[i];
				result[i] = result[i+1];
				result[i+1] = temp;
			}
		}
		}
	
		return result;
	}
	public LibraryBook search(int id) {
		LibraryBook target = null;
		for (int index = 0; index < books.size(); index++) {
			LibraryBook book = books.get(index);
			if (book.getId() == id) {
				target = book;
				break;
			}
		}
		return target;
	}
	public boolean remove(int id) {
		LibraryBook target = search(id);
		return books.remove(target);
	}
	public boolean add(LibraryBook bk) {
		if (bk != null) {
			LibraryBook temp = search(bk.getId());
			if (temp == null) {
				if(bk.getPrice()> 0.00) {
				books.add(bk);
				} else { return false; }
			} else { return false; }
		} else { return false; }
		return true;
	}
	
}
