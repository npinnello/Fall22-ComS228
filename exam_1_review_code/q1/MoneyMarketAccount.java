package q1;

class MoneyMarketAccount extends CheckingAccount implements InterestBearing {
	protected double rate;

	public MoneyMarketAccount(String name, int number, double initialBalance, double interestRate) {
		super(name, number, initialBalance);
		rate = interestRate;
	}

	@Override
	public double getInterestRate() {
		return rate;
	}

	@Override
	public void setInterestRate(double newRate) {
		System.out.println(" Money market interest rate changed to " + newRate);
		rate = newRate;
	}

	@Override
	public void calculateInterest(int time) {
		System.out.println(" Calculating money market interest on " + balance + " at " + rate
				+ " % over " + time + " days ");
	}

	@Override
	public String getAccountInfo() {
		return " Money Market : " + name + ", " + number;
	}
}
