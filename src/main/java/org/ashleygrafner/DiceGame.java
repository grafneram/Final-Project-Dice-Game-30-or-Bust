package main.java.org.ashleygrafner;

import java.util.Arrays;
import java.util.Scanner;

public class DiceGame {

    public static final int DEFAULT_NUMBER_OF_PLAYERS = 2;
    public static final int DEFAULT_STARTING_SCORE = 0;
    public static final int DEFAULT_WINNING_DIE_TOTAL = 30;

    private final int winningDieTotal;
    private final Player[] players; //array of players (defined in main)
    private final DiceRoll diceRoll;

    public DiceGame(int numberOfPlayers, int numberOfDice, int winningDieTotal) {
        this.winningDieTotal = winningDieTotal;
        diceRoll = new DiceRoll(numberOfDice);
        players = generateDefaultPlayers(numberOfPlayers);
    }

    public DiceGame(Player[] players) { //will run through each player
        winningDieTotal = DEFAULT_WINNING_DIE_TOTAL;
        this.players = players;
        diceRoll = new DiceRoll();
    }

    public DiceGame(int numberOfPlayers) {
        this(generateDefaultPlayers(numberOfPlayers));
    }

    public DiceGame() {
        this(DEFAULT_NUMBER_OF_PLAYERS);
    }

    private static Player[] generateDefaultPlayers(int numberOfPlayers) {
        Player[] players = new Player[numberOfPlayers];
        players = Arrays.stream(players).map(player -> new Player()).toArray(Player[]::new);
        return players;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void display() {
        Scanner input = new Scanner(System.in);
        boolean gameInProgress = true;

        final String ANSI_YELLOW = "\u001B[33m"; //sets text color to yellow
        final String ANSI_RESET = "\u001B[0m"; //sets text color back to basic color

        while (gameInProgress) { //will run while true:
            for (Player player : players) { //runs for each player, think of it as switching turns
                System.out.println("\nPlayer: " + ANSI_YELLOW + player.getName() + ANSI_RESET + ".... Your total score is: " + player.getScore()); //says player name and score

                diceRoll.rollDice();
                for (int i = 0; i < diceRoll.getNumberOfDice(); i++) {
                    System.out.println(IntegerNames.ORDINALS[i + 1] + " dice is: " + diceRoll.getRolledDice()[i]); //prints dice 1
                }
                System.out.println("The total of the " + IntegerNames.NAMES[diceRoll.getNumberOfDice()] + " dice is: " + diceRoll.sumOfRolledDice()); //prints total of dice1+ dice 2

                System.out.println("Do you want to: " + buildOptionsQuestion(diceRoll.getNumberOfDice()));
                String user_input = input.nextLine();

                while (!validateUserDieRollOption(user_input, diceRoll.getNumberOfDice())) { //if user does not enter 1,2 or 3:
                    System.out.println(buildOptionsQuestion(diceRoll.getNumberOfDice()));
                    user_input = input.nextLine(); //loop until user_input = 1,2 or 3
                }
                int user_input_int = Integer.parseInt(user_input);

                if (user_input_int == diceRoll.getNumberOfDice() + 1) {
                    player.addScore(diceRoll.sumOfRolledDice()); //(score = getScore() + addScore) +DiceTotal
                } else {
                    player.addScore(diceRoll.getRolledDice()[user_input_int - 1]);//(score = getScore() + addScore) +Dice2
                }
                System.out.println("Your new total score is: " + player.getScore()); //states score based on user_input

                if (player.getScore() > winningDieTotal) {
                    player.resetScore();
                    System.out.println("Oh no! Your score is over " + winningDieTotal + ". Your score is now reset back to 0.");
                }
                if (player.getScore() == winningDieTotal) {
                    System.out.println("Your score is " + winningDieTotal + "! Congratulations, Player: " + ANSI_YELLOW + player.getName() + ANSI_RESET + " you have won!");
                    System.out.println("-------------------------------------");
                    gameInProgress = false; //will stop our gameInProgress loop because false
                    break; //closes out of loop
                }
                System.out.println("-------------------------------------");
            }
        }
    }

    private static String buildOptionsQuestion(int numberOfDice) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= numberOfDice; i++) {
            builder.append(i).append(".Keep die").append(i).append(", ");
        }
        builder.append(numberOfDice + 1).append(".Keep the total of dice? (Please type your selection): ");
        return builder.toString();
    }

    private static boolean validateUserDieRollOption(String userInput, int numberOfDice) {
        if (userInput.length() != 1) {
            return false;
        }
        for (int i = 1; i <= numberOfDice + 1; i++) {
            if (userInput.equals(Integer.toString(i))) {
                return true;
            }
        }
        return false;
    }

}
