package net.stenya.nicky.databaseprovider.utils;

/**
 * The type Pair.
 *
 * @param <First> the type parameter
 * @param <Last>  the type parameter
 */
public class Pair<First, Last> {

    private final First first;
    private final Last last;

    /**
     * Instantiates a new Pair.
     *
     * @param first the first
     * @param last  the last
     */
    public Pair(First first, Last last) {
        this.first = first;
        this.last = last;
    }

    /**
     * Gets last.
     *
     * @return the last
     */
    public Last getLast() {
        return last;
    }

    /**
     * Gets first.
     *
     * @return the first
     */
    public First getFirst() {
        return first;
    }
}
