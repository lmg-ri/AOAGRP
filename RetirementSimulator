import java.util.InputMismatchException;
import java.util.Scanner;

public class RetirementSimulator {
	
    private double balance;    // Starting retirement balance
    private double expense;    // Annual withdrawal
    private double rate;       // Annual growth rate (decimal)

    // Constructor
    public RetirementSimulator(double balance, double expense, double rate)
    {
        this.balance = balance;
        this.expense = expense;
        this.rate = rate;
    }

    // Getters & Setters
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getExpense() {
        return expense;
    }
    public void setExpense(double expense) {
        this.expense = expense;
    }

    public double getRate() {
        return rate;
    }
    public void setRate(double rate) {
        this.rate = rate;
    }

    // Functional Method: finallyRetired()
    public int calculateYearsUntilDepleted()
    {
        if (balance <= 0)
            throw new IllegalArgumentException("Balance must be greater than 0.");
        if (expense <= 0)
            throw new IllegalArgumentException("Expense must be greater than 0.");
        if (rate < 0)
            throw new IllegalArgumentException("Rate cannot be negative.");

        double tempBalance = balance;
        int years = 0;

        while (tempBalance > 0)
        {
            tempBalance -= expense;  // withdraw
            if (tempBalance <= 0)
                break;

            tempBalance += tempBalance * rate; // apply interest
            years++;
        }

        return years;
    }

    public void printYearSummary()
    {
        double tempBalance = balance;
        int year = 1;

        System.out.println("-------------------------------");
        System.out.println(" Retirement Depletion Summary ");
        System.out.println("-------------------------------");

        while (tempBalance > 0)
        {
            double startBalance = tempBalance;
            tempBalance -= expense;
            if (tempBalance <= 0)
            {
                System.out.printf("Year %d: Start $%.2f → End $0.00 (Funds Exhausted)\n",
                        year, startBalance);
                break;
            }

            tempBalance += tempBalance * rate;

            System.out.printf("Year %d: Start $%.2f → End $%.2f\n",
                    year, startBalance, tempBalance);

            year++;
        }
    }
    
    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);
        double balance = 0, expense = 0, rate = 0;

        System.out.println("=======================================");
        System.out.println("     RETIREMENT DEPLETION SIMULATOR    ");
        System.out.println("=======================================");

        // -------- BALANCE INPUT ----------
        while (true) {
            try {
                System.out.print("Enter Starting Balance: ");
                sc.nextLine(); // FIX: flush leftover newline
                String input = sc.nextLine().trim().replace(",", ".");
                balance = Double.parseDouble(input);

                if (balance <= 0) {
                    System.out.println("ERROR: Balance must be greater than 0.\n");
                } else break;

            } catch (Exception e) {
                System.out.println("ERROR: Invalid number.\n");
            }
        }

        // -------- EXPENSE INPUT ----------
        while (true) {
            try {
                System.out.print("Enter Annual Withdrawal Amount: ");
                String input = sc.nextLine().trim().replace(",", ".");
                expense = Double.parseDouble(input);

                if (expense <= 0) {
                    System.out.println("ERROR: Expense must be greater than 0.\n");
                } else break;

            } catch (Exception e) {
                System.out.println("ERROR: Invalid number.\n");
            }
        }

        // -------- RATE INPUT ----------
        while (true) {
            try {
                System.out.print("Enter Annual Interest Rate (e.g., 0.05): ");
                String input = sc.nextLine().trim().replace(",", ".");
                rate = Double.parseDouble(input);

                if (rate < 0) {
                    System.out.println("ERROR: Rate cannot be negative.\n");
                } else break;

            } catch (Exception e) {
                System.out.println("ERROR: Invalid number.\n");
            }
        }

        // Create object
        RetirementSimulator rs = new RetirementSimulator(balance, expense, rate);

        // Process
        int years = rs.calculateYearsUntilDepleted();

        System.out.println("---------------------------------------");
        System.out.println("Your funds will last for: " + years + " years.");
        System.out.println("---------------------------------------");

        rs.printYearSummary();
        sc.close();
    }
}
