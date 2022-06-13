package net.stenya.nicky.databaseprovider.collection;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import net.stenya.nicky.databaseprovider.utils.Callback;
import org.bson.Document;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The type Database collection.
 */
public class DatabaseCollection implements IDatabaseCollection {

    private static final ExecutorService SERVICE = Executors.newCachedThreadPool();

    private final MongoCollection<Document> collection;

    /**
     * Instantiates a new Database collection.
     *
     * @param collection the collection
     */
    public DatabaseCollection(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    @Override
    public Document getDocument(String key, Object value) {
        return collection.find(Filters.eq(key, value)).first();
    }

    @Override
    public void createDocument(Document document) {
        collection.insertOne(document);
    }

    @Override
    public boolean deleteDocument(String key, Object value) {
        return collection.deleteOne(Filters.eq(key, value)).wasAcknowledged();
    }

    @Override
    public long updateDocument(String key, Object value, Document document) {
        return this.collection.updateOne(Filters.eq(key, value), document).getModifiedCount();
    }

    @Override
    public long replaceDocument(String key, Object value, Document document) {
        return this.collection.replaceOne(Filters.eq(key, value), document).getModifiedCount();
    }

    @Override
    public List<Document> collect() {
        final List<Document> documents = new ArrayList<>();
        for (Document document : collection.find()) {
            if (document != null)
                documents.add(document);
        }

        return documents;
    }

    @Override
    public void getDocumentAsync(String key, Object value, Callback<Document> callback) {
        this.executeAsync(() -> callback.accept(collection.find(Filters.eq(key, value)).first()));
    }

    @Override
    public void createDocumentAsync(Document document) {
        this.executeAsync(() -> collection.insertOne(document));
    }

    @Override
    public void deleteDocumentAsync(String key, Object value) {
        this.executeAsync(() -> collection.deleteOne(Filters.eq(key, value)));
    }

    @Override
    public void updateDocumentAsync(String key, Object value, Document document) {
        this.executeAsync(() -> collection.updateOne(Filters.eq(key, value), document));
    }

    @Override
    public void replaceDocumentAsync(String key, Object value, Document document) {
        this.executeAsync(() -> collection.replaceOne(Filters.eq(key, value), document));
    }

    @Override
    public void collectAsync(final Callback<List<Document>> callback) {
        this.executeAsync(() -> {
            final List<Document> documents = new ArrayList<>();
            for (Document document : collection.find()) {
                if (document != null)
                    documents.add(document);
            }

            callback.accept(documents);
        });
    }

    @Override
    public MongoCollection<Document> getRaw() {
        return this.collection;
    }

    @Override
    public boolean exsits(String key, Object value) {
        return this.getDocument(key, value) != null;
    }

    @Override
    public void executeAsync(final Runnable runnable) {
        try {
            SERVICE.submit(runnable).isDone();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
