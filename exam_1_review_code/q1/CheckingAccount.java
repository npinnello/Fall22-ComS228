package q1;

class CheckingAccount extends Account {
	public CheckingAccount(String name, int number, double initialBalance) {
		super(name, number, initialBalance);
	}

	public void writeCheck(double amount) {
		System.out.println(" Wrote check for " + amount);
		balance -= amount;
	}

	public void checkOverdraft() {
		if (balance < 0) {
			System.out.println(" Account is overdrawn ");
		}
	}

	@Override
	public String getAccountInfo() {
		return " Checking : " + name + ", " + number;
	}
}
