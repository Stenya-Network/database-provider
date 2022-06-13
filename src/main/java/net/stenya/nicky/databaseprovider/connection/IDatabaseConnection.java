package net.stenya.nicky.databaseprovider.connection;

import com.mongodb.client.MongoCollection;
import net.stenya.nicky.databaseprovider.collection.IDatabaseCollection;
import org.bson.Document;

/**
 * The interface Database connection.
 */
public interface IDatabaseConnection {
    /**
     * Connect.
     */
    void connect();

    /**
     * Gets collection.
     *
     * @param name the name
     * @return the collection
     */
    IDatabaseCollection getCollection(String name);
}
