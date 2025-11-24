import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n=======================================");
            System.out.println("                MAIN MENU");
            System.out.println("=======================================");
            System.out.println("1. Fixed Investor");
            System.out.println("2. Retirement Depletion Simulator");
            System.out.println("3. Success Approximation");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = safeInt(sc);

            switch (choice) {

                case 1:
                    runFixedInvestor(sc);
                    break;

                case 2:
                    runRetirementDepletion(sc);
                    break;

                case 3:
                    runSuccessApproximation(sc);
                    break;

                case 4:
                    System.out.println("Exiting Program...");
                    sc.close();
                    return;

                default:
                    System.out.println("ERROR: Invalid option. Try again.\n");
            }
        }
    }

    // =====================================================
    // 1. FIXED INVESTOR – COMPOUND INTEREST
    // =====================================================
    private static void runFixedInvestor(Scanner sc) {

        double principal = 0, rate = 0;
        int years = 0;

        System.out.println("\n=======================================");
        System.out.println(" FIXED INVESTOR - COMPOUND INTEREST");
        System.out.println("=======================================");

        // PRINCIPAL
        while (true) {
            try {
                System.out.print("Enter Principal Amount: ");
                principal = sc.nextDouble();

                if (principal <= 0) {
                    System.out.println("ERROR: Principal must be greater than 0.\n");
                } else break;

            } catch (InputMismatchException e) {
                System.out.println("ERROR: Invalid input.\n");
                sc.nextLine();
            }
        }

        // RATE
        while (true) {
            try {
                System.out.print("Enter Annual Interest Rate (e.g., 0.05): ");
                rate = sc.nextDouble();

                if (rate < 0) {
                    System.out.println("ERROR: Rate cannot be negative.\n");
                } else break;

            } catch (InputMismatchException e) {
                System.out.println("ERROR: Invalid input.\n");
                sc.nextLine();
            }
        }

        // YEARS
        while (true) {
            try {
                System.out.print("Enter Number of Years: ");
                years = sc.nextInt();

                if (years <= 0) {
                    System.out.println("ERROR: Years must be greater than 0.\n");
                } else break;

            } catch (InputMismatchException e) {
                System.out.println("ERROR: Invalid input.\n");
                sc.nextLine();
            }
        }

        FixedInvestor investor = new FixedInvestor(principal, rate, years);
        double result = investor.calculateBalance();

        System.out.println("---------------------------------------");
        System.out.printf("Final Accumulated Balance: $%.2f\n", result);
        System.out.println("---------------------------------------");

        investor.printYearlyGrowth();
    }

    // =====================================================
    // 2. RETIREMENT DEPLETION SIMULATOR
    // =====================================================
    private static void runRetirementDepletion(Scanner sc) {

        double balance = 0, expense = 0, rate = 0;

        System.out.println("\n=======================================");
        System.out.println("     RETIREMENT DEPLETION SIMULATOR");
        System.out.println("=======================================");

        sc.nextLine();  // clear buffer

        // BALANCE
        while (true) {
            try {
                System.out.print("Enter Starting Balance: ");
                String input = sc.nextLine().trim().replace(",", ".");
                balance = Double.parseDouble(input);

                if (balance <= 0)
                    System.out.println("ERROR: Balance must be greater than 0.\n");
                else break;

            } catch (Exception e) {
                System.out.println("ERROR: Invalid number.\n");
            }
        }

        // WITHDRAWAL
        while (true) {
            try {
                System.out.print("Enter Annual Withdrawal Amount: ");
                String input = sc.nextLine().trim().replace(",", ".");
                expense = Double.parseDouble(input);

                if (expense <= 0)
                    System.out.println("ERROR: Expense must be greater than 0.\n");
                else break;

            } catch (Exception e) {
                System.out.println("ERROR: Invalid number.\n");
            }
        }

        // RATE
        while (true) {
            try {
                System.out.print("Enter Annual Interest Rate (e.g., 0.05): ");
                String input = sc.nextLine().trim().replace(",", ".");
                rate = Double.parseDouble(input);

                if (rate < 0)
                    System.out.println("ERROR: Rate cannot be negative.\n");
                else break;

            } catch (Exception e) {
                System.out.println("ERROR: Invalid number.\n");
            }
        }

        RetirementSimulator rs = new RetirementSimulator(balance, expense, rate);
        int years = rs.calculateYearsUntilDepleted();

        System.out.println("---------------------------------------");
        System.out.println("Your funds will last for: " + years + " years.");
        System.out.println("---------------------------------------");

        rs.printYearSummary();
    }

    // =====================================================
    // 3. SUCCESSIVE APPROXIMATION 
    // =====================================================
    private static void runSuccessApproximation(Scanner sc) {

        System.out.println("\n=======================================");
        System.out.println("       SUCCESSIVE APPROXIMATION");
        System.out.println("=======================================");

        System.out.print("Enter Starting Balance: ");
        double startingBalance = sc.nextDouble();

        System.out.print("Enter Annual Interest Rate (e.g., 0.04): ");
        double rate = sc.nextDouble();

        System.out.print("Enter Number of Retirement Years: ");
        int years = sc.nextInt();

        if (years <= 0) years = 30; // default

        double optimalWithdrawal =
                SuccessiveApproximation.maximumExpense(startingBalance, rate, years);

        System.out.printf("\nStarting Balance: %.2f\n", startingBalance);
        System.out.printf("Interest Rate: %.2f%%\n", rate * 100);
        System.out.printf("Retirement Duration: %d years\n", years);

        System.out.printf("Maximum Sustainable Withdrawal: %.2f\n", optimalWithdrawal);
        
        double check = SuccessiveApproximation.simulateRetirementBalance(
                startingBalance, optimalWithdrawal, rate, years
        );

        System.out.printf("Final Balance After %d Years: %.2f\n", years, check);
    
    }

    // =====================================================
    // SAFE INTEGER INPUT
    // =====================================================
    private static int safeInt(Scanner sc) {
        while (true) {
            try {
                return sc.nextInt();
            } catch (Exception e) {
                System.out.println("ERROR: Enter a valid number.");
                sc.nextLine();
            }
        }
    }
}


