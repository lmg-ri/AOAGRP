/*This project used ChatGPT to assist with:
  - designing the user interface
  - testing and debugging validation logic
*/
package Project;

public class FixedInvestor
{
	private double principal;   // Initial investment amount
	private double rate;       //  Annual interest rate (decimal)
	private int years;        //   Number of years
	private double balance;  //    Final accumulated balance
	
	
	// Constructor
	public FixedInvestor (double principal, double rate, int years)
	{
		this.principal = principal;
		this.rate = rate;
		this.years = years;
		this.balance = principal; // Initial balance starts at principal
	}
	
	// Getters and Setters
	public double getPrincipal()
	{
		return principal;
	}
	public void setPrincipal(double principal)
	{
		this.principal = principal;
	}
	public double getRate()
	{
		return rate;
	}
	public void setRate(double rate)
	{
		this.rate = rate;
	}
	public int getYears()
	{
		return years;
	}
	public void setYears(int years)
	{
		this.years = years;
	}
	public double getBalance()
	{
		return balance;
	}
	public void setBalance(double balance)
	{
		this.balance = balance;
	}


	// Functional Method
	public double calculateBalance()
	{
		if (principal <= 0)
		{
			throw new IllegalArgumentException("Principal must be greater than 0.");
		}
		if (rate < 0)
		{
			throw new IllegalArgumentException("Rate cannot be negative.");
		}
		if(years <= 0)
		{
			throw new IllegalArgumentException("Years must be greater than 0.");
		}
		
		
		balance = principal;
		
		for(int i = 1; i<= years; i++)
		{
			balance = balance *(1 + rate);
		}
		return balance;
	}
	
	public void printYearlyGrowth()
	{
	    double tempBalance = principal;
	    System.out.println("------------------------");
	    System.out.println("Yearly Growth Summary:");
	    System.out.println("------------------------");
	    for (int i = 1; i <= years; i++) {
	        tempBalance *= (1 + rate);
	        System.out.printf("Year %d: $%.2f\n", i, tempBalance);
	    }
	}
	
}


