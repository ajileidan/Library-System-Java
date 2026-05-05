package part02;

import java.awt.Color;
import java.awt.Font;
import java.util.Scanner;

import javax.swing.ImageIcon;

import console.Console;

public class Menu {

	private String items[];
	private String title;

	Console con = new Console(true);

	public Menu(String title, String data[]) {
		this.title = title;
		this.items = data;
		
	}

	private void display() {
		con.clear();
		ImageIcon img = new ImageIcon("Images\\" + "QUB.png");
		
		con.println(img);
		con.setSize(500, 412);
		con.setLocation(0, 0);
		con.setVisible(true);
		con.setBgColour(Color.white);
		con.setColour(Color.RED);
		con.setFont(new Font("Consolas", Font.BOLD, 18));
		con.setTitle(title);

		con.println();
		for (int option = 1; option <= items.length; option++) {
			con.println(option + ". " + items[option - 1]);
		}
		
		con.println();
	}

	public int getUserChoice() {
		display();

		int value = 0;
		boolean valid = true;
		do {
			con.setColour(Color.RED);
			con.print("Enter Selection: ");
			String text = con.readLn();
			text = text.trim();

			if (text.length() > 0 && text.matches("\\d+")) {
				try (Scanner input = new Scanner(text)) {
					value = input.nextInt();
				}
				valid = true;

			} else {
				con.setColour(Color.BLACK);
				con.println("\nInvalid input please try Again\n");
				con.setColour(Color.RED);
				valid = false;
			}
		} while (!valid);

		return value;
	}
	public void closeMenu() {
		con.setVisible(false);
	}
}
