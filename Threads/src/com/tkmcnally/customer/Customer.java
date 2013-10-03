package com.tkmcnally.customer;

import java.util.Random;

import com.tkmcnally.account.BankAccount;

public class Customer {

	protected final BankAccount account;
	protected final String name;
	protected final Random random;

	public Customer(String name, BankAccount account) {
		this.account = account;
		this.name = name;
		random = new Random();
	}

	public BankAccount getAccount() {
		return account;
	}

	public String getName() {
		return name;
	}

	public int getRandomAmount() {

		//return random.nextInt(10)+1;
		return 1;
	}

}
