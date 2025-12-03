package Project;

import java.util.*;
import java.util.InputMismatchException;

public class VariableInvestmentManager 
{
	private Scanner scanner = new Scanner(System.in);
    private VariableInvestmentCalculator calculator = new VariableInvestmentCalculator();
   
    public void runInteractiveSimulation() {
        System.out.println("\nINTERACTIVE VARIABLE GROWTH SIMULATION");
        System.out.println("==========================================");
       
        try {
            // Step 1: Get principal amount
            double principal = getPrincipalFromUser();
           
            // Step 2: Get investment period
            int years = getNumberOfYearsFromUser();
           
            // Step 3: Get variable interest rates
            List<Double> rateList = getInterestRatesFromUser(years);
           
            // Step 4: Create investment data object
            InvestmentData investment = new InvestmentData(principal, rateList);
           
            // Step 5: Perform calculations
            long startTime = System.nanoTime();
            double finalBalance = calculator.calculateFinalBalance(investment);
            long endTime = System.nanoTime();
           
            Map<String, Double> metrics = calculator.calculatePerformanceMetrics(investment);
           
            // Step 6: Display results
            displayResults(investment, metrics, endTime - startTime);
           
        } catch (Exception e) {
            System.out.println("Simulation error: " + e.getMessage());
        }
    }
   
   
    private double getPrincipalFromUser() {
        while (true) {
            try {
                System.out.print("Enter initial investment amount: $");
                double principal = scanner.nextDouble();
               
                if (principal <= 0) {
                    System.out.println("ERROR: Principal must be greater than 0. Please try again.");
                    continue;
                }
               
                System.out.printf("SUCCESS: Initial investment set to: $%,.2f\n", principal);
                return principal;
               
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Please enter a valid number (e.g., 10000.50)");
                scanner.next(); // Clear invalid input
            }
        }
    }
   
   
    private int getNumberOfYearsFromUser() {
        while (true) {
            try {
                System.out.print("Enter number of years for investment: ");
                int years = scanner.nextInt();
               
                if (years <= 0) {
                    System.out.println("ERROR: Years must be positive. Please try again.");
                    continue;
                }
                if (years > 30) {
                    System.out.println("NOTE: Long-term investment (>30 years) selected.");
                }
               
                System.out.printf("SUCCESS: Investment period set to: %d years\n", years);
                return years;
               
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Please enter a valid integer number.");
                scanner.next(); // Clear invalid input
            }
        }
    }
   
   
    private List<Double> getInterestRatesFromUser(int numberOfYears) {
        List<Double> rateList = new ArrayList<>();
       
        System.out.println("\nEnter annual interest rates for each year:");
        System.out.println("   Examples: 5 for 5% or 0.05 for 5% (both accepted)");
        System.out.println("   Negative values allowed for market losses");
        System.out.println("   " + "-".repeat(50));
       
        for (int year = 1; year <= numberOfYears; year++) {
            while (true) {
                try {
                    System.out.printf("Year %d interest rate: ", year);
                    double rate = scanner.nextDouble();
                   
                    // Convert percentage to decimal if needed
                    if (Math.abs(rate) > 1 && Math.abs(rate) <= 100) {
                        rate = rate / 100.0;
                        System.out.printf("   Converted to decimal: %.4f\n", rate);
                    }
                   
                    // Validate rate bounds
                    if (rate < -1.0) {
                        System.out.println("ERROR: Rate cannot be less than -100%. Please try again.");
                        continue;
                    }
                    if (rate > 2.0) {
                        System.out.print("WARNING: Very high rate (>200%). Continue? (y/n): ");
                        String confirm = scanner.next();
                        if (!confirm.equalsIgnoreCase("y")) {
                            continue;
                        }
                    }
                   
                    rateList.add(rate);
                    System.out.printf("   SUCCESS: Rate accepted: %.2f%%\n", rate * 100);
                    break;
                   
                } catch (InputMismatchException e) {
                    System.out.println("ERROR: Please enter a valid number (e.g., 5, -10, or 0.05)");
                    scanner.next(); // Clear invalid input
                }
            }
        }
       
        return rateList;
    }
   
   
    private void displayResults(InvestmentData data, Map<String, Double> metrics, long computationTime) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("                    SIMULATION RESULTS");
        System.out.println("=".repeat(70));
       
        // Basic information
        System.out.printf("Initial Principal: $%,.2f\n", data.getPrincipal());
        System.out.printf("Investment Period: %d years\n", data.getYears());
        System.out.printf("Final Balance: $%,.2f\n", data.getFinalBalance());
        System.out.printf("Computation Time: %,d nanoseconds\n", computationTime);
        System.out.println("-".repeat(70));
       
        // Year-by-year breakdown
        System.out.println("YEAR-BY-YEAR GROWTH:");
        System.out.println("Year | Interest Rate |    Balance    |     Growth");
        System.out.println("-----|---------------|---------------|-------------");
       
        double balance = data.getPrincipal();
        System.out.printf("%4d | %13s | $%,10.2f | %11s\n",
            0, "Initial", balance, "-");
       
        List<Double> rates = data.getRateSequence();
        for (int year = 0; year < rates.size(); year++) {
            double rate = rates.get(year);
            double previousBalance = balance;
            balance *= (1 + rate);
            double growth = balance - previousBalance;
           
            System.out.printf("%4d | %11.2f%% | $%,10.2f | $%,9.2f\n",
                year + 1, rate * 100, balance, growth);
        }
       
        System.out.println("-".repeat(70));
       
        // Performance metrics
        System.out.println("PERFORMANCE ANALYSIS:");
        System.out.printf("* Total Growth: $%,.2f\n", metrics.get("totalGrowth"));
        System.out.printf("* Total Return: %.2f%%\n", metrics.get("totalReturn"));
        System.out.printf("* Average Annual Return: %.2f%%\n", metrics.get("averageReturn") * 100);
        System.out.printf("* Geometric Mean Return: %.2f%%\n", metrics.get("geometricMean") * 100);
        System.out.printf("* Best Year: %.2f%%\n", metrics.get("bestYear") * 100);
        System.out.printf("* Worst Year: %.2f%%\n", metrics.get("worstYear") * 100);
       
        System.out.println("=".repeat(70));
    }
   
   
    public void displayAlgorithmAnalysis() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("               ALGORITHM ANALYSIS DOCUMENTATION");
        System.out.println("=".repeat(70));
       
        System.out.println("\nCOMPLEXITY ANALYSIS:");
        System.out.println("* Time Complexity: O(n) where n = number of years");
        System.out.println("* Space Complexity: O(n) for rate sequence storage");
        System.out.println("* Algorithm Class: P (Polynomial Time)");
       
        System.out.println("\nALGORITHM DESIGN:");
        System.out.println("* Design Paradigm: Iterative Simulation");
        System.out.println("* Key Formula: Compound Interest A = P(1 + r)");
        System.out.println("* Data Structures: ArrayList for rate sequences");
        System.out.println("* Error Handling: Comprehensive input validation");
       
        System.out.println("\nPERFORMANCE CHARACTERISTICS:");
        System.out.println("* Linear time scaling with investment period");
        System.out.println("* Efficient O(1) space for calculations");
        System.out.println("* Robust validation for financial integrity");
       
        System.out.println("\nCORRECTNESS VERIFICATION:");
        System.out.println("* Mathematical induction on compound interest");
        System.out.println("* Boundary testing: negative rates, edge cases");
        System.out.println("* Input validation at multiple levels");
       
        System.out.println("\n" + "=".repeat(70));
    }
   
   
    public void runPredefinedExample() {
        System.out.println("\nPREDEFINED EXAMPLE: Market Volatility Scenario");
        System.out.println("=================================================");
       
        double principal = 15000.0;
        List<Double> rates = Arrays.asList(0.08, 0.12, -0.05, 0.15, -0.02, 0.10);
       
        InvestmentData exampleData = new InvestmentData(principal, rates);
        double finalBalance = calculator.calculateFinalBalance(exampleData);
        Map<String, Double> metrics = calculator.calculatePerformanceMetrics(exampleData);
       
        displayResults(exampleData, metrics, 0);
    }

}
