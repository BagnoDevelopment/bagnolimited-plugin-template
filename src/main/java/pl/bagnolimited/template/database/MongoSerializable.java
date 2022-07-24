package pl.bagnolimited.template.database;

import org.bson.Document;

public interface MongoSerializable {
    Document toDocument();
}