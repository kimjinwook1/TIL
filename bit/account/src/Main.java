import java.util.List;

public class Main {

    public static void main(String[] args) {

        Bank bank = new Bank();
        bank.addAccount("1111", "kim");
        bank.addAccount("2222", "lee");
        bank.addAccount("3333", "park");
        bank.addAccount("4444", "hong");
        bank.addAccount("5555", "hong");
        bank.addAccount("6666", "kim");
        bank.addAccount("7777", "lee");
        bank.addAccount("7777", "123");

        System.out.println("***계좌번호가 2222인 계좌 조회***");
        Account getAccount = bank.getAccount("2222");
        System.out.println("getAccount = " + getAccount);

        System.out.println("\n***이름이 hong인 사람의 계좌 조회***");
        List<Account> findAccount = bank.findAccounts("hong");
        for (Account a : findAccount) {
            System.out.println("a = " + a);
        }

        System.out.println("\n***모든 계좌 목록 조회***");
        List<Account> accounts = bank.getAccounts();
        for (Account a : accounts) {
            System.out.println("a = " + a);
        }

        System.out.println("\n***계좌번호가 1111인 계좌에 5만원 입금***");
        Account kim = bank.getAccount("1111");
        kim.deposit(50000);
        System.out.println(kim);

        System.out.println("\n***계좌번호가 1111인 계좌에 5000원 출금***");
        kim.withdraw(5000);
        System.out.println(kim);

        System.out.println("\n***계좌번호가 1111인 계좌에 5000원 출금***");
        kim.withdraw(5000);
        System.out.println(kim);

        System.out.println("\n***계좌번호가 1111인 계좌의 잔고 확인***");
        System.out.println("kim님의 \'1111\'  계좌 잔액: " + kim.getBalance());

        System.out.println("\n***계좌번호가 1111인 계좌의 거래내역 확인하기***");
        List<Transaction> transactionList = kim.getTransactionList();
        for (Transaction transaction : transactionList) {
            System.out.println("transaction = " + transaction);
        }
        System.out.println("\n***계좌번호가 1111인 계좌에 잔고 초과해서 출금시도해보기***");
        kim.withdraw(100000);

    }
}
