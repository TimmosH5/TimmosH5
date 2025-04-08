package pojo;

import java.math.BigDecimal;

// here we determine that the MarginAccount objects draw from the parent class TradeAccount
// because -> TradeAccount supers the ID field for both CashAccount & MarginAccount constructors
// because -> TradeAccount instructs their children classes to handle cloning
public class MarginAccount extends TradeAccount {

    // unique field to MarginAccount
    private BigDecimal margin;

    // constructor that supers the ID from parent TradeAccount
    // but handles the balance/margin locally in this object class
    public MarginAccount(String id, BigDecimal margin) {
        super(id);
        this.margin = margin;
    }

    public BigDecimal getMargin() {
        return this.margin;
    }

    public void setMargin(BigDecimal margin) {
        this.margin = margin;
    }

    // specifically made clone method that overrides the abstract method
    // from the parent class -> in order to clone MarginAccount, with the getter 
    // from the super class TradeAccount get.Id and the locally passed this.margin
    // so - new object marginAccount(return super.this.getID & this.margin)
    // that means the object that IS currently calling the clone, gets both values 
    // from the parent(this.getId) and the child itself(locally this.margin)
    @Override
    public TradeAccount clone() {
        return new MarginAccount(super.getId(), this.margin);
    }
    

}
