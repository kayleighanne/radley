package radley;

import java.util.List;

public interface PersistenceInterface {
    public ErrorCode writeAccounts(List<AccountInterface> account);
    public List<AccountInterface> readAccounts();
}
