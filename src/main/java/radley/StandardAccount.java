package radley;

public class StandardAccount {

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

    private static int generateAccountNumber() {
        // Genreate a random number between 40000 and 60000
        int min = 40000, max = 60000;
        return (int) ((Math.random() * (max - min)) + min);

    }

    public ErrorCode depositFunds(float value) {
        ErrorCode retval = ErrorCode.Success;

        if (value > 0) {
            this.balance += value;
        } else {
            retval = ErrorCode.ErrorInvalidValue;
        }

        return retval;
    }

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

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public float getBalance() {
        return this.balance;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

}
