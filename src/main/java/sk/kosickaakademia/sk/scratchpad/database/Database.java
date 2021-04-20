package sk.kosickaakademia.sk.scratchpad.database;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sk.kosickaakademia.sk.scratchpad.util.Tasks;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class Database implements Mongo,MongoJSON{
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
    public boolean setTaskToDone() {
        BasicDBObject query = new BasicDBObject();
        query.put("done", false);
        BasicDBObject doc = new BasicDBObject();
        doc.put("done", true);
        BasicDBObject updateObj = new BasicDBObject();
        updateObj.put("$set", doc);
        database.getCollection("Tasks").updateOne(query, updateObj);
        return true;
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public List<Tasks> getAllTasks() {
        database = mongoClient.getDatabase("TaskDB");
        collection = database.getCollection("Tasks");
        List<Tasks> list = new ArrayList<>();
        FindIterable<Document> iterDoc = collection.find();
        for (Document document : iterDoc) {
            String title = document.getString("title");
            int priority = document.getInteger("priority");
            boolean done = document.getBoolean("done");
            Date date = document.getDate("date");
            ObjectId id = document.getObjectId("_id");
            Tasks tasks;
            if( document.containsKey("price") ) {
                double price = document.getDouble("price");
                tasks= new Tasks(title,date,priority,(int) price,done );
            }else{
                tasks = new Tasks(title,priority,done,date);
            }
            tasks.setId(id);
            list.add(tasks);
        }
        return list;
        }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public List<Tasks> getAllTasks(boolean done) {
        database = mongoClient.getDatabase("TaskDB");
        collection = database.getCollection("Tasks");
        List<Tasks> list = new ArrayList<>();
        FindIterable<Document> iterDoc = collection.find();
        for (Document document : iterDoc) {
            String title = document.getString("title");
            int priority = document.getInteger("priority");
            done = document.getBoolean("done");
            Date date = document.getDate("date");
            ObjectId id = document.getObjectId("_id");
            Tasks tasks;
            if (done==true) {
                if (document.containsKey("price")) {
                    double price = document.getDouble("price");
                    tasks = new Tasks(title, date, priority, (int) price, done);
                } else {
                    tasks = new Tasks(title, priority, done, date);
                }
                tasks.setId(id);
                list.add(tasks);
            }else
            if (done==false) {
                if (document.containsKey("price")) {
                    double price = document.getDouble("price");
                    tasks = new Tasks(title, date, priority, (int) price, done);
                } else {
                    tasks = new Tasks(title, priority, done, date);
                }
                tasks.setId(id);
                list.add(tasks);
            }
        }
        return list;
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public List<Tasks> getAllTasksByPriority(int prior) {
        if(prior<=0 || prior>3)
            return null;
        database = mongoClient.getDatabase("TaskDB");
        collection = database.getCollection("Tasks");
        List<Tasks> list = new ArrayList<>();
        FindIterable<Document> iterDoc = collection.find();
        for (Document document : iterDoc) {
            String title = document.getString("title");
            int priority = document.getInteger("priority");
            boolean done = document.getBoolean("done");
            Date date = document.getDate("date");
            ObjectId id = document.getObjectId("_id");
            Tasks tasks;
            if (prior==priority) {
                if (document.containsKey("price")) {
                    double price = document.getDouble("price");
                    tasks = new Tasks(title, date, priority, (int) price, done);
                } else {
                    tasks = new Tasks(title, priority, done, date);
                }
                tasks.setId(id);
                list.add(tasks);
            }
        }
        return list;
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public List<Tasks> getAllTasksByName(String name) {
        database = mongoClient.getDatabase("TaskDB");
        collection = database.getCollection("Tasks");
        List<Tasks> list = new ArrayList<>();
        FindIterable<Document> iterDoc = collection.find();
        for (Document document : iterDoc) {
            String title = document.getString("title");
            int priority = document.getInteger("priority");
            boolean done = document.getBoolean("done");
            Date date = document.getDate("date");
            ObjectId id = document.getObjectId("_id");
            Tasks tasks;
            if (name==title) {
                if (document.containsKey("price")) {
                    double price = document.getDouble("price");
                    tasks = new Tasks(title, date, priority, (int) price, done);
                } else {
                    tasks = new Tasks(title, priority, done, date);
                }
                tasks.setId(id);
                list.add(tasks);
            }else {
                return null;
            }
        }
        return list;
    }

    @Override
    public void DeleteDoneTasks() {
        collection.deleteMany(new Document().append("done", true));
    }

    @Override
    public void insertTaskJSON(JSONObject task) {
        collection=database.getCollection("Tasks");
        JSONObject object = new JSONObject();
        object.put("task",task);
        docs=Document.parse(object.toJSONString());
        collection.insertOne(docs);
    }

    @Override
    public JSONObject getAllTasksJSON() {
        for(Document doc : database.getCollection("Tasks").find()){
            try {
                JSONObject object = (JSONObject) new JSONParser().parse(doc.toJson());
                String date = (String) object.get("date");
                String title = (String) object.get("title");
                String task = (String) object.get("task");
                int priority = Integer.parseInt(String.valueOf(object.get("priority")));
                double price = (double) object.get("price");
                boolean done = (boolean) object.get("done");
                object.put(new Tasks(title,date,price,priority,done));
            }catch ( ParseException e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
