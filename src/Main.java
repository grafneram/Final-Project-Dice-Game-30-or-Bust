import java.util.*;
class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        class DiceGame {
            private String name = "unknown";
            private int score = 0;

            public DiceGame(String setName, int setScore) { //constructor
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


            public void askName() {
                System.out.println("\nWhat is your name, Player: ");
                name = input.next();
                System.out.println("Welcome to the game, " + getName());
            }

            public void display() {
                while (score <= 30) {
                    int diceRoll1 = (int) (Math.random() * 6) + 1; //dice 1
                    int diceRoll2 = (int) (Math.random() * 6) + 1; //dice 2
                    int diceRollTotal = diceRoll1 + diceRoll2; // total of dice 1 and dice 2


                    System.out.println("\nPlayer: " + getName() + ".... Your total score is: " + getScore()); //says player name and score
                    System.out.println("First dice is: " + diceRoll1); //prints dice 1
                    System.out.println("Second dice is: " + diceRoll2);// prints dice 2
                    System.out.println("The total of the two dice is: " + diceRollTotal); //prints total of dice1+ dice 2

                    System.out.println("Do you want to: 1.Keep dice1, 2.Keep dice2, 3.Keep the total of dice? (Please type 1 or 2 or 3): ");
                    String user_input = input.nextLine();
                    while (!user_input.equals("1") && !user_input.equals("2") && !user_input.equals("3")) { //if user does not enter 1,2 or 3:
                        System.out.println("Please choose 1, 2 or 3");
                        user_input = input.nextLine(); //loop until user_input = 1,2 or 3
                    }
                    if (user_input.equals("1")) {
                        score = score + diceRoll1; //1= dice1
                    }
                    if (user_input.equals("2")) { //2 = dice2
                        score = score + diceRoll2;
                    }
                    if (user_input.equals("3")) { //3 = dice1+dice2
                        score = score + diceRollTotal;
                    }
                    System.out.println("Your new total score is: " + score); //states score based on user_input

                    if (score > 30) {
                        score = 0;
                        System.out.println("\nOh no! Your score is over 30. Your score is now reset back to 0.");
                    }
                    if (score == 30) {
                        System.out.println("Your score is 30! Congratulations " + getName() + " you have won!");
                        break; //quits out of our code, because a player won
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

        DiceGame[] arr;
        arr = new DiceGame[2];
        arr[0] = new DiceGame("unknown",0);
        arr[1] = new DiceGame("unknown",0);

        arr[0].askName();
        arr[1].askName();

        arr[0].display();
        arr[1].display();
    }
}