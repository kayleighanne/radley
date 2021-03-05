package radley;

// import packages
import java.io.*;
import java.util.*;

public class PersistenceCsv implements PersistenceInterface {
    // define variables
    private String filename;
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String FILE_HEADER = "accountNumber,firstName,lastName,isLimited,balance";
    private static final int ACCOUNT_NUMBER_IDX = 0;
    private static final int FIRST_NAME_IDX = 1;
    private static final int LAST_NAME_IDX = 2;
    private static final int IS_LIMITED_IDX = 3;
    private static final int BALANCE_IDX = 4;
    private static final int NUM_TOKENS = 5;


    public PersistenceCsv(String filename) {
        this.filename = filename;
    }

    @Override
    public ErrorCode writeAccounts(List<AccountInterface> accountList) {
        // create new fileWriter
        FileWriter fileWriter = null;
        ErrorCode retval = ErrorCode.Success;

        try {
            fileWriter = new FileWriter(this.filename);

            fileWriter.append(FILE_HEADER);
            fileWriter.append(NEW_LINE_SEPARATOR);

            Iterator<AccountInterface> accountIterator = accountList.listIterator();
            while (accountIterator.hasNext()) {
                AccountInterface account = accountIterator.next();
                fileWriter.append(String.valueOf(account.getAccountNumber()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(account.getFirstName()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(account.getLastName()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(account.isLimitedAccount()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(account.getBalance()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
        } catch (IOException e) {
            retval = ErrorCode.ErrorWriteFile;
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                retval = ErrorCode.ErrorFlushFile;
            }
        }

        return retval;
    }


    public List<AccountInterface> readAccounts() {
        ErrorCode retval = ErrorCode.Success;

        // create fileReader and accountList
        BufferedReader fileReader = null;
        List<AccountInterface> accountList = new ArrayList<AccountInterface>();

        try {
            String line = "";

            fileReader = new BufferedReader(new FileReader(this.filename));
            fileReader.readLine();

            while ((line = fileReader.readLine()) != null) {
                String[] tokens = line.split(COMMA_DELIMITER);
                if (tokens.length == NUM_TOKENS) {
                    if (tokens[IS_LIMITED_IDX].equals("true")) {
                        AccountInterface account = new LimitedAccount(tokens[FIRST_NAME_IDX], tokens[LAST_NAME_IDX], Float.parseFloat(tokens[BALANCE_IDX]), Integer.parseInt(tokens[ACCOUNT_NUMBER_IDX]));
                        accountList.add(account);
                    } else {
                        AccountInterface account = new StandardAccount(tokens[FIRST_NAME_IDX], tokens[LAST_NAME_IDX], Float.parseFloat(tokens[BALANCE_IDX]), Integer.parseInt(tokens[ACCOUNT_NUMBER_IDX]));
                        accountList.add(account);
                    }

                } else {
                    System.out.printf("Error: malformed record [%s]", line);
                }
            }
        } catch (Exception e) {
            retval = ErrorCode.ErrorReadFile;
        }
        return accountList;
    }
}
