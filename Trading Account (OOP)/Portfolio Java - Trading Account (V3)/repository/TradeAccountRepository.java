package repository;

import java.util.HashMap;
import java.util.Map;

import pojo.TradeAccount;

// in a three layer design, you have the (1) main/frontend presentation layer
// then you have the (2)service layer, which is the middle man with business logic
// at the bottom/back there's the (3) repository - which acts as the database

// the (3)repository handles the storing, updating, reading, deleting of 
// the data/accounts that are used by the (2)service layers
// the repository is the ONLY layer that can access the data
// so if the main layer (1) wants something, they instruct the (2) service layer 
// to get the data,but then they first have to call the repository (3) and
// the unique ways THROUGH the repository (C R U D) to complete requests from (1)
public class TradeAccountRepository {

    // this ensures that every repository object SHOULD have a HashMap 
    // that has a String that an ID 
    // and an object that is a type of TradeAccount
    private Map<String, TradeAccount> datastore = new HashMap<>();

    // at the creation level, an account of type TradeAccount gets passed in
    // since thats Polymorphic, it doesnt need a CashAccount or MarginAccount specifically
    // the account calls THEIR extension from the parent class to call their ID
    // this ID gets passed in here, and a clone/deep copy of the account
    // to avoid reference traps and danger of accentidally updating values
    // in more than one place. 
    public void createTradeAccount(TradeAccount account) {
        this.datastore.put(account.getId(), account.clone());
    }

    // this construction is so that if there's no ID, it will say null
    // if there is an ID, it will retrieve a clone of the retrieved account
    // so retrieving remains safe in different contexts (?)
    public TradeAccount retrieveTradeAccount(String id) {
        return this.datastore.get(id) == null ? null : this.datastore.get(id).clone();
    }

    public void updateTradeAccount(TradeAccount account) {
        this.datastore.put(account.getId(), account.clone());
    }

    public void deleteTradeAccount(String id) {
        this.datastore.remove(id);
    }

}

