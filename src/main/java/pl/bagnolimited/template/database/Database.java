package pl.bagnolimited.template.database;

public interface Database {
    boolean connect();
    void disconnect();
}