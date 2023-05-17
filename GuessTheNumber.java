import java.util.Scanner;

public class GuessTheNumber {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        

        // Generate a random number between 1 and 100.
        int randomNumber = (int) (Math.random() * 100) + 1;

        // Set the number of attempts to 10.
        int numberOfAttempts = 10;

        // Start the game loop.
        while (numberOfAttempts > 0) {

            // Get the user's guess.
            System.out.println("Guess a number between 1 and 100: ");
            int guess = sc.nextInt();

            // Check if the user guessed the number correctly.
            if (guess == randomNumber) {
                System.out.println("Congratulations! You guessed the number correctly!");
                break;
            } else if (guess < randomNumber) {
                System.out.println("Your guess is too low.");
            } else {
                System.out.println("Your guess is too high.");
            }

            // Decrement the number of attempts.
            numberOfAttempts--;
        }

        // Display the result of the game.
        if (numberOfAttempts == 0) {
            System.out.println("You lost! The number was " + randomNumber);
        } else {
            System.out.println("You won! You guessed the number in " + numberOfAttempts + " attempts.");
        }
        sc.close();
    }
}