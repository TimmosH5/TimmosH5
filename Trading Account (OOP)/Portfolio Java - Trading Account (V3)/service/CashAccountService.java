package service;

import java.math.BigDecimal;

import pojo.CashAccount;
import repository.TradeAccountRepository;

// since the (2)service layers cannot access the data directly from the database
// they call the (3)repository layer to work FOR THEM to access and handle data.
// that means specifically that the service(cash or margin) needs a repository that 
// gets passed into their service Object constructor. 
// Without the passing, they cannot access the data.
public class CashAccountService implements TradeAccountService {

    // every service NEEDS a repository
    private TradeAccountRepository repository;

    // at the creation of the service, a pre-made repository gets passed in.
    // repositorie objects are made at the TradeAccountRepository level. 
    public CashAccountService(TradeAccountRepository repository) {
        this.repository = repository;
    }

    // if the service layer wants to create an account, they first need an object of CashAccount
    // this gets created in the pojo layer. The function here calls the repository of THIS service.
    // THIS service calls the repository that was passed in at the constructor level
    // and through the repository it calls the repository's OWN method of creating an account

    // so although it seems that the service is creating an account in this class, 
    // the service is NOT actually creating an account in this class.
    // the service merely calls the creation function through the TradeAccountRepository class
    // and passes the account along that gets passed in here. 
    public void createTradeAccount(CashAccount account) {
        this.repository.createTradeAccount(account);
    }

    // much of the same logic as the createTradeAccount applies here.
    // whats interesting is that we are typecasting here, 
    // so that whatever the repository returns, it will be typecasted
    // to a CashAcocunt -> in case of wrongfull calling?
    public CashAccount retrieveTradeAccount(String id) {
        return (CashAccount)this.repository.retrieveTradeAccount(id);
    }

    public void updateTradeAccount(CashAccount account) {
        this.repository.updateTradeAccount(account);
    }

    public void deleteTradeAccount(String id) {
        this.repository.deleteTradeAccount(id);
    }

    // here we override the implemented service contract by imposing our own way of depositing money
    // within the function, an account is first retrieved by help of a passed in ID
    // this ID in turn help the retrieveTradeAccount method to find the right account
    // this specific account gets stored in the "account" object within the deposit method HERE
    // then, the margin is retrieved of that account, and the passed in deposit extra is passed in the 
    //.add functionality specific to BigDecimals object class (getMargin)
    // then the locally stored account has a new balance and this gets passed in the
    // updateTradeAccount method from the TradeAccountRepository that replaces the old 
    // balance account with a newly & cloned MarginAccount
    @Override
    public void deposit(String id, BigDecimal amount) {
        CashAccount account = retrieveTradeAccount(id);
        account.setCashBalance(account.getCashBalance().add(amount));
        updateTradeAccount(account);
    }

    // same logic mostly as above but then subtracting an amount + updating the account
    @Override
    public void withdraw(String id, BigDecimal amount) {
        CashAccount account = retrieveTradeAccount(id);
        account.setCashBalance(account.getCashBalance().subtract(amount));
        updateTradeAccount(account);
    }

}