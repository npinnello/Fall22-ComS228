package q1;

public abstract class Account {
	protected String name;
	protected int number;
	protected double balance;

	public Account(String name, int number, double initialBalance) {
		this.name = name;
		this.number = number;
		balance = initialBalance;
	}

	public String getName() {
		return name;
	}

	public int getAccountNumber() {
		return number;
	}

	public double getBalance() {
		return balance;
	}

	public void deposit(double amount) {
		System.out.println(" Deposited " + amount);
		balance += amount;
	}

	public void withdraw(double amount) {
		if (amount > balance) {
			throw new IllegalArgumentException();
		}
		System.out.println(" Withdrew " + amount);
		balance -= amount;
	}

	public abstract String getAccountInfo();
}
