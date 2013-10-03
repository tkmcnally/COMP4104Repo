package com.tkmcnally.account;

public class TestProtectedBankAccount {

	public static void main(String[] args) {

		ProtectedBankAccount paccount = new ProtectedBankAccount(new BankAccount(100));

		new Thread(new WithdrawThread(paccount)).start();
		new Thread(new DepositThread(paccount)).start();


	}

}
