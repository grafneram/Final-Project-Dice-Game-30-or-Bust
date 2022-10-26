//Ashley Grafner
//CSC 10606080, Computer Science 1 (Java)
//10/26/2022
//The goal of this game is to have a score of EXACTLY 30
//Each player will roll two dice, then you must choose the score of ONE of the dice or the TOTAL of the two dice rolled
//The value you choose is then added to your total score. If a player's score is ABOVE 30, your score is reset to ZERO.
//The two players will then switch. The game will end when a player earns a total score of EXACTLY 30.
import java.util.Scanner;
class Main {
    public static void main(String[] args) { //main method (go down to bottom)
        Scanner input = new Scanner(System.in);
        //used for input.next() to set name
        //used for input.next() for when user decides 1, 2 or 3



        class Player {
            private String name = "unknown"; //default name
            private int score = 0; //default score

            public Player(String setName, int setScore) { //constructor
                this.name = setName; //explicit setters
                this.score = setScore; //explicit setters
            }

            public String getName() {
                return name;
            }
            public void setName(String name) {
                this.name = name;
            }
            public int getScore() {
                return score;
            }
            public void setScore(int score) {
                this.score = score;
            }
            public void addScore(int addScore) {
             score = getScore() + addScore; //sets score to
            }

            public void askName() {
                final String ANSI_RESET = "\u001B[0m"; //sets text color back to basic
                final String ANSI_BLUE = "\u001B[36m"; //sets text color to blue

                System.out.println("\nWhat is your name, Player: ");
                name = input.next();
                System.out.println(ANSI_BLUE+"Welcome to the game, " + getName()+ANSI_RESET);
                System.out.println("-------------------------------------");
            }
        }





        class DiceGame {
            Player[]playerNames; //initializer for array
            public DiceGame(Player[] playerNames) { //calls our array in DiceGame
                this.playerNames = playerNames; //("setter") that will set our player names
                //explicit setter
            }

            public void display() {
                boolean gameInProgress = true;

                final String ANSI_YELLOW = "\u001B[33m"; //sets text color to yellow
                final String ANSI_RESET = "\u001B[0m"; //sets text color back to basic color

                while (gameInProgress) { //will run while true:
                    for (Player players: playerNames) { //Player players defines object for each item
                        //After colon defines the iterable set of items

                        //for loop will run through each player, think of switching turns

                        int diceRoll1 = (int) (Math.random() * 6) + 1; //dice 1
                        int diceRoll2 = (int) (Math.random() * 6) + 1; //dice 2
                        int diceRollTotal = diceRoll1 + diceRoll2; // total of dice 1 and dice 2

                        System.out.println("\nPlayer: " + ANSI_YELLOW + players.getName()+ ANSI_RESET + ".... Your total score is: " + players.getScore()); //says player name and score
                        System.out.println("First dice is: " + diceRoll1); //prints dice 1
                        System.out.println("Second dice is: " + diceRoll2);// prints dice 2
                        System.out.println("The total of the two dice is: " + diceRollTotal); //prints total of dice1+ dice 2

                        System.out.println("Do you want to: 1.Keep dice1, 2.Keep dice2, 3.Keep the total of dice? (Please type 1 or 2 or 3): ");
                        String user_input = input.nextLine();
                        while (!user_input.equals("1") && !user_input.equals("2") && !user_input.equals("3")) { //if user does not enter 1,2 or 3:
                            System.out.println("Please choose 1, 2 or 3");
                            user_input = input.nextLine(); //loop until user_input = 1,2 or 3
                        }
                        if (user_input.equals("1")) {//1= dice1
                            players.addScore(diceRoll1); //(score = getScore() + addScore) +Dice1
                        }
                        if (user_input.equals("2")) { //2 = dice2
                            players.addScore(diceRoll2);//(score = getScore() + addScore) +Dice2
                        }
                        if (user_input.equals("3")) { //3 = dice1+dice2
                            players.addScore(diceRollTotal); //(score = getScore() + addScore) +DiceTotal
                        }
                        System.out.println("Your new total score is: " + players.getScore()); //states score based on user_input

                        if (players.getScore() > 30) {
                            players.setScore(0);
                            System.out.println("Oh no! Your score is over 30. Your score is now reset back to 0.");
                        }
                        if (players.getScore() == 30) {
                            System.out.println("Your score is 30! Congratulations, Player: " + ANSI_YELLOW+players.getName()+ ANSI_RESET +" you have won!!!");
                            System.out.println("-------------------------------------");
                            gameInProgress = false; //will stop our gameInProgress loop because false
                            break; //closes out of loop
                        }
                        System.out.println("-------------------------------------");
                    }
                }
            }
        }
        // Game Rules:
        System.out.println("\nHello! Welcome to 30 or Miss!");
        System.out.println("\nHere are the rules: ");
        System.out.println("The goal of this game is to have a score of EXACTLY 30.");
        System.out.println("Each player will roll two dice, then you must choose the score of ONE of the dice or the TOTAL of the two dice rolled.");
        System.out.println("The value you choose is then added to your total score. If a player's score is ABOVE 30, your score is reset to ZERO. ");
        System.out.println("The two players will then switch. The game will end when a player earns a total score of EXACTLY 30.");

        System.out.println("\nLets start the game!");
        System.out.println("-------------------------------------");

        Player[] arr;
        arr = new Player[2]; //2 player objects
        arr[0] = new Player("unknown", 0); //defaults are name="unknown" and score=0
        arr[1] = new Player("unknown", 0);//defaults are name="unknown" and score=0

        arr[0].askName(); //asks for player 1 name
        arr[1].askName(); //asks for player 2 name

        DiceGame newDiceGame = new DiceGame(arr);
        newDiceGame.display(); //prints our actual game
    }
}
//Notes:
//I wanted to use color, so it is clear who is currently playing or who won
//Looked up how to use colored text

//Code should work for multiple players, currently have set to 2 players.
//Will need to change in Player array if more or less people are playing

//Struggled with having player turns (switching turns) so I separated Player class and DiceGame class