package radley;

public class StandardAccount implements AccountInterface {

    private float balance;
    private float overdraft = 100;
    private String firstName;
    private String lastName;
    private int accountNumber;

    public StandardAccount(String firstName, String lastName, float balance) throws Exception {

        if (firstName.isBlank() || lastName.isBlank()) {
            throw new Exception("Invalid Name");
        } else {
            this.accountNumber = generateAccountNumber();

            this.balance = balance;
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    public StandardAccount(String firstName, String lastName, float balance, int accountNumber) throws Exception {

        if (firstName.isBlank() || lastName.isBlank()) {
            throw new Exception("Invalid Name");
        } else {
            this.accountNumber = accountNumber;

            this.balance = balance;
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }


    private static int generateAccountNumber() {
        // Genreate a random number between 40000 and 60000
        int min = 40000, max = 60000;
        return (int) ((Math.random() * (max - min)) + min);

    }

    @Override
    public boolean isLimitedAccount() {
        return false;
    }

    @Override
    public ErrorCode depositFunds(float value) {
        ErrorCode retval = ErrorCode.Success;

        if (value > 0) {
            this.balance += value;
        } else {
            retval = ErrorCode.ErrorInvalidValue;
        }

        return retval;
    }

    @Override
    public ErrorCode withdrawFunds(float value) {
        ErrorCode retval = ErrorCode.Success;

        if (value <= 0) {
            retval = ErrorCode.ErrorInvalidValue;
        } else if (this.balance + this.overdraft >= value) {
            this.balance -= value;
        } else {
            retval = ErrorCode.ErrorInsuffcientFunds;
        }
        return retval;
    }

    @Override
    public int getAccountNumber() {
        return this.accountNumber;
    }

    @Override
    public float getBalance() {
        return this.balance;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public float getOverdraft() {
        return this.overdraft;
    }
}
