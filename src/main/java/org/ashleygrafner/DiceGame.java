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
                int numberOfDice = diceRoll.getNumberOfDice();
                for (int i = 0; i < numberOfDice; i++) {
                    System.out.println(IntegerOrdinals.INTEGER_ORDINALS[i + 1] + " dice is: " + diceRoll.getRolledDice()[i]); //prints dice 1
                }
                System.out.println("The total of the two dice is: " + diceRoll.sumOfRolledDice()); //prints total of dice1+ dice 2

                System.out.println("Do you want to: 1.Keep dice1, 2.Keep dice2, 3.Keep the total of dice? (Please type 1 or 2 or 3): ");
                String user_input = input.nextLine();

                while (!user_input.equals("1") && !user_input.equals("2") && !user_input.equals("3")) { //if user does not enter 1,2 or 3:
                    System.out.println("Please choose 1, 2 or 3");
                    user_input = input.nextLine(); //loop until user_input = 1,2 or 3
                }
                if (user_input.equals("1")) {//1= dice1
                    player.addScore(diceRoll.getRolledDice()[0]); //(score = getScore() + addScore) +Dice1
                }
                if (user_input.equals("2")) { //2 = dice2
                    player.addScore(diceRoll.getRolledDice()[1]);//(score = getScore() + addScore) +Dice2
                }
                if (user_input.equals("3")) { //3 = dice1+dice2
                    player.addScore(diceRoll.sumOfRolledDice()); //(score = getScore() + addScore) +DiceTotal
                }
                System.out.println("Your new total score is: " + player.getScore()); //states score based on user_input

                if (player.getScore() > winningDieTotal) {
                    player.setScore(DEFAULT_STARTING_SCORE);
                    System.out.println("Oh no! Your score is over 30. Your score is now reset back to 0.");
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

}
