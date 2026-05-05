package part02;

import java.awt.Color;
import java.awt.Font;
import java.util.Scanner;

import javax.swing.ImageIcon;

import console.Console;
import part01.BookStatus;
import part01.BookType;
import part01.Library;
import part01.LibraryBook;

public class QUBLibraryUpdated {

	private static Library mcClay;
	private static Menu appMenu;
	private static Console con = new Console(true);;

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
		Menu mnu = new Menu("QUB Library manager (Main Window)", options);
		return mnu;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mcClay = initialise();
		appMenu = setupMenu();
		boolean done = false;

		con.setSize(1400, 460);
		con.setLocation(0, 400);

		con.setBgColour(Color.WHITE);
		con.setColour(Color.RED);
		con.setTitle("QUB Library manager");

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
				printError("Invalid Option");
			}
		} while (!done);
		System.out.println("Goodbye!");
	}

	private static void listAllBooks() {
		setUp("List All Books");//set Up the console and print the title

		LibraryBook[] books = mcClay.list();
		for (LibraryBook str : books) {
			if (str != null) {
				con.println("--->" + str);
			}
		}
		con.println();
	}

	private static void listBooksStat() {
		setUp("List Books By Status");

		con.println("Books Status: ");

		BookStatus wanted = BookStatus.AVAILABLE;

		con.println("1.Available books\n" + "2.on loan books\n" + "3.Withdrwn books\n");

		boolean done = false;
		boolean notEmpty = false;

		do {
			// input Validation
			int choice = getUserChoice("Selection");

			switch (choice) {
			case 1:
				con.println();
				con.println("Avaliable Books:");
				done = true;

				break;
			case 2:
				con.println();
				con.println("On-Loan Books:");
				wanted = BookStatus.ON_LOAN;
				done = true;

				break;
			case 3:
				con.println();
				con.println("Withdrawn Books:");
				wanted = BookStatus.WITHDRAWN;
				done = true;

				break;
			default:
				printError("Invalid Option");

			}

		} while (!done);

		LibraryBook[] books = mcClay.list(wanted);
		for (LibraryBook str : books) {
			if (str != null) {
				con.println("--->" + str);
				notEmpty = true;
			}
		}
		if (!notEmpty) {
			con.println("There is no " + wanted + " books");
		}
		con.println();
	}

	private static void addNewBook() {
		setUp("Add a New Book");
		// variables for the construction
		String title = null;
		String author = null;
		String isbn;
		BookType type = BookType.FICTION;
		int edition;
		String summary;
		double price = 0;
		String image;

		boolean valid = false; // For the validation loops


		// Title Validation
		do {
			con.print("Enter The Title : ");
			title = con.readLn();
			title = title.trim();
//			title = input.nextLine();
			if (title.length() >= 5 && title.length() <= 40) {
				break;
			} else {
				printError("#!Title must be between 5 and 40 characters!#");
			}
		} while (!valid);

		// Author Validation
		do {
			con.print("Enter Author: ");
			author = con.readLn();
			author = author.trim();
			if (author.length() >= 5 && author.length() <= 40) {
				break;
			} else {
				printError("#!Author must be between 5 and 40 characters!#");
			}
		} while (!valid);

//	
//		ISBN Validation
		do {
			con.print("Enter The ISBN: ");
			isbn = con.readLn();
			if (isbn.length() == 10 && isbn.matches("\\d+")) {
				break;
			} else {
				printError("#!ISBN must be 10 degits!#");
			}
		} while (!valid);

		//
		// BookType Validation
		do {
			con.println("-Book Types : ");
			con.println("1.Fiction\n2.Non Fiction\n3.Refrence: ");

			int choice = getUserChoice("Book Type");
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
				printError("Invalid Option");
			}
		} while (!valid);

		valid = false; // reset for the next loop

		// Edition Validation
		do {
			edition = getUserChoice("Book Edition");
			if (edition >= 1) {
				break;
			} else {
				printError("#!Edition must be 1 or more!#");
			}
		} while (!valid);

		// Summary Validation
		do {
			con.print("Enter Summary:");
			summary = con.readLn();
			if (summary.length() >= 20 && summary.length() <= 150) {
				break;
			} else {
				printError("#!Summary must be between 20 and 150 characters!#");
			}
		} while (!valid);

		// Price Validation
		do {

			con.print("Enter Price:");
			String text = con.readLn();
			text = text.trim();

			if (text.length() > 0 && text.matches("\\d*(\\.\\d+)?")) {
				try (Scanner input = new Scanner(text)) {
					double enteredPrice = input.nextDouble();
					if(enteredPrice > 0) {
						price = enteredPrice;
					break;
					} else {
						printError("#!Price must be more than 0.00£");
					}
				}
			} else {
				printError("Invalid Input");
			}

		} while (!valid);

		con.print("Enter Image Source:");
		image = con.readLn();

		con.println("The Book has succesfully added");

		//
		LibraryBook book = new LibraryBook(title, author, isbn, type, edition, summary, price, image);
		mcClay.add(book);
	}

	private static void removeBook() {
		setUp("Remove a Book");

		int id = getUserChoice("Book ID");

		LibraryBook book = mcClay.search(id);
		if (book == null) {
			con.println("Book with id: " + id + " does not exist.");
		} else {
			if (book.getStatus() == BookStatus.AVAILABLE) {
				book.setStatus(BookStatus.WITHDRAWN);
				con.println("->Book have Been Removed succssufly<-");
			} else if (book.getStatus() == BookStatus.ON_LOAN) {
				printError("#!Book is on loan can not be removed!#");
			} else {
				printError("#!Book is previsoly removed!#");

			}

		}
		con.println();
	}

	private static void borrowBook() {
		setUp("Borrow a book");
//		con.println("Borrow a book");
		con.println();

		con.println("Available Books:");
		boolean notEmpty = false;
		LibraryBook[] books = mcClay.list(BookStatus.AVAILABLE);
		for (LibraryBook str : books) {
			if (str != null) {
				con.println("--->" + str);
				notEmpty = true;
			}
		}
		if (!notEmpty) {
			printError("There is no Avaliable Books to Borrow");
		}

		if (notEmpty) {
			int id = getUserChoice("Book ID");

			if (mcClay.borrowBook(id - 1)) {
				con.println("->Borrow completed<-");
			} else {
				printError("#!The chosen Book is either On-Loan or Withdrawn!#");
			}
		}
	}

	private static void returnBook() {
		setUp("Return a book");
		con.println();

		con.println("On Loan Books:");
		boolean notEmpty = false;
		LibraryBook[] books = mcClay.list(BookStatus.ON_LOAN);
		for (LibraryBook str : books) {
			if (str != null) {
				con.println("--->" + str);
				notEmpty = true;
			}
		}
		if (!notEmpty) {
			printError("There is no On-Loan Books to Return");

		}

		if (notEmpty) {
			int id = getUserChoice("Book ID");

			if (mcClay.returnBook(id - 1)) {
				con.println("Return completed");
			} else {
				printError("#!The chosen Book is not On-Loan!#");
			}
		}
	}

	private static void listBooksRanking() {
		setUp("List Books by poularity");
		LibraryBook[] books = mcClay.mostPopular();
		for (LibraryBook str : books) {
			if (str != null) {
				con.println("---> Title: " + str.getTitle() + ", Author: " + str.getAuthor() + ", Loan count: "
						+ str.getLoanCount());
			}
		}
	}
// @return the type int choice from the console
// String wanted: the name of the choice 
	private static int getUserChoice(String wanted) {
		int value = 0;
		boolean valid = false;
		do {
			con.setColour(Color.RED);
			con.print("Enter " + wanted + " : ");
			String text = con.readLn();
			text = text.trim();

			//
			if (text.length() > 0 && text.matches("\\d+")) {
				try (Scanner input = new Scanner(text)) {
					int enteredvalue = input.nextInt();
					if (enteredvalue > 0) {
						value = enteredvalue;
						valid = true;
					}else {
						printError("input must be greater than zero");
					}
				}
				

			} else {
				printError("Invalid input please try Again");
			}
		} while (!valid);

		return value;
	}
	// print the error in a black color
	private static void printError(String error) {
		con.setColour(Color.BLACK);
		con.println(error);
		con.setColour(Color.RED);
	}
	
	//set Up the console and print the title
	private static void setUp(String title) {
		con.clear();
		con.setVisible(true);
		ImageIcon img = new ImageIcon("Images\\" + "QUB.png");
		con.println(img);
		con.setFont(new Font("Consolas", Font.ITALIC, 20));
		con.setColour(Color.BLUE);
		con.println("\t" + title);
		con.setColour(Color.RED);
		con.println();
		con.setFont(new Font("Consolas", Font.ROMAN_BASELINE, 13));
	}

}
