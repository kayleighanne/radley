package radley;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Application started at " + dtf.format(now));

        // Create 10 accounts and add to an array list
        List<StandardAccount> accountList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            try {
                // create a new standard account and add it to the list
                StandardAccount account = new StandardAccount("First" + i, "Last" + i, 10 * i);
                accountList.add(account);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // iterate through the accountList and print the summary information for each account
        Iterator<StandardAccount> accountIterator = accountList.iterator();
        while (accountIterator.hasNext()) {
            StandardAccount account = accountIterator.next();
            System.out.println("Account Name: " + account.getFullName() + " Account Number: " + account.getAccountNumber()
                    + " Balance: " + account.getBalance());
        }
    }

}
