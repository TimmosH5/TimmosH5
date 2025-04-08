package pojo;

// this is the abstract class that provides property sharing across all type of tradeaccounts
// in this case the shared property is an ID, because every account has an ID. 
// TradeAccount acts as a Polymorphic object - it can both be a CashAccount & MarginAccount
public abstract class TradeAccount {

    private String id;

    // classic simple constructor, 1 getter and 1 setter
    public TradeAccount(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // abstract method that tells the other TradeAccount children classes/types (Cash/Margin) that 
    // they should handle the cloning of their account (object) specifically to their context
    // so they can deep copy themselves into other contexts (getter/setter/copy constructor) where its needed. 
    public abstract TradeAccount clone();

}
