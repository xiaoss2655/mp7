package myRPG;
import java.util.Random;

public class Dice {

    /** Private random number generator. */
    Random myRand;
    /** Number of sides for this dice. */
    int numSides;

    /**
     * Create a new dice with a given number of sides.
     *
     * @param sides the number of sides our dice should have
     */
    public Dice(final int sides) {
        this.myRand = new Random();
        this.numSides = sides;
    }

    /**
     * Rolls our simulated dice.
     * <p>
     * Uses a pseudorandom number generator to determine the side to return.
     *
     * @return the number the die rolled
     */
    public int roll() {
        return (myRand.nextInt(numSides) + 1);
    }
}
