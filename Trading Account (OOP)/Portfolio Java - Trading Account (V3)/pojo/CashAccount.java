package pojo;

import java.math.BigDecimal;

// here we determine that the CashAccount objects draw from the parent class TradeAccount
// because -> TradeAccount supers the ID field for both CashAccount & MarginAccount constructors
// because -> TradeAccount instructs their children classes to handle cloning
public class CashAccount extends TradeAccount {

    // unique field to CashAccount
    private BigDecimal cashBalance;

    // constructor that supers the ID from parent TradeAccount
    // but handles the balance locally in this object class
    public CashAccount(String id, BigDecimal cashBalance) {
        super(id);
        this.cashBalance = cashBalance;
    }

    public BigDecimal getCashBalance() {
        return this.cashBalance;
    }

    public void setCashBalance(BigDecimal cashBalance) {
        this.cashBalance = cashBalance;
    }

    // specifically made clone method that overrides the abstract method
    // from the parent class -> in order to clone cashaccount, with the getter 
    // from the super class TradeAccount get.Id and the locally passed this.cashbalance
    // so - new object cashAccount(return super.this.getID & this.cashbalance)
    // that means the object that IS currently calling the clone, gets both values 
    // from the parent(this.getId) and the child itself(locally this.cashBalance)
    @Override
    public TradeAccount clone() {
        return new CashAccount(super.getId(), this.cashBalance);
    }

}