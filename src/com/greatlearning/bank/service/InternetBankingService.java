package com.greatlearning.bank.service;
import com.greatlearning.bank.model.UserAccount;
public class InternetBankingService implements InternetBanking {
   private static final double minBalance=10000;
   private static final double maxBalance=500000;	
	@Override
	public void checkBalance(UserAccount account) {
		// TODO Auto-generated method stub
		if(account.getBalance()<minBalance) {
			System.out.println("Please maintain the minimum balance of " +minBalance);
		}
		System.out.println("Current balance amount is : "+account.getBalance());
	}

	@Override
	public boolean deposit(UserAccount account, double amount) {
		// TODO Auto-generated method stub
		if(amount >0 && amount <= maxBalance) {
			double currBalance=account.getBalance();
			double newBalance=currBalance+amount;
			account.setBalance(newBalance);
			System.out.println("Amount deposited successfully! ");
			System.out.println("Updated account balance is: "+account.getBalance());
			return true;	
		}
		
			return false;
	}

	@Override
	public boolean withdraw(UserAccount account, double amount) {
		// TODO Auto-generated method stub
		if(amount >0 && amount <= maxBalance) {
			double currBalance=account.getBalance();
			double newBalance=currBalance-amount;
			account.setBalance(newBalance);
			System.out.println("Amount withdrawn successfully! ");
			System.out.println("Updated account balance is: "+account.getBalance());
			return true;	
		}
		else {
			System.out.println("Withdrawal failed !..Insufficient balance...");
		}
		
			return false;
	}

	@Override
	public boolean transfer(UserAccount fromAccount, UserAccount toAccount, double amount,int otp) {
		if(otp==fromAccount.getOtp()) {
			System.out.println("OTP Validation Successful ! ");
			if(amount >0 && amount <= fromAccount.getBalance()) {
				double fromCurrBalance=fromAccount.getBalance();
				double fromNewBalance=fromCurrBalance-amount;
				fromAccount.setBalance(fromNewBalance);
				
				double toCurrBalance=toAccount.getBalance();
				double toNewBalance=toCurrBalance+amount;
				toAccount.setBalance(toNewBalance);
				
				System.out.println("Amount transfered successfully! ");
				System.out.println("Updated balance in account "+ fromAccount.getAccountNo() + "  is  " +fromAccount.getBalance());
				System.out.println("Updated balance in account  " +toAccount.getAccountNo() + "  is  " +toAccount.getBalance());
				return true;	
			}else {
				System.out.println("Amount transfer failed due to insufficient balance !");
				return false;
			}
			
		}
		else {
			System.out.println("Invalid OTP... OTP Validation fails");
			return false;
		}
		
	}

	@Override
	public boolean validateLogin(String accountNo, String password, UserAccount account) {
		if(accountNo.equals(account.getAccountNo()) && password.equals(account.getPassword())) {
			return true;
		}	
		
		return false;
	}

	@Override
	public int generateOTP() {
		// TODO Auto-generated method stub
		OTPGenerator generator=new OTPGenerator();
		return generator.generateOTP();
	}

}
