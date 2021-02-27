package radley;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StandardAccountTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    void standardAccount() {
        // Create a standardAccount with an initial balance
        assertDoesNotThrow(() -> new StandardAccount("First", "Last", 150));
    }

    @Test
    void standardAccountInvalidName() {
        // Create an account with an initial balance
        assertThrows(Exception.class, () -> new StandardAccount("", "Last", 150));
    }

    @org.junit.jupiter.api.Test
    void depositFunds() {
        try {
            StandardAccount account = new StandardAccount("First", "Last", 150);
            assertEquals(account.depositFunds(50), ErrorCode.Success);
            assertEquals(account.getBalance(), 200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void depositInvalidFunds() {
        try {
            StandardAccount account = new StandardAccount("First", "Last", 150);
            assertEquals(account.depositFunds(0), ErrorCode.ErrorInvalidValue);
            assertEquals(account.depositFunds(-50), ErrorCode.ErrorInvalidValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void withdrawFunds() {
        try {
            StandardAccount account = new StandardAccount("First", "Last", 150);
            account.withdrawFunds(50);
            assertEquals(account.withdrawFunds(0), ErrorCode.ErrorInvalidValue);
            assertEquals(account.withdrawFunds(-100), ErrorCode.ErrorInvalidValue);
            assertEquals(account.getBalance(), 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void getAccountNumber() {
        try {
            StandardAccount account = new StandardAccount("First", "Last", 150);
            assertTrue(account.getAccountNumber() >= 40000);
            assertTrue(account.getAccountNumber() <= 60000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void getBalance() {
        try {
            StandardAccount account = new StandardAccount("First", "Last", 150);
            assertEquals(account.getBalance(), 150);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void getFirstName() {
        try {
            StandardAccount account = new StandardAccount("First", "Last", 150);
            assertEquals(account.getFirstName(), "First");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void getLastName() {
        try {
            StandardAccount account = new StandardAccount("First", "Last", 150);
            assertEquals(account.getLastName(), "Last");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void getFullName() {
        try {
            StandardAccount account = new StandardAccount("First", "Last", 150);
            assertEquals(account.getFullName(), "First Last");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}