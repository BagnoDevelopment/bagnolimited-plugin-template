package pl.bagnolimited.template.database.impl;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.bagnolimited.template.TemplatePlugin;
import pl.bagnolimited.template.database.Database;

@RequiredArgsConstructor
public final class MongoImpl implements Database {

    private final TemplatePlugin plugin;

    private MongoClient mongoClient;
    @Getter
    private MongoDatabase database;

    @Override
    public boolean connect() {
        try {
            final MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(plugin.getMainConfiguration().getString("mongo.connection-string")))
                    .build();
            this.mongoClient = MongoClients.create(mongoClientSettings);
            this.database = this.mongoClient.getDatabase(plugin.getMainConfiguration().getString("mongo.database"));

            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public void disconnect() {
        mongoClient.close();
    }

}