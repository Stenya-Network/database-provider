package net.stenya.nicky.databaseprovider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.stenya.nicky.databaseprovider.configuration.DatabaseConfiguration;
import net.stenya.nicky.databaseprovider.configuration.IDatabaseConfiguration;
import net.stenya.nicky.databaseprovider.connection.IDatabaseConnection;
import net.stenya.nicky.databaseprovider.connection.InternalConnection;
import net.stenya.nicky.databaseprovider.utils.Pair;

import java.nio.file.Path;
import java.util.Arrays;

/**
 * The type Database provider.
 */
public class DatabaseProvider {

    /**
     * The constant GSON.
     */
    public static final Gson GSON = new GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .create();

    /**
     * The constant DEFAULTS.
     */
    public static final Pair<String, Object>[] DEFAULTS = new Pair[] {
            new Pair<>("host", "127.0.0.1"),
            new Pair<>("port", 27017),
            new Pair<>("user.name", "root"),
            new Pair<>("user.password", "password"),
            new Pair<>("database", "server")
    };

    private static final IDatabaseConfiguration CONFIGURATION;

    static {

        CONFIGURATION = new DatabaseConfiguration(Path.of("./database.json"));
        CONFIGURATION.load();

        if (Arrays.stream(DEFAULTS).noneMatch(element -> CONFIGURATION.getValues().get(element.getFirst()) != null)) {

            final JsonObject object = CONFIGURATION.getValues();

            for (Pair<String, Object> element : DEFAULTS) {
                if (element.getLast() instanceof String) {
                    object.addProperty(element.getFirst(), (String) element.getLast());
                    continue;
                }
                if (element.getLast() instanceof Boolean) {
                    object.addProperty(element.getFirst(), (Boolean) element.getLast());
                    continue;
                }
                if (element.getLast() instanceof Number) {
                    object.addProperty(element.getFirst(), (Number) element.getLast());
                    continue;
                }
                object.add(element.getFirst(), GSON.toJsonTree(element.getLast()));
            }
        }

        CONFIGURATION.save();

    }

    private IDatabaseConnection connection;

    /**
     * Connect.
     */
    public void connect() {
        connection = new InternalConnection(CONFIGURATION.getValues());
        connection.connect();

    }

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public IDatabaseConnection getConnection() {
        return connection;
    }
}
