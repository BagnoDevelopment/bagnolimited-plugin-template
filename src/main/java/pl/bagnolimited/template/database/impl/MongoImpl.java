package pl.bagnolimited.template.database.impl;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.bagnolimited.template.TemplatePlugin;
import pl.bagnolimited.template.database.Database;

@RequiredArgsConstructor
public final class MongoImpl implements Database {

    private final TemplatePlugin plugin;

    private MongoClient mongoClient;
    @Getter
    private MongoDatabase database;

    // Try catch is necessary here because there is no other method to check if the database connection has been successful.
    @Override
    public boolean connect(@NonNull String connectionString, @NonNull String databaseName) {
        try {
            final MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(connectionString))
                    .build();
            this.mongoClient = MongoClients.create(mongoClientSettings);
            this.database = this.mongoClient.getDatabase(databaseName);

            return true;
        } catch (MongoException exception) {
            return false;
        }
    }

    @Override
    public void disconnect() {
        mongoClient.close();
    }

}