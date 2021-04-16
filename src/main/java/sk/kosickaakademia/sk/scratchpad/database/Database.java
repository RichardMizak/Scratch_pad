package sk.kosickaakademia.sk.scratchpad.database;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.MongoClient;
import org.json.simple.JSONObject;
import sk.kosickaakademia.sk.scratchpad.util.Tasks;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Database {
    private static final MongoClient mongoClient = new MongoClient();
    private static MongoDatabase database;
    private static Document docs;
    private static MongoCollection<Document> collection;


    public void insertNewTask(Tasks task){
        collection.insertOne(new Document("task", new Gson().toJson(task)));
    }
}
