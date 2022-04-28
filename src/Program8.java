import java.util.Arrays;
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
		System.out.println("\nSetting available resouces: "+Arrays.toString(availableResources));
		
		String selection = test.getMenuSelection();
		while(!selection.equalsIgnoreCase("Q")) {
			test.processRequest(selection, banker);
			selection = test.getMenuSelection();
		}
		
		System.out.println("Goodbye");
	}
	
	private void processRequest(String command, Banker banker) {
		String cleaned = command.trim().replaceAll("\\s+", " ");
		String[] commandArgs = cleaned.split(" ");
		if(commandArgs.length == (2 + NUMBER_OF_RESOURCES)) {
			String option = commandArgs[0];
			String customerNum = commandArgs[1];
			String resourceArgs = Arrays.toString(Arrays.copyOfRange(commandArgs, 2, commandArgs.length));
			if(isValidCommand(option, customerNum, resourceArgs)) {
				System.out.println("ALL GOOD");
			}
			
		} else {
			System.out.println("[ INVALID COMMAND LENGTH ]");
		}
		
	}
	
	private boolean isValidCommand(String option, String customerNumber, String resources) {
		boolean isValid = true;
		
		// validate option 
		if(!option.equalsIgnoreCase("RQ") && !option.equalsIgnoreCase("RL") 
				&& !option.equalsIgnoreCase("OP")) {
			isValid = false;
			System.out.println("[ INVALID OPTION ("+option+") :: MUST BE RQ, RL, OP, OR Q (QUIT) ]");
		}
		
		// validate customer number
		try {
			int customerNum = Integer.parseInt(customerNumber);
			if(customerNum < 1 || customerNum > NUMBER_OF_CUSTOMERS) {
				isValid = false;
				System.out.println("[ INVALID CUSTOMER NUMBER ("+customerNum+") :: MUST BE BETWEEN 1 - "+NUMBER_OF_CUSTOMERS+" ]");
			}
		} catch (NumberFormatException e) {
			isValid = false;
			System.out.println("[ INVALID CUSTOMER NUMBER ("+customerNumber+") :: MUST BE AN INTEGER ]");
		}
		
		// validate resource request
		if(!isValidArray(getIntArray(resources), NUMBER_OF_RESOURCES)) {
			isValid = false;
			System.out.println("[ INVALID RESOURCE REQUEST "+resources+" ]");
		}
		
		return isValid;
	}
	
	private String getMenuSelection() {
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
		System.out.println("---------------------------------------------------");
		System.out.println("Ex: 10 5 7 8");
		int[] available = getIntArray(getUserInput());
		while(!isValidArray(available, NUMBER_OF_RESOURCES)) {
			System.out.println("Must enter " + NUMBER_OF_RESOURCES + " positive integers");
			available = getIntArray(getUserInput());
		}
		return available;
	}
	
	private int[] getIntArray(String input) {
		String str = input.replaceAll("\\[|\\]|,", " "); // handle [1, 2, 3] format
		String[] arr = str.trim().split("\\s+"); // handle 1 2 3 format
		int[] newArray = new int[arr.length];
		for(int i = 0; i < arr.length; i++) {
			try {
				newArray[i] = Integer.parseInt(arr[i]);
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
	
	private boolean isValidArray(int[] input, int expectedLength) {
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