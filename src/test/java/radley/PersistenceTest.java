package radley;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersistenceTest {

    List<AccountInterface> accountList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        // Create some accounts for test purposes
        try {
            for (int i = 0; i < 10; i++) {
                if (i % 2 == 0) {
                    accountList.add(new StandardAccount("First" + i, "Last" + i, 10 * i));
                } else {
                    accountList.add(new LimitedAccount("First" + i, "Last" + i, 10 * i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void writeAccountsAndReadBack() {
        File tempFile;
        try {
            tempFile = File.createTempFile("radley", "");
            tempFile.deleteOnExit();
            PersistenceCsv persistenceCsv = new PersistenceCsv(tempFile.getAbsolutePath());

            assertEquals(persistenceCsv.writeAccounts(this.accountList), ErrorCode.Success);

            List<AccountInterface> readBack = persistenceCsv.readAccounts();
            assertEquals(this.accountList.size(), readBack.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}