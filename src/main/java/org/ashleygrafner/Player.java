package main.java.org.ashleygrafner;

import java.util.Scanner;

public class Player {
    public static final String DEFAULT_USERNAME = "unknown";
    public static final int STARTING_SCORE = 0;
    private String name;
    private int score = 0;

    public Player(String setName, int setScore) { //constructor
        this.name = setName; //explicit setters
        this.score = setScore; //explicit setters
    }

    public Player() {
        this(DEFAULT_USERNAME, STARTING_SCORE);
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

    public void resetScore() {
        score = STARTING_SCORE;
    }

    public void addScore(int addScore) {
        score = getScore() + addScore; //sets score to
    }

    public void askName() {
        Scanner input = new Scanner(System.in);

        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLUE = "\u001B[36m";

        System.out.println("\nWhat is your name, Player: ");
        name = input.next();
        System.out.println(ANSI_BLUE + "Welcome to the game, " + getName() + ANSI_RESET);
        System.out.println("-------------------------------------");
    }
}
