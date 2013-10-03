package com.tkmcnally.account;

import com.tkmcnally.customer.Customer;
import com.tkmcnally.customer.impl.DepositCustomer;
import com.tkmcnally.customer.impl.WithdrawCustomer;
import com.tkmcnally.util.Constants;

public class TestBankAccount {

	public static void main(String[] args) {


		System.out.println("Initalizing...");
		BankAccount account = new BankAccount(Constants.Accounts.MAX_INIT_BALANCE);
		Customer[] customer = new Customer[Constants.Accounts.MAX_CUSTOMERS * 2];


		System.out.println("Creating customers...");
		System.out.println("Account balance before: " + account.balance);
		for(int i = 0; i < Constants.Accounts.MAX_CUSTOMERS; i++) {
			Thread t = new Thread(new DepositCustomer(i + "Deposit " + Constants.Accounts.CUSTOMER, account));
			t.setPriority(1);
			t.start();
			Thread t1 = new Thread(new WithdrawCustomer((i) + "Withdraw " + Constants.Accounts.CUSTOMER, account));
			t1.setPriority(10);
			t1.start();

		}


	}

}
