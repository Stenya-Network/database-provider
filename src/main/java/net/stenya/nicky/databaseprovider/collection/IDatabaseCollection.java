package net.stenya.nicky.databaseprovider.collection;

import com.mongodb.client.MongoCollection;
import net.stenya.nicky.databaseprovider.utils.Callback;
import org.bson.Document;

import java.util.List;

/**
 * The interface Database collection.
 */
public interface IDatabaseCollection {

    /**
     * Gets document.
     *
     * @param key   the key
     * @param value the value
     * @return the document
     */
//sync
    Document getDocument(final String key, final Object value);

    /**
     * Create document.
     *
     * @param document the document
     */
    void createDocument(final Document document);

    /**
     * Delete document boolean.
     *
     * @param key   the key
     * @param value the value
     * @return the boolean
     */
    boolean deleteDocument(final String key, final Object value);

    /**
     * Update document long.
     *
     * @param key      the key
     * @param value    the value
     * @param document the document
     * @return the long
     */
    long updateDocument(final String key, final Object value, final Document document);

    /**
     * Replace document long.
     *
     * @param key      the key
     * @param value    the value
     * @param document the document
     * @return the long
     */
    long replaceDocument(final String key, final Object value, final Document document);

    /**
     * Collect list.
     *
     * @return the list
     */
    List<Document> collect();

    /**
     * Gets document async.
     *
     * @param key      the key
     * @param value    the value
     * @param callback the callback
     */
//async
    void getDocumentAsync(final String key, final Object value, final Callback<Document> callback);

    /**
     * Create document async.
     *
     * @param document the document
     */
    void createDocumentAsync(final Document document);

    /**
     * Delete document async.
     *
     * @param key   the key
     * @param value the value
     */
    void deleteDocumentAsync(final String key, final Object value);

    /**
     * Update document async.
     *
     * @param key      the key
     * @param value    the value
     * @param document the document
     */
    void updateDocumentAsync(final String key, final Object value, final Document document);

    /**
     * Replace document async.
     *
     * @param key      the key
     * @param value    the value
     * @param document the document
     */
    void replaceDocumentAsync(final String key, final Object value, final Document document);

    /**
     * Collect async.
     *
     * @param callback the callback
     */
    void collectAsync(final Callback<List<Document>> callback);

    /**
     * Gets raw.
     *
     * @return the raw
     */
// general
    MongoCollection<Document> getRaw();

    /**
     * Exsits boolean.
     *
     * @param key   the key
     * @param value the value
     * @return the boolean
     */
    boolean exsits(final String key, final Object value);

    /**
     * Execute async.
     *
     * @param runnable the runnable
     */
    void executeAsync(Runnable runnable);
}
