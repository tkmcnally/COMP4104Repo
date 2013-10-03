package com.tkmcnally.account;


public class ProtectedBankAccount {

	protected BankAccount delegate;

	public ProtectedBankAccount(BankAccount account) {
		this.delegate = account;
	}

	public synchronized void deposit(int amount) {
		delegate.deposit(amount);
		notifyAll();
	}

	public synchronized void withdraw(int amount) {
		try {
			while (delegate.balance - amount < 0) {
				wait();
			}
			delegate.withdraw(amount);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
