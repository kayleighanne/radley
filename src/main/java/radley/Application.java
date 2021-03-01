package radley;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Application {

    List<StandardAccount> standardAccountList = new ArrayList<>();
    List<LimitedAccount> limitedAccountList = new ArrayList<>();

    public Application() {
        // Create some accounts for test purposes
        for (int i = 0; i < 10; i++) {
            this.createAccount("TestAccount" + i, "TestAccount"+ i, 10 * i, ((i % 2) == 0));
        }
    }

    public int createAccount(String firstname, String lastName, float balance, boolean limitedAccount) {
        int accountNumber = 0;

        try {
            if (limitedAccount) {
                LimitedAccount account = new LimitedAccount(firstname, lastName, balance);
                this.limitedAccountList.add(account);
                accountNumber = account.getAccountNumber();

                System.out.printf("New limited account created: %d", accountNumber);
            } else {
                StandardAccount account = new StandardAccount(firstname, lastName, balance);
                this.standardAccountList.add(account);
                accountNumber = account.getAccountNumber();

                System.out.printf("New standard account created: %d", accountNumber);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountNumber;
    }

    public ErrorCode deleteAccount(int accountNumber) {
        // Search the limited account list
        Iterator<LimitedAccount> limitedAccountIterator = this.limitedAccountList.iterator();
        while (limitedAccountIterator.hasNext()) {
            LimitedAccount account = limitedAccountIterator.next();
            if (accountNumber == account.getAccountNumber()) {
                // Find the account to be deleted
                this.limitedAccountList.remove(account);
                System.out.printf("Limited account %d deleted.\n", accountNumber);
                return ErrorCode.Success;
            }
        }

        // Search the standard account list
        Iterator<StandardAccount> standardAccountIterator = this.standardAccountList.iterator();
        while (standardAccountIterator.hasNext()) {
            StandardAccount account = standardAccountIterator.next();
            if (accountNumber == account.getAccountNumber()) {
                // Find the account to be deleted
                this.standardAccountList.remove(account);
                System.out.printf("Standard account %d deleted.\n", accountNumber);
                return ErrorCode.Success;
            }
        }
        // Account not found
        System.out.printf("Account %d not found.", accountNumber);
        return ErrorCode.ErrorAccountNotFound;

    }

    public int listAccounts() {
        int countAccounts = 0;
        // Print standard account list
        // iterate through the accountList and print the summary information for each account
        System.out.println("\nStandard Accounts: ");
        Iterator<StandardAccount> standardAccountIterator = standardAccountList.iterator();
        while (standardAccountIterator.hasNext()) {
            StandardAccount account = standardAccountIterator.next();
            System.out.println("Account Name: " + account.getFullName() + " Account Number: " + account.getAccountNumber()
                    + " Balance: " + account.getBalance());
            countAccounts++;
        }


        // Print limited account list
        System.out.println("\nLimited Accounts: ");
        Iterator<LimitedAccount> limitedAccountIterator = limitedAccountList.iterator();
        while (limitedAccountIterator.hasNext()) {
            StandardAccount account = limitedAccountIterator.next();
            System.out.println("Account Name: " + account.getFullName() + " Account Number: " + account.getAccountNumber()
                    + " Balance: " + account.getBalance());
            countAccounts++;
        }

        return countAccounts;
    }

    public ErrorCode depositFunds(int accountNumber, float value){
        ErrorCode retval = ErrorCode.ErrorAccountNotFound;

        // Search the limited account list
        Iterator<LimitedAccount> limitedAccountIterator = this.limitedAccountList.iterator();
        while (limitedAccountIterator.hasNext()) {
            LimitedAccount account = limitedAccountIterator.next();
            if (accountNumber == account.getAccountNumber()) {
                // Found the account

                retval = account.depositFunds(value);
                if(retval == ErrorCode.Success) {
                    System.out.printf("Limited account %d deposit %f succeeded", accountNumber, value);
                } else {
                    System.out.printf("Limited account %d deposit %f failed", accountNumber, value);
                }
                return retval;
            }
        }

        // Search the standard account list
        Iterator<StandardAccount> standardAccountIterator = this.standardAccountList.iterator();
        while (standardAccountIterator.hasNext()) {
            StandardAccount account = standardAccountIterator.next();
            if (accountNumber == account.getAccountNumber()) {
                // Found the account
                retval = account.depositFunds(value);

                if(retval == ErrorCode.Success) {
                    System.out.printf("Standard account %d deposit %f succeeded", accountNumber, value);
                } else {
                    System.out.printf("Standard account %d deposit %f failed", accountNumber, value);
                }
                return retval;
            }
        }
        // Account not found
        System.out.printf("Account %d not found.", accountNumber);
        return retval;
    }

    public ErrorCode withdrawFunds(float accountNumber, float value) {

        ErrorCode retval = ErrorCode.ErrorAccountNotFound;

        // Search the limited account list
        Iterator<LimitedAccount> limitedAccountIterator = this.limitedAccountList.iterator();
        while (limitedAccountIterator.hasNext()) {
            LimitedAccount account = limitedAccountIterator.next();
            if (accountNumber == account.getAccountNumber()) {
                // Found the account
                if(account.withdrawFunds(value) == ErrorCode.Success) {
                    System.out.printf("Limited account %d withdrawal %f succeeded", accountNumber, value);
                } else {
                    System.out.printf("Limited account %d withdrawal %f failed", accountNumber, value);
                }
                return retval;
            }
        }

        // Search the standard account list
        Iterator<StandardAccount> standardAccountIterator = this.standardAccountList.iterator();
        while (standardAccountIterator.hasNext()) {
            StandardAccount account = standardAccountIterator.next();
            if (accountNumber == account.getAccountNumber()) {
                // Found the account
                if(account.withdrawFunds(value) == ErrorCode.Success) {
                    System.out.printf("Standard account %d withdrawal %f succeeded", accountNumber, value);
                } else {
                    System.out.printf("Standard account %d withdrawal %f failed", accountNumber, value);
                }
                return retval;
            }
        }
        // Account not found
        System.out.printf("Account %d not found.", accountNumber);
        return retval;
    }

    public float getBalance( int accountNumber) throws Exception {
        // Search the limited account list
        Iterator<LimitedAccount> limitedAccountIterator = this.limitedAccountList.iterator();
        while (limitedAccountIterator.hasNext()) {
            LimitedAccount account = limitedAccountIterator.next();
            if (accountNumber == account.getAccountNumber()) {
                return account.getBalance();
            }
        }

        // Search the standard account list
        Iterator<StandardAccount> standardAccountIterator = this.standardAccountList.iterator();
        while (standardAccountIterator.hasNext()) {
            StandardAccount account = standardAccountIterator.next();
            if (accountNumber == account.getAccountNumber()) {
                return account.getBalance();
            }
        }
        // Account not found
        System.out.printf("Account %d not found.", accountNumber);
        throw new Exception("Account " + accountNumber + "not found");
    }
}