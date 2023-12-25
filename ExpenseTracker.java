/**
 * ExpenseTracker.java
 * Personal Expense Tracker program.
 *
 * @author Kartikeya Arvind Yadav
 * @version 1.0
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseTracker {
    
    // Main method

    public static void main(String[] args){

    // Scanner class to take user input
    Scanner keyboardInput = new Scanner(System.in);

    //Loading expenses from the expenses.txt file
    ArrayList<Expense> expenses = ExpenseFile.loadExpensesEntry();

    // Using a forever loop using while to get the Personal Expense Tracker Menu
    while (true) {
        System.out.println("\nPersonal Expense Tracker Menu:");
        System.out.println("1. Add Expense Entry");
        System.out.println("2. View All Expense Entries");
        System.out.println("3. Delete Expense Entry");
        System.out.println("4. Update Date, Description and Amount for an Expense Entry");
        System.out.println("5. Close Personal Expense Tracker");
        System.out.print("Enter your choice: ");

        // Taking user input from the given menu
        int choice = keyboardInput.nextInt();

        // Starting a switch case to perform task according to the menu option selected
        switch (choice) {
            // Case for adding expenses.
            case 1:

                // print statements to ask user to enter date, description and amount in USD
                System.out.print("Enter date in (YYYY-MM-DD) format: ");
                String date = keyboardInput.next();
                // Clearing Buffer
                keyboardInput.nextLine();  
                System.out.print("Enter description of what you purchased: ");
                String desc = keyboardInput.nextLine();
                System.out.print("Enter amount in USD: ");
                double amt = keyboardInput.nextDouble();

                // double variable for amount in INR
                double amt_inr = 0;

                // Getting the current INR rate using the method declared in Expense Class
                for (Expense expense : expenses) {
                     amt_inr = expense.getAmtInINR();
                }

                // Adding the entry make to the enpenses.txt file
                expenses.add(new Expense(date, desc, amt, amt_inr));

                // Saving the entry in the ExpenseStorage Class
                ExpenseFile.saveExpensesEntry(expenses);

                // Printing list of all expenses
                System.out.println(expenses.toString());
                break;

            // Case for viewing the expense made
            case 2:
                // Logic to View the expense made in the format : date, description, Amount in USD, Amount in INR 

                // Opening the expenses.txt file, reading and printing it the result line by line
                String filePath = "Expenses.txt";
                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                int index = 0;
                System.out.print("Idx   |Date     |Desc |USD |INR");
                while ((line = reader.readLine()) != null) {
                    System.out.print("\n");
                    System.out.print(index + ":    "); //Adding index of the entry
                    System.out.println(line);
                    index++;
                }
                } catch (IOException e) {
                e.printStackTrace();
                }
                break;

            // Case to delete expense entry
            case 3:
                System.out.print("Enter the index of the expense entry you want to delete: ");
                int index = keyboardInput.nextInt();
                if (index >= 0 && index < expenses.size()) {
                    expenses.remove(index);
                    ExpenseFile.saveExpensesEntry(expenses);
                    System.out.println("Expense entry deleted successfully.");
                } else {
                    System.out.println("Invalid index. Please try again.");
                }
                break;
            
            // Case for updating date, description and amount
            case 4:
                System.out.print("Enter the index of the expense entry you want to update: ");
                int indexUpdate = keyboardInput.nextInt();
                if (indexUpdate >= 0 && indexUpdate < expenses.size()){
                    System.out.println("Enter the field which you want to update");
                    System.out.println("1. Update Date");
                    System.out.println("2. Update Desciption");
                    System.out.println("3. Update Amount in USD");
                    
                    // User input to update the field
                    int updateField = keyboardInput.nextInt();
                    keyboardInput.nextLine(); // Clear buffer

                    // Retrives expense object from ArrayList at the specified index
                    Expense expense = expenses.get(indexUpdate);

                    switch (updateField) {
                        case 1:
                            System.out.println("Enter New Date in (YYYY-MM-DD) format");
                            String updateDate = keyboardInput.next();
                            expense.setDate(updateDate);
                            break;
                        case 2:
                            System.out.println("Enter New Description for expense entry");
                            String updateDesc = keyboardInput.nextLine();
                            expense.setDesc(updateDesc);
                            break;
                        case 3:
                            System.out.println("Enter New Amount for expense entry");
                            double updateAmt = keyboardInput.nextDouble();
                            expense.setAmt(updateAmt);
                            break;
                        default:
                            System.out.println("Invalid choice. Please select from the menu.");
                    }
                    ExpenseFile.saveExpensesEntry(expenses);
                    System.out.println("Expense updated successfully.");
                    } else {
                    System.out.println("Invalid index. Please try again.");
                     }   
                break;
            
            // Case to exit the Personal Expense Tracker
            case 5:
                System.out.println("Closing Personal Expense Tracker!");
                return;

            // Default case to handle invalid input from user
            default:
                System.out.println("Invalid choice. Please select from the menu.");
        }


}
}
}