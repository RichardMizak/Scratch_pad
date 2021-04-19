package sk.kosickaakademia.sk.scratchpad.database;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import sk.kosickaakademia.sk.scratchpad.util.Tasks;

import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class Database implements Mongo {
//dbname: TaskDB
//collection: Tasks
private static final MongoClient mongoClient = new MongoClient();
    private static MongoDatabase database;
    private static Document docs;
    private static MongoCollection<Document> collection;
    private static Date date = new Date();
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean insertTask(String title, String task, int priority, double price) {
        if(title==null || title.equals("") || task==null || task.equals("") || priority<1 || priority>3 )
            return false;
        if(price==0){
            database = mongoClient.getDatabase("TaskDB");
            collection = database.getCollection("Tasks");
            docs = new Document("task", task);
            docs.append("date", date);
            docs.append("title", title);
            docs.append("task", task);
            docs.append("priority", priority);
            docs.append("done", false);
            collection.insertOne(docs);
            return true;
        }else {
            database = mongoClient.getDatabase("TaskDB");
            collection = database.getCollection("Tasks");
            docs = new Document("task", task);
            docs.append("date", date);
            docs.append("title", title);
            docs.append("task", task);
            docs.append("priority", priority);
            docs.append("price", price);
            docs.append("done", false);
            collection.insertOne(docs);
            return true;
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void setTaskToDone(int id) {

    }

    @Override
    public List<Tasks> getAllTasks() {
        database = mongoClient.getDatabase("TaskDB");
        collection = database.getCollection("Tasks");
        FindIterable<Document> iterDoc = collection.find();
        for (Document document : iterDoc) {
            System.out.println(document);
        }
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
