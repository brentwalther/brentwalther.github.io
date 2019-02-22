/**
 * @(#)BankAccount.java
 *
 *
 * @author 
 * @version 1.00 2011/4/11
 */


public class BankAccount {
	
	public String name;
	public double balance;

    public BankAccount(String name, double balance) {
    	this.name = name;
    	this.balance = balance;
    }
    
    public void deposit(double amount) {
    	balance += amount;
    }
    
    public void withdraw(double amount) {
    	balance -= amount;
    }    
}