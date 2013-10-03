package com.tkmcnally.account;

public class BankAccount {
	int balance;
	java.util.Random r = new java.util.Random();

	public BankAccount(int initialBalance) {
		balance = initialBalance;
	}

	public int balance() {
		return getBalance();
	}

	public void delay() {
		try {
			Thread.sleep(r.nextInt(90)+10);
		} catch (java.lang.InterruptedException e) {
		}
	}

	public void deposit(int amount, String name) {
		//	setBalance(getBalance() + amount);
		setBalance(amount, name);
		delay();
		//	System.out.println(this+" deposit: "+amount+" balance: "+getBalance());
	}

	private int getBalance() {
		return balance;
	}

	private synchronized void setBalance(int i, String name) {
		//System.out.println(name +" setBalance: " + i + " balance: " + getBalance());
		balance = balance + i;
		System.out.println(name +" setBalance: " + i + " balance: " + getBalance());
	}

	public boolean withdraw(int amount, String name) {
		if (getBalance() < amount) {
			return false;
		}
		//setBalance(getBalance() - amount);
		setBalance(-1 * amount, name);
		delay();
		//System.out.println(this+" withdraw: "+amount+" balance: "+getBalance());
		return true;
	}
}