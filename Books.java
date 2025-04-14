package main;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Books {
	
	String catalogueDate;
	String zv;
	int yearPublished;
	long isbn;
	String callNumber;
	String title;
	String alternativeTitle;
	String author;
	String publishingLocation;
	String publishingCompany;
	int pages;
	boolean illustrations;
	int size;
	String description;
	int numberOfTags;
	
	HashMap<String, ArrayList <String>> tags = new HashMap<String, ArrayList <String>>();
	ArrayList<String> tagCollection = new ArrayList<String>();
	Input input = new Input();
	
	public void catalogue() {
		
		Scanner scannerTwo = new Scanner(System.in);
		
		System.out.print("Enter the date the current date (MM/DD/YEAR): ");
		catalogueDate = scannerTwo.nextLine();
		
		System.out.print("Enter your zv code: ");
		zv = scannerTwo.nextLine();
		
		yearPublished = input.intValidation(scannerTwo, "Enter year the book was published (2025): ");
		
		isbn = input.longValidation(scannerTwo, "Enter the ISBN (no dashes): ");
		
		System.out.print("Enter the book's call number: ");
		callNumber = scannerTwo.nextLine();
		
		System.out.print("Enter the books Title: ");
		title = scannerTwo.nextLine();
		
		System.out.print("Does the book have an alternative title? (y/n): ");
		String alternative = scannerTwo.nextLine();
		
		if (alternative.equals("y")) {
			System.out.print("Enter the books alternative title: ");
			alternativeTitle = scannerTwo.nextLine();
		}
		
		System.out.print("Enter the authors name: ");
		author = scannerTwo.nextLine();
		
		System.out.print("Enter the publishing location: ");
		publishingLocation = scannerTwo.nextLine();
		
		System.out.print("Enter the name of the publishing company: ");
		publishingCompany = scannerTwo.nextLine();
		
		pages = input.intValidation(scannerTwo, "Enter the number of pages: ");
		
		illustrations = input.booleanValidation(scannerTwo, "Does the book have illustrations? (true/false): ");
		
		size = input.intValidation(scannerTwo, "Enter the book cover's height (in centimeters): ");
		
		System.out.print("Write a summary of the book: ");
		description = scannerTwo.nextLine();
		
		numberOfTags = input.intValidation(scannerTwo, "How many tags would you like to include?: ");
		
		for (int i = 0 ; i < numberOfTags ; i++) {
			System.out.print("Enter a tag: ");
			String tag = scannerTwo.nextLine();
			tagCollection.add(tag);
			tags.put(title, tagCollection);
		}
		
		String yearPublishedString = String.valueOf(yearPublished);
		String isbnString = String.valueOf(isbn);
		String pagesString = String.valueOf(pages);
		String sizeString = String.valueOf(size);
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(title + " Record.txt"));
			writer.write("Catalogue Date:\t" + catalogueDate + "\n");
			writer.write("001:\t" + "zv_" + zv + "\n");
			writer.write("020:\t" + isbnString +"\n");
			writer.write("099:\t" + callNumber + " " + title + "\n");
			writer.write("245:\t" + title + " / " + author +"\n");
			writer.write("246:\t" + alternativeTitle + "\n");
			writer.write("260:\t" + publishingLocation + " : " + publishingCompany + ", " + yearPublishedString + "\n");
			if (illustrations) {
				String illustrationsString = "Illustrations ;";
				writer.write("300:\t" + pagesString + " pages : " + illustrationsString + " ; " + sizeString + "cm.\n");
			}
			else {
				writer.write("300:\t" + pagesString + " pages : " + " ; " + sizeString + "cm.\n");
			}
			writer.write("336:\t" + "text | txt | rdacontent\n");
			writer.write("337:\t" + "unmediated | n | rdamedia\n");
			writer.write("338:\t" + "volume | nc | rdacarrier\n");
			writer.write("520:\t" + description + "\n");
			for (String i : tagCollection) {
				writer.write("650:\t" + i + "\n");
			}
			writer.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
