package main;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
    public static void main(String[] args) {
    	
        Books myBook = new Books();
        ArrayList<String> bookCollection = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        Input input = new Input();
        
        boolean run = true;
        int menu;
        int search;
        int subMenu;
        
        while (run) {
        	
        	divider();
        	System.out.println("1. Add a new book");
        	System.out.println("2. Delete a book");
        	System.out.println("3. View all books");
        	System.out.println("4. Search for a book");
        	System.out.println("5. Import a record");
        	System.out.println("6. Exit");
        	System.out.println();
        	menu = input.intValidation(scanner, "Enter a number (1-6): ");
        	
        	if (menu == 1) {
        		divider();
        		
        		myBook.catalogue();
            bookCollection.add(myBook.title);
            
        	}
        	else if (menu == 2) {
        		divider();
        		
        		System.out.println("0. Cancel");
        		
        		for (int i=0; i < bookCollection.size(); i++) {
        			System.out.printf("%d. " + bookCollection.get(i) + "\n", i+1);
        		}
        		
        		System.out.println();
        		System.out.println();
        		
        		subMenu = input.intValidation(scanner, "Enter which Book you would like to delete: ");
        		
        		if (subMenu == 0) {
        			//Intentionally left blank
        		}
        		else {
        			bookCollection.remove(subMenu-1);
        		}
        		
        		System.out.println();
        		System.out.println();
        		
        	}
        	else if (menu == 3) {
        		divider();
        		
        		for (int i=0; i < bookCollection.size(); i++) {
        			System.out.printf("%d. " + bookCollection.get(i) + "\n", i+1);
        		}
        		
        		System.out.println();
        		System.out.println();

        	}
        	else if (menu == 4) {
        		divider();
        		
        		System.out.println("0. Cancel");
        		System.out.println("1. Search by Title");
        		System.out.println("2. Search by Tag");
        		System.out.println();
        		search = input.intValidation(scanner, "Enter seach method (0-2): ");
        		
        		if (search == 0) {
        			//Intentionally left blank
        		}
        		else if (search == 1) {
        			System.out.print("Enter book's Title: ");
        			String keyWord = scanner.nextLine();
        			
        			if (myBook.tags.containsKey(keyWord)) {
        				System.out.println(keyWord + ": " + myBook.tags.get(keyWord));
        			}
        			else {
        				System.out.println("No matching Titles");
        			}
        		}
        		else if (search == 2) {
        			System.out.print("Enter book's Tag: ");
        			String keyWord = scanner.nextLine();
        			
        			for (String i : myBook.tags.keySet()) {
        	            ArrayList<String> tags = myBook.tags.get(i);

        	            if (tags.contains(keyWord)) {
        	                System.out.println("Found in: " + i);
        	            }
        	            else {
        	            		System.out.println("No books with matching tags");
        	            }
        	        }
        		}
        		else {
        			System.out.println("Invalid input...");
        			System.out.println();
        		}
        		
        	}
        	else if (menu == 5) {
        		divider();
        		
        		System.out.print("Enter the directory of the record you would like to import: ");
            	String importedRecord = scanner.nextLine();
            	importedRecord = importedRecord.replace("\\", "\\\\");
            	
            	try {
            		BufferedReader reader = new BufferedReader(new FileReader(importedRecord));
            		
            		String line;
            		String tag;
            		String title = null;
            		
            		while((line = reader.readLine()) != null) {
            			if (line.startsWith("245:\t")) {
            				title = line.substring(5).split("/")[0].trim();
            				bookCollection.add(title);
            			}
            			else if (line.startsWith("650:\t")) {
            				tag = line.substring(5).split("/")[0].trim();
            				myBook.tagCollection.add(tag);
            				myBook.tags.put(title, myBook.tagCollection);
            			}
            		}
            	
            	}
            	catch (IOException e){
            		e.printStackTrace();
            	}
        		
        	}
        	else if (menu == 6) {
        		run = false;
        	}
        	else {
        		System.out.println("Invalid Input, try again...");
        	}
        	
        }
        
        scanner.close();
        
    }
    
    public static void divider() {
    	System.out.println("----------------------------------");
    	System.out.println();
    }
    
}