import java.util.ArrayList;
import java.util.List;

public class Bank {

    private List<Account> accountList;
    private int totalAccount;

    public Bank() {
        accountList = new ArrayList<>();
        totalAccount = 0;
    }

    public void addAccount(String accountNo, String name) {

        Account account = new Account(accountNo, name);
        for (Account ac : accountList) {
            if (ac.getAccountNo().equals(account.getAccountNo())) {
                System.out.println(account.getAccountNo()+"의 계좌는 이미 존재하는 계좌입니다.");
                return;
            }
        }
        accountList.add(account);
        totalAccount++;

    }

    public Account getAccount(String accountNo) {

        for (Account ac : accountList) {
            if (ac.getAccountNo().equals(accountNo))
                return ac;
        }
        return null;
    }

    public List<Account> findAccounts(String name) {

        List<Account> findList = new ArrayList();
        for (Account ac : accountList) {
            if (ac.getName().equals(name)) {
                findList.add(ac);
            }
        }
        return findList;
    }

    public List<Account> getAccounts() {

        return new ArrayList<>(accountList);
    }

    public int getTotalAccount() {
        return totalAccount;
    }
}
