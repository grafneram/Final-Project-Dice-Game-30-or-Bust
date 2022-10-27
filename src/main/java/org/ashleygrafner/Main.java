package main.java.org.ashleygrafner;//Ashley Grafner
//CSC 10606080, Computer Science 1 (Java)
//10/26/2022
//The goal of this game is to have a score of EXACTLY 30
//Each player will roll two dice, then you must choose the score of ONE of the dice or the TOTAL of the two dice rolled
//The value you choose is then added to your total score. If a player's score is ABOVE 30, your score is reset to ZERO.
//The two players will then switch. The game will end when a player earns a total score of EXACTLY 30.
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {

        // Game Rules:
        System.out.println("Hello! Welcome to 30 or Miss!");
        System.out.println("\nHere are the rules: ");
        System.out.println("The goal of this game is to have a score of EXACTLY 30.");
        System.out.println("Each player will roll two dice, then you must choose the score of ONE of the dice or the TOTAL of the two dice rolled.");
        System.out.println("The value you choose is then added to your total score. If a player's score is ABOVE 30, your score is reset to ZERO. ");
        System.out.println("The two players will then switch. The game will end when a player earns a total score of EXACTLY 30.");

        System.out.println("\nLets start the game!");
        System.out.println("-------------------------------------");

        DiceGame diceGame = new DiceGame(2);
        Player[] players = diceGame.getPlayers();;
        Arrays.stream(players).forEach(Player::askName);

        diceGame.display(); //prints our actual game
    }
}

//Notes:`
//I wanted to use color, so it is clear who is currently playing or who won
//Looked up how to use colored text

//Code should work for multiple players, currently have set to 2 players