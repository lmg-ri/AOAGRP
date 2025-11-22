// SuccessiveApproximation.java

import java.util.Scanner;

public class SuccessiveApproximation {

    private static final double EPSILON = 0.01; // accuracy of final withdrawal

    /* Simulate retirement balance evolution over 'years'.*/
    private static double simulateRetirementBalance(
            double startingBalance,
            double annualExpense,
            double rate,
            int years) {

        double balance = startingBalance;

        for (int i = 0; i < years; i++) {
            balance = balance * (1 + rate);  // apply interest
            balance -= annualExpense;        // subtract withdrawal

            if (balance < 0) break;
        }

        return balance;
    }

    /* Optimization via Successive Approximation (Binary Search).*/
    public static double maximumExpense(double balance, double rate, int years) {

        double low = 0.0;
        double high = balance;

        while ((high - low) > EPSILON) {

            double mid = (low + high) / 2.0;

            double finalBalance = simulateRetirementBalance(balance, mid, rate, years);

            // If final balance is positive → safe withdrawal → increase lower bound
            if (finalBalance >= 0) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return low;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter starting retirement balance: ");
        double startingBalance = sc.nextDouble();

        System.out.print("Enter annual interest rate during retirement (e.g., 0.04 for 4%): ");
        double rate = sc.nextDouble();

        System.out.print("Enter number of retirement years (press 0 to use default 30): ");
        int years = sc.nextInt();

        if (years <= 0) {
            years = 30; // default
        }

        // Compute maximum sustainable annual withdrawal
        double optimalWithdrawal = maximumExpense(startingBalance, rate, years);

        // Print results
        System.out.printf("\nStarting Balance: %.2f\n", startingBalance);
        System.out.printf("Annual Interest Rate: %.2f%%\n", rate * 100);
        System.out.printf("Retirement Duration: %d years\n", years);

        System.out.printf("Maximum Sustainable Annual Withdrawal: %.2f\n",
                optimalWithdrawal);

        double check = simulateRetirementBalance(startingBalance, optimalWithdrawal, rate, years);

        System.out.printf("Final Balance After %d Years: %.2f\n", years, check);
    }
}


