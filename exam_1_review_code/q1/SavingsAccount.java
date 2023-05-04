package q1;

class SavingsAccount extends Account implements InterestBearing {
	protected double rate;

	public SavingsAccount(String name, int number, double initialBalance, double interestRate) {
		super(name, number, initialBalance);
		rate = interestRate;
	}

	@Override
	public double getInterestRate() {
		return rate;
	}

	@Override
	public void setInterestRate(double newRate) {
		System.out.println(" Rate changed to " + newRate);
		rate = newRate;
	}

	@Override
	public void calculateInterest(int time) {
		System.out.println(
				" Calculating interest on " + balance + " at " + rate + " % over " + time + " days ");
	}

	@Override
	public String getAccountInfo() {
		return " Savings : " + name + ", " + number;
	}
}
