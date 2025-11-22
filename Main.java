package Project;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	   public static void main(String[] args)
	   {
	       Scanner sc = new Scanner(System.in);
	       double principal = 0, rate = 0;
	       int years = 0;
	       System.out.println("=======================================");
	       System.out.println("  FIXED INVESTOR SYSTEM");
	       System.out.println("=======================================");
	       while (true)
	       {
	       	try {
	               System.out.print("Enter Principal Amount: ");
	               principal = sc.nextDouble();
	               if (principal <= 0) {
	                   System.out.println("ERROR: Principal must be greater than 0. Please try again.\n");
	               } else {
	                   break;
	               }
	           } catch (InputMismatchException e) {
	               System.out.println("ERROR: Invalid input! Please enter a valid number.\n");
	               sc.nextLine(); 
	           }
	       }
	      
	       while (true) {
	           try {
	               System.out.print("Enter Interest Rate (e.g., 0.05 for 5%): ");
	               rate = sc.nextDouble();
	               if (rate < 0) {
	                   System.out.println("ERROR: Rate cannot be negative. Please try again.\n");
	               } else {
	                   break;
	               }
	           } catch (InputMismatchException e) {
	               System.out.println("ERROR: Invalid input! Please enter a valid number.\n");
	               sc.nextLine(); 
	           }
	       }
	      
	       while (true) {
	           try {
	               System.out.print("Enter Number of Years: ");
	               years = sc.nextInt();
	               if (years <= 0) {
	                   System.out.println("ERROR: Years must be greater than 0. Please try again.\n");
	               } else {
	                   break;
	               }
	           } catch (InputMismatchException e) {
	               System.out.println("ERROR: Invalid input! Please enter a whole number.\n");
	               sc.nextLine();
	           }
	       }
	       FixedInvestor investor = new FixedInvestor(principal, rate, years);
	       double result = investor.calculateBalance();
	       System.out.println("---------------------------------------");
	       System.out.printf("Final Accumulated Balance: $%.2f\n", result);
	       System.out.println("---------------------------------------\n");
	       investor.printYearlyGrowth();
	       sc.close();
	   }
	}




