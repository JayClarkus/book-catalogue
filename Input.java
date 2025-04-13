package main;

import java.util.Scanner;

public class Input {
	
	public int intValidation(Scanner scanner, String prompt) {
		
		int userInput = 0;
		boolean valid = false;
		
		while (!valid) {
			System.out.print(prompt);
			if (scanner.hasNextInt()) {
				userInput = scanner.nextInt();
				scanner.nextLine();
				valid = true;
			}
			else {
				System.out.println("Invalid input, try again...");
				System.out.println();
				scanner.next();
			}
		}
		return userInput;
	}
	
	public long longValidation(Scanner scanner, String prompt) {
		
		long userInput = 0;
		boolean valid = false;
		
		while (!valid) {
			System.out.print(prompt);
			if (scanner.hasNextLong()) {
				userInput = scanner.nextLong();
				scanner.nextLine();
				valid = true;
			}
			else {
				System.out.println("Invalid input, try again...");
				System.out.println();
				scanner.next();
			}
		}
		return userInput;
	}
	
	public boolean booleanValidation(Scanner scanner, String prompt) {
		
		boolean userInput = true;
		boolean valid = false;
		
		while (!valid) {
			System.out.print(prompt);
			if (scanner.hasNextBoolean()) {
				userInput = scanner.nextBoolean();
				valid = true;
			}
			else {
				System.out.println("Invalid input, try again...");
				System.out.println();
				scanner.next();
			}
		}
		return userInput;
	}
}