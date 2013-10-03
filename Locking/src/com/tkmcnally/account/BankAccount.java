package com.tkmcnally.account;

public class BankAccount {
	int balance;
	java.util.Random r = new java.util.Random();

	BankAccount(int initialBalance) {
		balance = initialBalance;
	}

	int balance() {
		return getBalance();
	}

	void delay() {
		try {
			Thread.sleep(r.nextInt(90)+10);
		} catch (java.lang.InterruptedException e) {
		}
	}

	void deposit(int amount) {
		setBalance(getBalance() + amount);
		delay();
		System.out.println(this+" deposit: "+amount+" balance: "+getBalance());
	}

	private int getBalance() {
		return balance;
	}

	private void setBalance(int i) {
		balance = i;
	}

	boolean withdraw(int amount) {
		if (getBalance() < amount) {
			return false;
		}
		setBalance(getBalance() - amount);
		delay();
		System.out.println(this+" withdraw: "+amount+" balance: "+getBalance());
		return true;
	}
}