import java.util.Scanner;

//********************************************************************
//
//  Author:        Jeremy Aubrey
//
//  Program #:     8
//
//  File Name:     Program8.java
//
//  Course:        COSC-4302 Operating Systems
//
//  Due Date:      05/12/2022
//
//  Instructor:    Fred Kumi 
//
//  Chapter:       9
//
//  Description:   TODO
//
//*********************************************************************

public class Program8 {

	private static final int NUMBER_OF_CUSTOMERS = 5;
	private static final int NUMBER_OF_RESOURCES = 4;
	
	private Scanner scan = new Scanner(System.in);	
	
	public static void main(String[] args) {
		
		Program8 test = new Program8();
		test.developerInfo();
		
		Banker banker = new Banker();
		
		int[] availableResources = test.setAvailableResources();
		
		String selection = test.getUserSelection();
		while(!selection.equalsIgnoreCase("Q")) {
			test.processRequest(selection, banker);
			selection = test.getUserSelection();
		}
		
		System.out.println("Goodbye");
	}
	
	private void printArr(int[] arr) {
		System.out.print("[");
		for(int i : arr) {
			System.out.print(" "+i+" ");
		}
		System.out.println("]");
	}
	
	private void processRequest(String command, Banker banker) {
		String cleaned = command.trim().replaceAll("\\s+", " ");
		int commandLength = cleaned.split(" ").length;
		if(commandLength == (2 + NUMBER_OF_RESOURCES)) {
			String option = cleaned.split(" ")[0];
			String customerId = cleaned.split(" ")[1];
			String resources = cleaned.substring(5);
			if(isValidCommand(option, customerId, resources)) {
				System.out.println("ALL GOOD");
			} else {
				System.out.println("[ INVALID COMMAND ARGS ]");
			}
			
		} else {
			System.out.println("[ INVALID COMMAND LENGTH ]");
		}
		
	}
	
	private boolean isValidCommand(String option, String customerNumber, String resources) {
		boolean isValid = true;
		if(!option.equalsIgnoreCase("RQ") && !option.equalsIgnoreCase("RL") 
				&& !option.equalsIgnoreCase("OP")) {
			isValid = false;
		}
		
		try {
			int customerNum = Integer.parseInt(customerNumber);
			if(customerNum < 0 || customerNum > NUMBER_OF_CUSTOMERS) {
				isValid = false;
			}
		} catch (NumberFormatException e) {
			isValid = false;
		}
		
		if(!isValidInts(getIntArray(resources), NUMBER_OF_RESOURCES)) {
			isValid = false;
		}
		
		return isValid;
	}
	
	private String getUserSelection() {
		displayMenu();
		String input = getUserInput().trim();
		return input;
	}
	
	private void displayMenu() {
		System.out.println("\nEnter a command");
		System.out.println("---------------");
		System.out.println("RQ (customer#)"+insertPlaceHolders()+"\t> (request resources)");
		System.out.println("RL (customer#)"+insertPlaceHolders()+"\t> (release resources)");
		System.out.println("OP \t\t\t> (output values)");
		System.out.println("\nEx: RQ 0 3 1 2 1\n");

	}
	
	private String insertPlaceHolders() {
		StringBuilder string = new StringBuilder();
		for(int i = 0; i < NUMBER_OF_RESOURCES; i++) {
			string.append(" #");
		}
		return string.toString();
	}
	
	private int[] setAvailableResources() {
		System.out.println("\nEnter the number of resources sepearated by a space");
		System.out.println("Ex: 10 5 7 8");
		int[] available = getIntArray(getUserInput());
		while(!isValidInts(available, NUMBER_OF_RESOURCES)) {
			System.out.println("Must enter " + NUMBER_OF_RESOURCES + " positive integers");
			available = getIntArray(getUserInput());
		}
		return available;
	}
	
	private int[] getIntArray(String input) {
		String[] str = input.trim().split("\\s+");
		int[] newArray = new int[str.length];
		for(int i = 0; i < str.length; i++) {
			try {
				newArray[i] = Integer.parseInt(str[i]);
			} catch (NumberFormatException e) {
				newArray[i] = -1;
				break;
			}
		}
		return newArray;
	}
	
	private String getUserInput() {
		System.out.print(": ");
		String input = scan.nextLine();
		return input;
	}
	
	private boolean isValidInts(int[] input, int expectedLength) {
		boolean isValid = true;
		if(input.length == expectedLength) {
			for(int num : input) {
				if(num < 0) {
					isValid = false;
					break;
				}
			}
			
		} else {
			isValid = false;
		}
		
		return isValid;
	}
	
	//***************************************************************
	//
	//  Method:       developerInfo (Non Static)
	// 
	//  Description:  The developer information method of the program.
	//
	//  Parameters:   None
	//
	//  Returns:      N/A 
	//
	//**************************************************************
	public void developerInfo()
	{
	   System.out.println("Name:    Jeremy Aubrey");
	   System.out.println("Course:  COSC 4302 Operating Systems");
	   System.out.println("Program: 8");

	}// end developerInfo method
	
}// end Program8 class