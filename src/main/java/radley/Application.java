package radley;
// import packages
import java.util.*;

// create application class
public class Application {
    String filename;
    // create ArrayList to store accounts in
    List<AccountInterface> accountList = new ArrayList<>();

    public Application() {
        // store data in temp file
        this.filename = "/tmp/rb-storage.log";
        this.accountList = new PersistenceCsv(this.filename).readAccounts();
    }

    public Application(String filename) {
        this.filename = filename;
        this.accountList = new PersistenceCsv(this.filename).readAccounts();
    }

    public int createAccount(String firstname, String lastName, float balance, boolean limitedAccount) {
        // initialise accountNumber
        int accountNumber = 0;

        try {
            if (limitedAccount) {
                LimitedAccount account = new LimitedAccount(firstname, lastName, balance);
                this.accountList.add(account);
                accountNumber = account.getAccountNumber();

                System.out.printf("New limited account created: %d\n", accountNumber);
            } else {
                StandardAccount account = new StandardAccount(firstname, lastName, balance);
                this.accountList.add(account);
                accountNumber = account.getAccountNumber();

                System.out.printf("New standard account created: %d\n", accountNumber);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountNumber;
    }

    public ErrorCode deleteAccount(int accountNumber) {
        // Search the account list
        Iterator<AccountInterface> accountIterator = this.accountList.iterator();
        while (accountIterator.hasNext()) {
            AccountInterface account = accountIterator.next();
            if (accountNumber == account.getAccountNumber()) {
                // Find the account to be deleted
                this.accountList.remove(account);
                System.out.printf("Account %d deleted.\n", accountNumber);
                return ErrorCode.Success;
            }
        }

        // Account not found
        System.out.printf("Account %d not found.\n", accountNumber);
        return ErrorCode.ErrorAccountNotFound;

    }

    public int listAccounts() {
        int countAccounts = 0;

        // iterate through the accountList and print the summary information for each account
        System.out.println("\nAccounts: ");
        Iterator<AccountInterface> accountIterator = accountList.iterator();
        while (accountIterator.hasNext()) {
            AccountInterface account = accountIterator.next();
            System.out.println("Account Name: " + account.getFullName() + " Account Number: " + account.getAccountNumber()
                    + " Limited: " + Boolean.toString(account.isLimitedAccount()) + " Balance: " + account.getBalance() + "\n");
            countAccounts++;
        }

        return countAccounts;
    }

    public ErrorCode depositFunds(int accountNumber, float value){
        // define return value
        ErrorCode retval = ErrorCode.ErrorAccountNotFound;

        // Search the account list
        Iterator<AccountInterface> accountIterator = this.accountList.iterator();
        while (accountIterator.hasNext()) {
            AccountInterface account = accountIterator.next();
            if (accountNumber == account.getAccountNumber()) {
                // Found the account
                retval = account.depositFunds(value);
                if(retval == ErrorCode.Success) {
                    System.out.printf("\nAccount %d deposit %.2f succeeded\n", accountNumber, value);
                } else {
                    System.out.printf("\nAccount %d deposit %.2f failed\n", accountNumber, value);
                }
                return retval;
            }
        }

        // Account not found
        System.out.printf("Account %d not found.\n", accountNumber);
        return retval;
    }

    public ErrorCode withdrawFunds(int accountNumber, float value) {

        ErrorCode retval = ErrorCode.ErrorAccountNotFound;

        // Search the account list
        Iterator<AccountInterface> accountIterator = this.accountList.iterator();
        while (accountIterator.hasNext()) {
            AccountInterface account = accountIterator.next();
            if (accountNumber == account.getAccountNumber()) {
                // Found the account
                retval = account.withdrawFunds(value);
            }
        }

        return retval;
    }

    public float getBalance( int accountNumber) throws Exception {
        // Search the account list
        Iterator<AccountInterface> accountIterator = this.accountList.iterator();
        while (accountIterator.hasNext()) {
            AccountInterface account = accountIterator.next();
            // getBalance for requested account
            if (accountNumber == account.getAccountNumber()) {
                return account.getBalance();
            }
        }

        // Account not found
        System.out.printf("Account %d not found.\n", accountNumber);
        throw new Exception(" Account " + accountNumber + " not found");
    }

    public float getOverdraft( int accountNumber) throws Exception {
        // Search the account list
        Iterator<AccountInterface> accountIterator = this.accountList.iterator();
        while (accountIterator.hasNext()) {
            AccountInterface account = accountIterator.next();
            // getBalance for requested account
            if (accountNumber == account.getAccountNumber()) {
                return account.getOverdraft();
            }
        }

        // Account not found
        System.out.printf("Account %d not found.\n", accountNumber);
        throw new Exception("Account " + accountNumber + " not found");
    }

    public ErrorCode close() {
        PersistenceCsv persistenceCsv = new PersistenceCsv(this.filename);
        return (persistenceCsv.writeAccounts(this.accountList));
    }
}