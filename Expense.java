/**
 * Expense.java
 * Class to track the expenses in the Personel Expense Tracker
 *
 * @author Kartikeya Arvind Yadav
 * @version 1.0
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;

import org.json.JSONObject;

public class Expense {

    double rate_inr; //current usd to inr rate

    
    // State
    private String date;        // Date on which the expense in added
    private String desc;        // Description of the type expense
    private double amt;         // Amount of the transaction in USD
    private double amt_inr;     // Amount of the transaction in INR

    // Constructor
    public Expense (String date, String desc, double amt, double amt_inr){
        
        this.date = date;
        this.desc = desc;
        this.amt = amt;
        this.amt_inr = getAmtInINR();
    }

    // Getters

    // Gets the Date on which the expense entry is made
    public String getDate() {
        return date;
    }

    // Gets the Description of the expense entry
    public String getDesc() {
        return desc;
    }

    // Gets the Amount in USD of the expense entry
    public double getAmt() {
        return amt;
    }

    // Gets amount in INR
    public double getAmtInINR() {

        getINRRate();
        double result = this.amt * rate_inr;

        // Formatting the amount upto 2 decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        result = Double.parseDouble(df.format(result));

        return result;
    }

    private void getINRRate() {

        // Using try and catch in order to handle expections such as if the api is not providing INR rate it will not work and code will executr the catch portion of the code block.
        try {
        
        // Getting the URL
        URL url = new URL("https://api.exchangerate-api.com/v4/latest/USD");

        // Reading JSON from the URL
        BufferedReader jsonReader = new BufferedReader(new InputStreamReader(url.openStream()));

        // Saving the JSON in string line by line
        String reply = jsonReader.readLine();

        // Creating a json object
        JSONObject jsonObject = new JSONObject(reply);

        // Saving the value of the INR rate in the double variable rate
        double rate = jsonObject.getJSONObject("rates").getDouble("INR");

        // saving the value of rate in another double for calculation expense made in INR
        rate_inr = rate;

        // Catch block to print the error message in case of errors
        } catch (IOException e) {
        e.printStackTrace();
        }

}
    // Setters

    // Update date of a expense entry
    public void setDate (String date){
        this.date = date;
    }

    // Update description of a expense entry
    public void setDesc (String desc){
        this.desc = desc;
    }

    // Update amt of a expense entry
    public void setAmt (double amt){
        this.amt = amt;
    }

    //To String
    public String toString() {
        return "Entry (date=" + date + ", description=" + desc + ", amount in USD=" + amt + ", amount in INR=" + amt_inr + ")";
    }

}