package radley;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LimitedAccountTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void depositFunds() {

        try {

            // Create a limited account with an initial balance
            LimitedAccount account = new LimitedAccount("First", "Last", 150);
            assertEquals(account.depositFunds(50), ErrorCode.Success);
            assertEquals(account.depositFunds(51), ErrorCode.ErrorDepositLimit);
            assertEquals(account.depositFunds(0), ErrorCode.ErrorInvalidValue);
            assertEquals(account.depositFunds(-50), ErrorCode.ErrorInvalidValue);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void withdrawFunds() {

        try {
            // Create a limited account with an initial balance
            LimitedAccount account = new LimitedAccount("First", "Last", 150);
            assertEquals(account.withdrawFunds(100), ErrorCode.Success);
            assertEquals(account.withdrawFunds(101), ErrorCode.ErrorWithdrawalLimit);
            assertEquals(account.withdrawFunds(0), ErrorCode.ErrorInvalidValue);
            assertEquals(account.withdrawFunds(-100), ErrorCode.ErrorInvalidValue);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}