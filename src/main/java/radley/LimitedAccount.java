package radley;

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
