package net.stenya.nicky.databaseprovider.configuration;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.stenya.nicky.databaseprovider.DatabaseProvider;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

/**
 * The type Database configuration.
 */
public class DatabaseConfiguration implements IDatabaseConfiguration {

    private static final JsonParser PARSER = new JsonParser();
    private final File configPath;

    private JsonObject jsonObject;

    /**
     * Instantiates a new Database configuration.
     *
     * @param configPath the config path
     */
    public DatabaseConfiguration(Path configPath) {
        this.configPath = configPath.toFile();
    }

    @Override
    public void load() {
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(configPath), StandardCharsets.UTF_8)) {
            jsonObject = PARSER.parse(new BufferedReader(reader)).getAsJsonObject();
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        if (jsonObject == null)
            jsonObject = new JsonObject();
    }

    @Override
    public void save() {
        if (configPath == null) return;

        if (configPath.exists()) {
            boolean deleted = configPath.delete();
            if (!deleted)
                throw new RuntimeException("Can't delete config file!");
        }

        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(configPath), StandardCharsets.UTF_8)) {
            DatabaseProvider.GSON.toJson(jsonObject, writer);
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }

    @Override
    public JsonObject getValues() {
        return jsonObject;
    }
}
