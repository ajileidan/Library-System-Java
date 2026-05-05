package part01;

import java.util.Scanner;

public class QUBLibrary {

	private static Library mcClay;
	private static Scanner input = new Scanner(System.in);
	private static Menu appMenu;

	private static Library initialise() {
		Library aLibrary = new Library();
		LibraryBook book1 = new LibraryBook("the night without light", "Homer Simpson", "1234567890",
				BookType.REFERENCE, 2, "A novel speaks apout a life you never have", 4.29, "Homer.png");
		LibraryBook book2 = new LibraryBook("Learn programming in 3 days", "Marge Simpson", "0987654321 ",
				BookType.FICTION, 6, "A book trying to teach programming", 16.99, "Marge.png");
		LibraryBook book3 = new LibraryBook("The coup of machine", "Bart Simpson", "1230987654", BookType.NON_FICTION,
				5, "A gr arsa iugbklj gkjas", 0.99, "Bart.png");
		LibraryBook book4 = new LibraryBook("privacy in a naked world", "Lisa Simpson", "0981234567", BookType.FICTION,
				1, "JGHA SJDG FJKSHGJGS SSS SF", 3.69, "Lisa.png");
		LibraryBook book5 = new LibraryBook("the art of words", "Maggie Simpson", "1029384756", BookType.NON_FICTION, 3,
				"sdaklhfkjvhlkajheflkjhlka", 99, "Maggie.png");

		aLibrary.add(book1);
		aLibrary.add(book2);
		aLibrary.add(book3);
		aLibrary.add(book4);
		aLibrary.add(book5);
		return aLibrary;
	}

	private static Menu setupMenu() {
		String options[] = { "List All Books", "List Books By Status ", "Add a Book", "Remove a Book", "Borrow a Book",
				"Return a Book", "Display Ranked List", "Exit" };
		Menu mnu = new Menu("Library Manager", options);
		return mnu;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mcClay = initialise();
		appMenu = setupMenu();
		boolean done = false;

		do {
			int choice = appMenu.getUserChoice();
			switch (choice) {
			case 1:
				listAllBooks();
				break;
			case 2:
				listBooksStat();
				break;
			case 3:
				addNewBook();
				break;
			case 4:
				removeBook();
				break;
			case 5:
				borrowBook();
				break;
			case 6:
				returnBook();
				break;
			case 7:
				listBooksRanking();
				break;
			case 8:
				done = true;
				break;
			default:
				System.out.println("Invalid Option");
			}
		} while (!done);
		System.out.println("Goodbye!");
	}

	private static void listAllBooks() {
		System.out.println();
		System.out.println("All Books:");
		LibraryBook[] books = mcClay.list();
		for (LibraryBook str : books) {
			if (str != null) {
				System.out.println("--->" + str);
			}
		}
		System.out.println("***************");
		System.out.println();
	}

	private static void listBooksStat() {
		BookStatus wanted = BookStatus.AVAILABLE;
		System.out.println();
		String options[] = { "Available books", "on loan books", "Withdrwn books ", "Exit" };
		Menu mnu = new Menu("List Books by Status.", options);

		boolean done = false;
		do {
			int choice = mnu.getUserChoice();
			switch (choice) {
			case 1:
				System.out.println();
				System.out.println("Avaliable Books:");
				done = true;
				break;
			case 2:
				System.out.println();
				System.out.println("On-Loan Books:");
				wanted = BookStatus.ON_LOAN;
				done = true;
				break;
			case 3:
				System.out.println();
				System.out.println("Withdrawn Books:");
				wanted = BookStatus.WITHDRAWN;
				done = true;
				break;
			case 4:
				done = true;
				break;
			default:
				System.out.println("Invalid Option");

			}

		} while (!done);

		LibraryBook[] books = mcClay.list(wanted);
		for (LibraryBook str : books) {
			if (str != null) {
				System.out.println("--->" + str);
			}
		}
		if (books[0] == null) {
			System.out.println("There is no " + wanted + " books");
		}
		System.out.println();
	}

	private static void addNewBook() {
		// variables for the construction
		String title = null;
		String author = null;
		String isbn;
		BookType type = BookType.FICTION;
		int edition;
		String summary;
		double price;
		String image;

		boolean valid = false; // For the validation loops

		System.out.println("Add new Book.\n");

		// Title Validation
		do {
			System.out.print("--->Enter The Title : ");
			title = input.nextLine();
			if (title.length() >= 5 && title.length() <= 40) {
				break;
			} else {
				System.out.println("#!Title must be between 5 and 10 characters!#");
			}
		} while (!valid);

		// Author Validation
		do {
			System.out.print("--->Enter Author: ");
			author = input.nextLine();
			if (author.length() >= 5 && author.length() <= 40) {
				break;
			} else {
				System.out.println("#!Author must be between 5 and 10 characters!#");
			}
		} while (!valid);

//	
//		ISBN Validation
		do {
			System.out.print("--->Enter The ISBN: ");
			isbn = input.nextLine();
			if (isbn.length() == 10 && isbn.matches("\\d+")) {
				break;
			} else {
				System.out.println("#!ISBN must be 10 degits!#");
			}
		} while (!valid);

		//
		// BookType Validation
		do {
			System.out.println("---\\/Book Types : ");
			System.out.println("1.Fiction\n2.Non Fiction\n3.Refrence: ");
			System.out.print("--->Enter Book Type : ");
			while (!input.hasNextInt()) {
				System.out.print("Invalid input. Please enter a valid integer:");
				input.next(); // Consume the invalid input to avoid an infinite loop
			}
			int choice = input.nextInt();
			switch (choice) {
			case 1:
				type = BookType.FICTION;
				valid = true;
				break;
			case 2:
				type = BookType.NON_FICTION;
				valid = true;
				break;
			case 3:
				type = BookType.REFERENCE;
				valid = true;
				break;
			default:
				System.out.println("Invalid Option");
			}
		} while (!valid);

		valid = false; // reset for the next loop

		input.reset();// ???

		// Edition Validation
		do {
			System.out.print("--->Enter Edition:");
			while (!input.hasNextInt()) {
				System.out.print("Invalid input. Please enter a valid integer:");
				input.next(); // Consume the invalid input to avoid an infinite loop
			}
			edition = input.nextInt();
			if (edition >= 1) {
				break;
			} else {
				System.out.println("#!Edition must be 1 or more!#");
			}
		} while (!valid);

		input.nextLine();

		// Summary Validation
		do {
			System.out.print("--->Enter Summary:");
			summary = input.nextLine();
			if (summary.length() >= 20 && summary.length() <= 150) {
				break;
			} else {
				System.out.println("#!Summary must be between 20 and 150 characters!#");
			}
		} while (!valid);

		input.reset();

		// Price Validation
		do {
			System.out.print("--->Enter Price:");
			while (!input.hasNextDouble()) {
				System.out.print("Invalid input. Please enter a valid Price:");
				input.next(); 
			}
			price = input.nextDouble();
			if (price > 0) {
				break;
			} else {
				System.out.println("#!Price must be more than 0£");
			}
		} while (!valid);

		input.nextLine();

		System.out.print("--->Enter Image Source:");
		image = input.nextLine();

		//
		LibraryBook book = new LibraryBook(title, author, isbn, type, edition, summary, price, image);
		mcClay.add(book);
	}

	private static void removeBook() {
		System.out.println();
		System.out.println("Remove a book.");
		System.out.print("--->Enter ID:");
		while (!input.hasNextDouble()) {
			System.out.print("Invalid input. Please enter a valid integer:");
			input.next(); 
		}
		int id = input.nextInt();
		LibraryBook book = mcClay.search(id);
		if (book == null) {
			System.out.println("Book with id: " + id + " does not exist.");
		} else {
			if (book.getStatus() == BookStatus.AVAILABLE) {
				book.setStatus(BookStatus.WITHDRAWN);
				System.out.println("->Book have Been Removed succssufly<-");
			} else if (book.getStatus() == BookStatus.ON_LOAN) {
				System.out.println("#!Book is on loan can not be removed!#");
			} else {
				System.out.println("#!Book is previsoly removed!#");
			}

		}
		System.out.println();
	}

	private static void borrowBook() {
		System.out.println();
		System.out.println("Borrow a book");
		System.out.println("Available Books:");
		LibraryBook[] books = mcClay.list(BookStatus.AVAILABLE);
		for (LibraryBook str : books) {
			if (str != null) {
				System.out.println("--->" + str);
			}
		} 
		if (books == null) {
			System.out.println("there is no avaliable books to borrow");
		}
		System.out.print("Yor choice: ");
		while (!input.hasNextDouble()) {
			System.out.print("Invalid input. Please enter a valid integer:");
			input.next(); 
		}
		int id = input.nextInt();

		if (mcClay.borrowBook(id -1)) {
			System.out.println("->Borrow completed<-");
		} else {
			System.out.println("#!The chosen Book is either On-Loan or Withdrawn!#");
		}
		System.out.println();
	}

	private static void returnBook() {
		System.out.println();
		System.out.println("Return a book");
		System.out.println("On Loan Books:");
		LibraryBook[] books = mcClay.list(BookStatus.ON_LOAN);
		for (LibraryBook str : books) {
			if (str != null) {
				System.out.println("--->" + str);
			}
		}
		System.out.print("Yor choice: ");
		while (!input.hasNextDouble()) {
			System.out.print("Invalid input. Please enter a valid integer:");
			input.next(); 
		}
		int id = input.nextInt();

		if (mcClay.returnBook(id - 1)) {
			System.out.println("Return completed");
		} else {
			System.out.println("#!The chosen Book is not On-Loan!#");
		}
		System.out.println();
	}

	private static void listBooksRanking() {
		System.out.println("List Books by poularity.\n");
		LibraryBook[] books = mcClay.mostPopular();
		for (LibraryBook str : books) {
			if (str != null) {
				System.out.println("---> Title: " + str.getTitle() + ", Author: " + str.getAuthor() + ", Loan count: "
						+ str.getLoanCount());
			}
		}
		System.out.println();
	}

}
