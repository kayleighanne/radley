package radley;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createAccount() {
        Application application = new Application();
        int accountNumber = application.createAccount("First", "Last", 100, false);
        assertNotEquals(accountNumber, 0);
    }

    @Test
    void deleteAccount() {

        Application application = new Application();
        int accountNumber = application.createAccount("First", "Last", 100, false);
        assertNotEquals(accountNumber, 0);
        assertEquals(ErrorCode.Success, application.deleteAccount(accountNumber));

    }

    @Test
    void listAccounts() {
        int numberOfAccounts = 10;
        Application application = new Application();

        for (int i = 0; i < numberOfAccounts; i++) {
            int accountNumber = application.createAccount("First" + i, "Last" + i, 100, false);
        }

        assertEquals(numberOfAccounts, application.listAccounts());
    }

    @Test
    void depositFunds() {
        try {
            float initialBalance = 200;
            float depositValue = 20;

            Application application = new Application();
            int accountNumber = application.createAccount("First", "Last", initialBalance, false);
            assertNotEquals(0, accountNumber);

            assertEquals(ErrorCode.Success, application.depositFunds(accountNumber, depositValue));
            assertEquals(initialBalance + depositValue, application.getBalance(accountNumber));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void withdrawFunds() {
        try {
            float initialBalance = 200;
            float withdrawalValue = 20;

            Application application = new Application();
            int accountNumber = application.createAccount("First", "Last", initialBalance, false);
            assertNotEquals(0, accountNumber);

            assertEquals(ErrorCode.Success, application.withdrawFunds(withdrawalValue, accountNumber));
            assertEquals(initialBalance + withdrawalValue, application.getBalance(accountNumber));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}