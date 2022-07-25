package pl.bagnolimited.template.database;

import lombok.NonNull;

public interface Database {
    boolean connect(@NonNull String connectionString, @NonNull String databaseName);
    void disconnect();
}