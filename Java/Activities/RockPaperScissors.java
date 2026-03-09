import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        String[] choices = {"Rock", "Paper", "Scissors"};

        try {
            System.out.println("Welcome to Rock Paper Scissors Game");
            System.out.println("Enter your choice:");
            System.out.println("1 - Rock");
            System.out.println("2 - Paper");
            System.out.println("3 - Scissors");

            int userChoice = sc.nextInt();

            if (userChoice < 1 || userChoice > 3) {
                throw new IllegalArgumentException("Invalid choice! Please enter 1, 2, or 3.");
            }

            int computerChoice = rand.nextInt(3) + 1;

            System.out.println("You chose: " + choices[userChoice - 1]);
            System.out.println("Computer chose: " + choices[computerChoice - 1]);

            if (userChoice == computerChoice) {
                System.out.println("Result: It's a Draw!");
            } 
            else if ((userChoice == 1 && computerChoice == 3) ||
                     (userChoice == 2 && computerChoice == 1) ||
                     (userChoice == 3 && computerChoice == 2)) {
                System.out.println("Result: You Win!");
            } 
            else {
                System.out.println("Result: Computer Wins!");
            }

        } catch (Exception e) {
            System.out.println("Error: Invalid input. Please enter numbers only.");
        } finally {
            sc.close();
            System.out.println("Game Ended.");
        }
    }
}