package q1;

class Problem1 {

	public static void main(String[] args) {
		// 1 : 4.25
//		MoneyMarketAccount sharpay = new MoneyMarketAccount(" Sharpay Evans ", 1, 2000000.00, 4.25);
//		System.out.println(sharpay.getInterestRate());

		// 2 : 3000.00
//		Account troy = new SavingsAccount(" Troy Bolton ", 2, 3000.00, 1.125);
//		System.out.println(troy.getBalance());

		// 3 : IllegalArgumentException
//		Account troy = new SavingsAccount(" Troy Bolton ", 2, 3000.00, 1.125);
//		troy.withdraw(5000);
//		System.out.println(troy.getBalance());

		// 4 : Savings :  Kelsi Nielsen , 3
//		Account kelsi = new SavingsAccount(" Kelsi Nielsen ", 3, 500.00, 1.125);
//		System.out.println(kelsi.getAccountInfo());

		// 5 : Compile-time error: no calculateInterest in CheckingAccount
//		CheckingAccount darbus = new MoneyMarketAccount(" Mrs . Darbus ", 4, 10000.00, 4.25);
//		darbus.calculateInterest(365);

		// 6 : Compile-time error: Cannot convert CheckingAccount to InterestBearing
//		InterestBearing chad = new CheckingAccount(" Chad Danforth ", 5, 777.77);
//		System.out.println(chad.getInterestRate());

		// 7 : 1.125
//		InterestBearing ryan = new SavingsAccount(" Ryan Evans ", 6, 400000.00, 1.125);
//		System.out.println(ryan.getInterestRate());

		// 8 : Money Market :  Gabriella Montez , 10000
//		InterestBearing gabriella = new MoneyMarketAccount(" Gabriella Montez ", 10000, 7, 4.25);
//		System.out.println(((MoneyMarketAccount) gabriella).getAccountInfo());

		// 9 : ClassCastException
//		CheckingAccount taylor = new CheckingAccount(" Taylor McKessie ", 8, 7000.00);
//		System.out.println(((InterestBearing) taylor).getInterestRate());

	}

}
