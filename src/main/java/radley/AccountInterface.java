package radley;

public interface AccountInterface {
    public boolean isLimitedAccount();
    public ErrorCode depositFunds(float value);
    public ErrorCode withdrawFunds(float value);
    public int getAccountNumber();
    public float getBalance();
    public String getFirstName();
    public String getLastName();
    public String getFullName();
    public float getOverdraft();
}
