import java.util.Scanner;

public class ATM {
    private User currentUser;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ATM!");
        System.out.print("User ID: ");
        String userId = scanner.nextLine();
        System.out.print("User PIN: ");
        String userPin = scanner.nextLine();
        
        // Authenticate user
        UserDatabase userDatabase = new UserDatabase();
        currentUser = userDatabase.authenticateUser(userId, userPin);
        
        if (currentUser != null) {
            System.out.println("Welcome, " + currentUser.getUserName() + "!");
            boolean isRunning = true;
            while (isRunning) {
                System.out.println("1. Check account balance");
                System.out.println("2. Deposit money");
                System.out.println("3. Withdraw money");
                System.out.println("4. Transfer money");
                System.out.println("5. View transaction history");
                System.out.println("6. Quit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                
                switch (choice) {
                    case 1:
                        checkAccountBalance();
                        break;
                    case 2:
                        deposit(scanner);
                        break;
                    case 3:
                        withdraw(scanner);
                        break;
                    case 4:
                        transfer(scanner);
                        break;
                    case 5:
                        viewTransactionHistory();
                        break;
                    case 6:
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice!");
                        break;
                }
            }
        } else {
            System.out.println("Invalid user ID or PIN.");
        }

        System.out.println("Thank you for using the ATM!");
        scanner.close();
    }
    
    private void checkAccountBalance() {
        System.out.println("Your account balance is: " + currentUser.getAccountBalance());
    }
    
    private void deposit(Scanner scanner) {
        System.out.print("Enter the amount you want to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character
        currentUser.deposit(amount);
        System.out.println("Deposit successful!");
    }
    
    private void withdraw(Scanner scanner) {
        System.out.print("Enter the amount you want to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character
        boolean success = currentUser.withdraw(amount);
        if (success) {
            System.out.println("Withdrawal successful!");
        } else {
            System.out.println("Insufficient funds or invalid amount!");
        }
    }
    
    private void transfer(Scanner scanner) {
        System.out.print("Enter the user ID you want to transfer to: ");
        String recipientId = scanner.nextLine();
        System.out.print("Enter the amount you want to transfer: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character
        boolean success = currentUser.transfer(recipientId, amount);
        if (success) {
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Invalid recipient ID or insufficient funds!");
        }
    }
    
    private void viewTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : currentUser.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }
    
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}

