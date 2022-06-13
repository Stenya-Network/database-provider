package net.stenya.nicky.databaseprovider.utils;

/**
 * The interface Callback.
 *
 * @param <Element> the type parameter
 */
public interface Callback<Element> {

    /**
     * Accept.
     *
     * @param element the element
     */
    void accept(final Element element);

}
