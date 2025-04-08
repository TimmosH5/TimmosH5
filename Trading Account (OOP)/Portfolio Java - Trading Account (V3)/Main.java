import java.io.IOException;
import java.math.BigDecimal;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import constants.AccountType;
import constants.Transaction;

import pojo.CashAccount;
import pojo.MarginAccount;

import repository.TradeAccountRepository;
import service.TradeAccountService;
import service.CashAccountService;
import service.MarginAccountService;

// Step 1 - make an abstract TradeAccount to superceede 
// CashAccount & MarginAccount including all 3 getters/setters
// and all 3 clone methods (1x abstract, 2x override)

// Step 2 - make a TradeAccountRepository that handles
// all methods with account creation, updating, deleting 
// that make deep copies of the accounts they get passed in
// and acts as a database layer

// Step 3 - make the service / business logic layer
// implement an interface that instructs two services 
// Cash/Margin to handle their unique ways of depositing/withdrawing 
// and instruct the services to require a repository to get passed in
// to handle account updates as well (throuh instructions/not directly)

// Step 4 - if all the logic is determined and works, import
// acounts.txt & transactions.txt (data layer) through paths and 
// transformation pipelines and instruct the main to call services, 
// to call repository, to run all data

public class Main {

    // here we define paths from which to draw text from, to transform into workable info in the method
    // section of the file here. This is copied from the Udemy instructions 
    static Path[] paths = new Path[] {Paths.get("data/accounts.txt"), 
           Paths.get("data/transactions.txt")};

    // here we define a new repository with the same name as the object (lower camel case)
    // as well as two seperate services to interact with from the main layer (THIS layer)
    // these services, require the parameter tradeAccountRepository that is made here 
    // for now, all of these are empty of accounts as well as transactions.
    // remember - the service instructs the repository - but doesnt handle data itself
    static TradeAccountRepository tradeAccountRepository = new TradeAccountRepository();
    static CashAccountService cashAccountService = new CashAccountService(tradeAccountRepository);
    static MarginAccountService marginAccountService = new MarginAccountService(tradeAccountRepository);

public static void main(String[] args) {

    // Also, this part is code from Udemy to test the application.
    // see explanation at the bottom for indepth about the methods that are called here
    // TLDR -> we are importing the text files from the DATA layer and reworking them
    // we import the accounts, and we import the transaction history (deposits/withdraws)
    // if somewhere there's an input error - IOException gets printed - app crashes 
    try {
        loadTradeAccounts();
        applyTransactions();
        finalTest();
    } catch (IOException exception) {
        System.out.println(exception.getMessage());
    }

    // TEST TIM
    // polymorphic so tradeaccount understands if you pass cashaccount it will be a cashaccount
    // tradeAccountRepository.createTradeAccount(new CashAccount("234", new BigDecimal("12345,00")));
    // cashAccountService.updateTradeAccount(new CashAccount("234", new BigDecimal("12345")));


    }

                    // METHODS

    // This part reworks the info from path[0] from the called paths/textfiles.
    // specifically it handles the importing of accounts.
    // we are making a stream pipeline to convert the dry text info into workable 
    // code objects/parameters/arguments. In each line of the text file are 3 seperate words
    // the words define the type of account, the account ID and the balance currently on them

    // these words are made into a string[] that SPLITS every word into an indexed element of that array
    // then we instruct the function that IF the first word equals MARGIN, its a MARGIN type account
    // if true, the object from on top gets called: marginAccountService. Since this is a service object,
    // it can only handle CRUD operations THROUGH the repository. It calls the repository.createTradeAccount
    // method. this method expects an object FIRSTLY, which is created newly on the spot. 
    // a newly created MarginAccount expects an ID to connect to the object, which is the [1] index of
    // the words arraylist created by the stream. The new object also expects a balance BigDecimal to pass
    // this is then imported from the arraylist as well, on position [2]
    public static void loadTradeAccounts() throws IOException {
        Files.lines(paths[0])
        .forEach(line -> { 
            String[] words = line.split(" ");
            if (words[0].equals("MARGIN")) {
                marginAccountService.createTradeAccount(new MarginAccount(words[1], new BigDecimal(words[2])));
            } else {
                cashAccountService.createTradeAccount(new CashAccount(words[1], new BigDecimal (words[2])));
            }
        });
    }

    // In much of the same way as we imported the account info from the textfile path[0],
    // we are now importing transaction history related to those accounts from textfile path[1] -> transactions.txt
    // The stream splits the words (4 in total) into a workable arraylist with indices.
    // A simple Boolean is made to define if true, then the related line 
    // in the word arraylist is connected to a MARGIN account.
    // within the method the interface contract is called and equalized to whatever kind of 
    // account type (true = margin, false = cashaccount) is found by the boolean

    // THEN enum type of Transaction (in the constants) is called to define whatever info 
    // is found in the index spot[2] in the line/text file. This spot instructs either deposit or withdraw
    // the 'transaction' variable stores the value of 'deposit' or 'withdraw'
    // and is checked with .equal against the enum constant Transaction types if its either
    // a transaction that is a deposit or withdraw. 

    // if its a deposit, the tradeaccountservice (polymorphic) that handles both Cash and Margin service
    // gets called. Java then knows which service to actually call and within the specific service
    // either deposit gets called which expects both a String ID and an amount to handle
    // thestring ID identifies the account number/id and is non the [1] spot of the words arraylist, 
    // and the amount that either gets deposited or withdrawn is indexed on [3] on the word list. 
    // THIS then gets passed as a new BigDecimal. 

    // within the deposit and withdraw overide the methods handle:
    // -> a storing of the account (ID) -> a setting of the new cash/margin balance (BigDecimal)
    // -> by first getting the balance of that account -> and setting a NEW balance to that account 
    // -> and updating the account -> cloning of the stored account as a NEW account in the repository
    public static void applyTransactions() throws IOException {
        Files.lines(paths[1])
        .forEach(line -> { 
            String[] words = line.split(" ");
            Boolean isMargin = words[0].equals(AccountType.MARGIN.toString());
            TradeAccountService tradeAccountService = isMargin ? marginAccountService : cashAccountService;
            Transaction transaction = Transaction.valueOf(words[2]);
            if (transaction.equals(Transaction.DEPOSIT)) {
                tradeAccountService.deposit(words[1], new BigDecimal (words[3]));
            } else {
                tradeAccountService.withdraw(words[1], new BigDecimal (words[3]));
            }
        });

    }

    // final test imported from Udemy instructions to check application 
    public static void finalTest() throws IOException {
        System.out.println("Account A1234B Cash Balance: " + cashAccountService.retrieveTradeAccount("A1234B").getCashBalance());
        System.out.println("Account E3456F Cash Balance: " + cashAccountService.retrieveTradeAccount("E3456F").getCashBalance());
        System.out.println("Account I5678J Cash Balance: " + cashAccountService.retrieveTradeAccount("I5678J").getCashBalance());
        System.out.println("Account C2345D Margin: " + marginAccountService.retrieveTradeAccount("C2345D").getMargin());
        System.out.println("Account G4567H Margin: " + marginAccountService.retrieveTradeAccount("G4567H").getMargin());
    }

}