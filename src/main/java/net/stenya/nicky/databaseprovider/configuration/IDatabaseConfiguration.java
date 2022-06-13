package net.stenya.nicky.databaseprovider.configuration;

import com.google.gson.JsonObject;

import java.util.Map;

/**
 * The interface Database configuration.
 */
public interface IDatabaseConfiguration {
    /**
     * Load.
     */
    void load();

    /**
     * Save.
     */
    void save();

    /**
     * Gets values.
     *
     * @return the values
     */
    JsonObject getValues();
}
