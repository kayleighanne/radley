package radley;

// create subclass Limited Account
public class LimitedAccount extends StandardAccount {
    public LimitedAccount(String firstName, String lastName, float balance) throws Exception {
        super(firstName, lastName, balance);
    }

    public LimitedAccount(String firstName, String lastName, float balance, int accountNumber) throws Exception {
        super(firstName, lastName, balance, accountNumber);
    }


    @Override
    public boolean isLimitedAccount() {
        return true;
    }

    // if account is limited set deposit limit of 50
    @Override
    public ErrorCode depositFunds(float value) {
        ErrorCode retval;

        float depositLimit = 50;
        if (value > depositLimit) {
            retval = ErrorCode.ErrorDepositLimit;
        } else {
            retval = super.depositFunds(value);
        }

        return retval;

    }


    // if account is limited set withdrawal limit to 100
    @Override
    public ErrorCode withdrawFunds(float value) {
        ErrorCode retval;

        float withdrawalLimit = 100;
        if (value > withdrawalLimit) {
            retval = ErrorCode.ErrorWithdrawalLimit;
        } else {
            retval = super.withdrawFunds(value);
        }

        return retval;
    }
}
