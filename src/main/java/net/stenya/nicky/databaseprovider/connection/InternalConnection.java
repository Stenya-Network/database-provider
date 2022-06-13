package net.stenya.nicky.databaseprovider.connection;

import com.google.gson.JsonObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import net.stenya.nicky.databaseprovider.collection.DatabaseCollection;
import net.stenya.nicky.databaseprovider.collection.IDatabaseCollection;

import java.io.Closeable;

/**
 * The type Internal connection.
 */
public class InternalConnection implements IDatabaseConnection, Closeable {

    /**
     * The constant CONNECTION_STRING.
     */
    public static final String CONNECTION_STRING = "mongodb://%s:%d";

    private final String host, databaseName;
    private final int port;

    private boolean connected;

    private MongoClient client;
    private MongoDatabase database;

    /**
     * Instantiates a new Internal connection.
     *
     * @param config the config
     */
    public InternalConnection(JsonObject config) {
        this.host = config.get("host").getAsString();
        this.databaseName = config.get("database").getAsString();
        this.port = config.get("port").getAsInt();
    }

    @Override
    public void connect() {
        if (connected)
            throw new IllegalStateException("Database already connected!");

        this.client = MongoClients.create(CONNECTION_STRING.formatted(host, port));
        this.database = client.getDatabase(databaseName);

        connected = true;
    }

    @Override
    public IDatabaseCollection getCollection(final String name) {
        if (!connected)
            throw new IllegalStateException("Database not connected!");

        return new DatabaseCollection(database.getCollection(name));
    }

    @Override
    public void close() {
        if (client != null)
            client.close();
    }
}
