package service;

import java.math.BigDecimal;

// this is the interface that will be exploited by the services in the same package
// these are methods that the respective services themselves should override
public interface TradeAccountService {
    public void deposit(String id, BigDecimal amount);
    public void withdraw(String id, BigDecimal amount);
}