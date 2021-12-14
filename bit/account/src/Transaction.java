public class Transaction {

    private String transactionDate;
    private String transactionTime;
    private String kind;
    private long amount;
    private long balance;

    public Transaction(String transactionDate, String transactionTime, String kind, long amount, long balance) {
        this.transactionDate = transactionDate;
        this.transactionTime = transactionTime;
        this.kind = kind;
        this.amount = amount;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionDate='" + transactionDate + '\'' +
                ", transactionTime='" + transactionTime + '\'' +
                ", kind='" + kind + '\'' +
                ", amount=" + amount +
                ", balance=" + balance +
                '}';
    }
}
