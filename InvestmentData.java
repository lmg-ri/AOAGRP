package Project;
import java.util.*;

public class InvestmentData 
{
	private double principal;
    private List<Double> rateSequence;
    private int years;
    private double finalBalance;
   
    public InvestmentData(double principal, List<Double> rateSequence) {
        validateInput(principal, rateSequence);
        this.principal = principal;
        this.rateSequence = new ArrayList<>(rateSequence);
        this.years = rateSequence.size();
        this.finalBalance = 0.0;
    }
   
    private void validateInput(double principal, List<Double> rateSequence) {
        if (principal <= 0) {
            throw new IllegalArgumentException("Principal must be greater than 0.");
        }
        if (rateSequence == null || rateSequence.isEmpty()) {
            throw new IllegalArgumentException("Rate sequence cannot be null or empty.");
        }
        for (int i = 0; i < rateSequence.size(); i++) {
            double rate = rateSequence.get(i);
            if (rate < -1.0) {
                throw new IllegalArgumentException(
                    String.format("Invalid rate at year %d: %.2f%%. Cannot be less than -100%%",
                    i + 1, rate * 100));
            }
        }
    }
   
    // Getters
    public double getPrincipal() { return principal; }
    public List<Double> getRateSequence() { return new ArrayList<>(rateSequence); }
    public int getYears() { return years; }
    public double getFinalBalance() { return finalBalance; }
    public void setFinalBalance(double balance) { this.finalBalance = balance; }
}




