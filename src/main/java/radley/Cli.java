package radley;

// import packages
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "rb", subcommands = {
        CreateAccount.class,
        DeleteAccount.class,
        ListAccounts.class,
        DepositFunds.class,
        WithdrawFunds.class,
        getBalance.class
})
public class Cli implements Runnable {
    public static void main(String[] args) {
        new CommandLine(new Cli()).execute(args);
    }

    @Override
    public void run() {
        new CommandLine(new Cli()).usage(System.out);
    }
}

@Command(name = "create", description = "Create a new account.")
class CreateAccount implements Callable<Integer> {
    @Option(names = "-f", required = true, description = "First name of account holder.")
    String firstname;
    @Option(names = "-s", required = true, description = "Surname of account holder.")
    String surname;
    @Option(names = "-b", description = "Current balance of account.")
    float balance;
    @Option(names = "-l", description = "Create a limited account.")
    boolean limited = false;


    @Override
    public Integer call() throws Exception {
        System.out.printf("FirstName: %s, LastName: %s, Balance: %f, Limited: %s\n", firstname, surname, balance, limited);
        Application app = new Application();
        app.createAccount(firstname, surname, balance, limited);
        app.close();
        return null;
    }
}

@Command(name="delete", description = "Delete Account")
class DeleteAccount implements Callable<Integer> {
    @Option(names = "-a", required = true, description = "Number of account to be deleted.") int account;

    @Override
    public Integer call() throws Exception {
        System.out.printf("Account: %d", account);
        Application app = new Application();
        app.deleteAccount(account);
        app.close();
        return null;
    }
}

@Command(name="list", description = "List Accounts")
class ListAccounts implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.printf("List");
        new Application().listAccounts();
        return null;
    }
}


@Command(name="deposit", description = "Deposit Funds")
class DepositFunds implements Callable<Integer> {
    @Option(names = "-v", required = true, description = "Value to be deposited.") float value;
    @Option(names = "-a", required = true, description = "Account Number.") int account;

    @Override
    public Integer call() throws Exception {
        System.out.printf("Funds deposited: %f", value);
        Application app = new Application();
        ErrorCode retval =  app.depositFunds(account, value);
        app.close();
        return null;
    }
}

@Command(name="withdraw", description = "Withdraw Funds")
class WithdrawFunds implements Callable<Integer> {
    @Option(names = "-v", required = true, description = "Value to be withdrawn.") float value;
    @Option(names = "-a", required = true, description = "Account Number.") int account;

    @Override
    public Integer call() throws Exception {
        System.out.printf("Funds withdrawn: %f", value);
        Application app = new Application();
        ErrorCode retval = app.withdrawFunds(account, value);
        app.close();
        return null;
    }
}

@Command(name="report", description = "Account Balance Report")
class getBalance implements Callable<Integer> {
    @Option(names ="-c", required = true, description = "Current balance.") float balance;
    @Option(names="-a", required = true, description = "Account Number.") int account;
    @Option(names="-f", description = "First Name") String firstname;
    @Option(names="-s", description = "Surname") String surname;
    @Option(names="-o", description = "Overdraft") float overdraft;

    @Override
    public Integer call() throws Exception {
        System.out.printf("Account Number: %d",account);
        System.out.printf("\nName: %s", firstname + " " + surname);
        System.out.printf("\nCurrent balance: %f", balance);
        System.out.printf("\nOverdraft remaining: %f", overdraft);
        new Application().getBalance(account);

        return null;
    }
}