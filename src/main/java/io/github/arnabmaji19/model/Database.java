package io.github.arnabmaji19.model;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

//Singleton class
public class Database {
    private static final String DATABASE_NAME = "ShopCartDatabase";
    private static Database instance = new Database();
    private MongoDatabase database;

    private Database(){}

    public void connect(){
        //Setting Up MongoDatabase for POJOs
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry)
                .build();
        MongoClient client = MongoClients.create(settings);
        database = client.getDatabase(DATABASE_NAME);
    }

    public static Database getInstance() {
        return instance;
    }

    public MongoDatabase getDatabaseReference(){
        return database;
    }

    public MongoCollection<Product> getProductsCollection(){
        return database.getCollection("products", Product.class);
    }

    public MongoCollection<User> getUsersCollection(){
        return database.getCollection("users", User.class);
    }
}
