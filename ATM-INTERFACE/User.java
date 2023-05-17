import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String userPin;
    private String userName;
    private double accountBalance;
    private List<Transaction> transactionHistory;

    public User(String userId, String userPin, String userName, double accountBalance) {
        this.userId = userId;
        this.userPin = userPin;
        this.userName = userName;
        this.accountBalance = accountBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && accountBalance >= amount) {
            accountBalance -= amount;
            Transaction transaction = new Transaction("Withdrawal", -amount);
            transactionHistory.add(transaction);
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            accountBalance += amount;
            Transaction transaction = new Transaction("Deposit", amount);
            transactionHistory.add(transaction);
        }
    }

    public boolean transfer(String recipientId, double amount) {
        if (recipientId != null && amount > 0 && accountBalance >= amount) {
            accountBalance -= amount;
            recipientId.deposit(amount);
            Transaction transaction = new Transaction("Transfer to " + recipientId.getUserName(), -amount);
            transactionHistory.add(transaction);
            recipientId.addTransaction(new Transaction("Received from " + getUserName(), amount));
            return true;
        }
        return false;
    }

    public boolean authenticate(String userPin) {
        return this.userPin.equals(userPin);
    }
}