package main.java.org.ashleygrafner;

import java.util.Arrays;

public class DiceRoll {

    public static final int DEFAULT_NUMBER_OF_SIDES = 6;
    public static final int DEFAULT_NUMBER_OF_DICE = 2;

    private final int numberOfDice;
    private final int numberOfSides;

    private Integer[] rolledDice;

    public DiceRoll(int numberOfDice, int numberOfSides) {
        this.numberOfDice = numberOfDice;
        this.numberOfSides = numberOfSides;
    }

    public DiceRoll(int numberOfDice) {
        this(numberOfDice, DEFAULT_NUMBER_OF_SIDES);
    }

    public DiceRoll() {
        this(DEFAULT_NUMBER_OF_DICE);
    }

    public void rollDice() {
        Integer[] rolledDice = new Integer[numberOfDice];
        for (int i = 0; i < numberOfDice; i++) {
            rolledDice[i] = rollOneDie();
        }
        this.rolledDice = rolledDice;
    }

    public Integer[] getRolledDice() {
        return rolledDice;
    }

    public int sumOfRolledDice() {
        return Arrays.stream(rolledDice).reduce(0, Integer::sum);
    }

    public int getNumberOfDice() {
        return numberOfDice;
    }

    public int rollOneDie() {
        return (int) (Math.random() * numberOfSides) + 1;
    }
}