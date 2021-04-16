package sk.kosickaakademia.sk.scratchpad.database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import sk.kosickaakademia.sk.scratchpad.util.Tasks;

import java.util.Date;
import java.util.List;


public class Database implements Mongo {
//dbname: TaskDB
//collection: Tasks
private static final MongoClient mongoClient = new MongoClient();
    private static MongoDatabase database;
    private static Document docs;
    private static MongoCollection<Document> collection;
    private static Date date = new Date();
    @Override
    public void insertTask(Tasks task) {
        database=mongoClient.getDatabase("TaskDB");
        collection = database.getCollection("Tasks");
        docs=new Document("task",task);
        collection.insertOne(docs);
    }
    @Override
    public void setTaskToDone(int id) {

    }

    @Override
    public List<Tasks> getAllTasks() {
        return null;
    }

    @Override
    public List<Tasks> getAllTasks(boolean done) {
        return null;
    }

    @Override
    public List<Tasks> getAllTasksByPriority() {
        return null;
    }

    @Override
    public List<Tasks> getAllTasksByName(String name) {
        return null;
    }

    @Override
    public void DeleteDoneTasks() {

    }
}
