import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private String accountNo;
    private String name;
    private long balance;
    private List<Transaction> transactions;

    public Account(String accountNo, String name) {
        this.accountNo = accountNo;
        this.name = name;
        balance = 0;
        transactions = new ArrayList<>();
    }

    public void deposit(long amount) {
        balance += amount;
        Transaction transaction = new Transaction(
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                , LocalTime.now().format(DateTimeFormatter.ofPattern("HH시 mm분 ss초"))
                , "입금"
                , amount
                , balance);
        transactions.add(transaction);
    }

    public void withdraw(long amount) {

        if (balance < amount) {
            System.out.println("잔고 초과입니다.");
            return;
        }
        balance -= amount;
        Transaction transaction = new Transaction(
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                , LocalTime.now().format(DateTimeFormatter.ofPattern("HH시 mm분 ss초"))
                , "출금"
                , amount
                , balance);
        transactions.add(transaction);
    }

    public long getBalance() {
        return balance;
    }

    public List<Transaction> getTransactionList() {
        return new ArrayList<>(transactions);
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNo='" + accountNo + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", transactions=" + transactions +
                '}';
    }
}
