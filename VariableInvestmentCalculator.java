package Project;

import java.util.*;

public class VariableInvestmentCalculator
{
	public double calculateFinalBalance(InvestmentData data) {
        double balance = data.getPrincipal();
        List<Double> rates = data.getRateSequence();
       
        for (int year = 0; year < rates.size(); year++) {
            double rate = rates.get(year);
            balance *= (1 + rate); // Compound interest formula
        }
       
        data.setFinalBalance(balance);
        return balance;
    }
   
   
    public Map<String, Double> calculatePerformanceMetrics(InvestmentData data) {
        Map<String, Double> metrics = new HashMap<>();
        List<Double> rates = data.getRateSequence();
       
        // Average return
        double sum = 0;
        for (double rate : rates) {
            sum += rate;
        }
        double averageReturn = sum / rates.size();
       
        // Geometric mean return
        double product = 1.0;
        for (double rate : rates) {
            product *= (1 + rate);
        }
        double geometricMean = Math.pow(product, 1.0 / rates.size()) - 1;
       
        // Best and worst years
        double bestYear = Collections.max(rates);
        double worstYear = Collections.min(rates);
       
        // Total growth
        double totalGrowth = data.getFinalBalance() - data.getPrincipal();
        double totalReturn = (totalGrowth / data.getPrincipal()) * 100;
       
        metrics.put("averageReturn", averageReturn);
        metrics.put("geometricMean", geometricMean);
        metrics.put("bestYear", bestYear);
        metrics.put("worstYear", worstYear);
        metrics.put("totalGrowth", totalGrowth);
        metrics.put("totalReturn", totalReturn);
       
        return metrics;
    }

}
