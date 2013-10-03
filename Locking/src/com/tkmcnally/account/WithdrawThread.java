package com.tkmcnally.account;

public class WithdrawThread implements Runnable {

	protected ProtectedBankAccount account;

	public WithdrawThread(ProtectedBankAccount account) {
		this.account = account;
	}

	@Override
	public void run() {
		account.withdraw(200);
		System.out.print("DONE!");
	}

}
