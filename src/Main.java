//Ashley Grafner
//CSC 10606080, Computer Science 1 (Java)
//10/26/2022
//The goal of this game is to have a score of EXACTLY 30
//Each player will roll two dice, then you must choose the score of ONE of the dice or the TOTAL of the two dice rolled
//The value you choose is then added to your total score. If a player's score is ABOVE 30, your score is reset to ZERO.
//The two players will then switch. The game will end when a player earns a total score of EXACTLY 30.


import java.util.*;
class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        class Player {
            private String name = "unknown";
            private int score = 0;

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
                System.out.println("\nWhat is your name, Player: ");
                name = input.next();
                System.out.println("Welcome to the game, " + getName());
                System.out.println("-------------------------------------");
            }
        }

        class DiceGame {
            Player[] playerNames; //array of players (defined in main)
            public DiceGame(Player[] players) { //will run through each player
                this.playerNames = players;
            }

            public void display() {
                boolean gameInProgress = true;

                while (gameInProgress ) { //will run while true:
                    for (Player player : playerNames) { //runs for each player, think of it as switching turns
                        int diceRoll1 = (int) (Math.random() * 6) + 1; //dice 1
                        int diceRoll2 = (int) (Math.random() * 6) + 1; //dice 2
                        int diceRollTotal = diceRoll1 + diceRoll2; // total of dice 1 and dice 2

                        System.out.println("\nPlayer: " + player.getName() + ".... Your total score is: " + player.getScore()); //says player name and score
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
                            player.addScore(diceRoll1); //(score = getScore() + addScore) +Dice1
                        }
                        if (user_input.equals("2")) { //2 = dice2
                            player.addScore(diceRoll2);//(score = getScore() + addScore) +Dice2
                        }
                        if (user_input.equals("3")) { //3 = dice1+dice2
                            player.addScore(diceRollTotal); //(score = getScore() + addScore) +DiceTotal
                        }
                        System.out.println("Your new total score is: " + player.getScore()); //states score based on user_input

                        if (player.getScore() > 30) {
                            player.setScore(0);
                            System.out.println("Oh no! Your score is over 30. Your score is now reset back to 0.");
                        }
                        if (player.getScore() == 30) {
                            System.out.println("Your score is 30! Congratulations, Player: " + player.getName() + " you have won!");
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
        System.out.println("Hello! Welcome to 30 or Miss!");
        System.out.println("\nHere are the rules: ");
        System.out.println("The goal of this game is to have a score of EXACTLY 30.");
        System.out.println("Each player will roll two dice, then you must choose the score of ONE of the dice or the TOTAL of the two dice rolled.");
        System.out.println("The value you choose is then added to your total score. If a player's score is ABOVE 30, your score is reset to ZERO. ");
        System.out.println("The two players will then switch. The game will end when a player earns a total score of EXACTLY 30.");

        System.out.println("\nLets start the game!");
        System.out.println("-------------------------------------");

        Player[] arr;
        arr = new Player[2];
        arr[0] = new Player("unknown", 0);
        arr[1] = new Player("unknown", 0);

        DiceGame newDiceGame = new DiceGame(arr);

        arr[0].askName(); //asks for player 1 name
        arr[1].askName(); //asks for player 2 name

        newDiceGame.display(); //prints our actual game
    }
}