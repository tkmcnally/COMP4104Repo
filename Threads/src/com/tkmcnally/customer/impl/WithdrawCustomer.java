package com.tkmcnally.customer.impl;

import com.tkmcnally.account.BankAccount;
import com.tkmcnally.customer.Customer;
import com.tkmcnally.util.Constants;

public class WithdrawCustomer extends Customer implements Runnable {

	public WithdrawCustomer(String name, BankAccount ba) {
		super(name, ba);
	}

	@Override
	public void run() {
		for(int i = 0; i < Constants.Accounts.NUM_TRANSAC; i++) {
			//System.out.println(name + " Account balance before: " + account.balance());
			//System.out.println(name + " Account balance before: " + account.balance());
			this.account.withdraw(getRandomAmount(), name);
			try {
				Thread.sleep(random.nextInt(90)+10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(name + " Account balance after: " + account.balance());
			//System.out.println(name + " Account balance after: " + account.balance());
		}

		//System.out.println("Account balance after: " + account.balance());

	}


}
