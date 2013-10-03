package com.tkmcnally.account;

public class DepositThread implements Runnable {

	protected ProtectedBankAccount account;

	public DepositThread(ProtectedBankAccount account) {
		this.account = account;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		account.deposit(100);
	}

}
