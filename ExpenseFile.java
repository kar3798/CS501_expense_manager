/**
 * ExpenseFile.java
 * This class saves and loads to/from a text file.
 *
 * @author Kartikeya Arvind Yadav
 * @version 1.0
 */

 import java.io.*;
 import java.util.ArrayList;

public class ExpenseFile {
    
    private static final String fileName = "Expenses.txt";

    // Method to load expense entry from the text file
    public static ArrayList<Expense> loadExpensesEntry() {
        
        // Creating an object that manages an ArrayList of Expense object
        // ArrayList stores the data loaded from the text file
        ArrayList<Expense> expensesEntry = new ArrayList<>();
        try (BufferedReader expensesReader = new BufferedReader(new FileReader(fileName))) {
            String line;

            // Reading every line in the file and parsing it
            while ((line = expensesReader.readLine()) != null) {

                // Spliting the line on the , sign
                String[] sections = line.split(",");

                // Creating a new expense object and adding the entry in the format : date, description, amt in USD, amt in INR
                expensesEntry.add(new Expense(sections[0], sections[1], Double.parseDouble(sections[2]), Double.parseDouble(sections[3])));
            }

            // Catch block to throw error
        } catch (IOException ex) {
            System.out.println("Error loading expenses: " + ex.getMessage());
        }
        return expensesEntry;
    }

    // Method declaration to save an expense to the text file
    // This method takes a list of array as a parameter
    public static void saveExpensesEntry(ArrayList<Expense> expensesList) {
        try (PrintWriter expensesWriter = new PrintWriter(new FileWriter(fileName))) {
            for (Expense expense : expensesList) {
                expensesWriter.println(expense.getDate() + "," + expense.getDesc() + "," + expense.getAmt() + "," + expense.getAmtInINR());
            }
        } catch (IOException ex) {
            System.out.println("Error saving expenses: " + ex.getMessage());
        }
    }

}

