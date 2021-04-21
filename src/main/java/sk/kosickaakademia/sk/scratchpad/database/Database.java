package sk.kosickaakademia.sk.scratchpad.database;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sk.kosickaakademia.sk.scratchpad.util.Tasks;

import java.util.*;


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
        database = mongoClient.getDatabase("TaskDB");
        collection=database.getCollection("Tasks");
        collection.deleteMany(new Document().append("done", true));
    }

    @Override
    public boolean insertTaskJSON(JSONObject task) {
        database = mongoClient.getDatabase("TaskDB");
        collection=database.getCollection("Tasks");
        if (task.isEmpty() || task == null)
            return false;
        docs = new Document();
        docs.append("date", date.toString());
        docs.append("title", task.get("title"));
        docs.append("task", task.get("task"));
        docs.append("priority", task.get("priority"));
        docs.append("price", task.get("price"));
        docs.append("done", false);
        return true;
    }

    @Override
    public JSONObject getAllTasksJSON() {
        database = mongoClient.getDatabase("TaskDB");
        JSONArray array = new JSONArray();
        JSONObject json = new JSONObject();
        for(Document docs : database.getCollection("Tasks").find()){
            try {
                JSONObject object = (JSONObject) new JSONParser().parse(docs.toJson());
                array.add(object);
            }catch (ParseException e){
                e.printStackTrace();
            }
        }
        json.put("Tasks", array);
        return json;
    }

    @Override
    public JSONObject getAllTasksByPriorityJSON() {
        database = mongoClient.getDatabase("TaskDB");
        JSONArray array = new JSONArray();
        JSONObject json = new JSONObject();
        for(Document docs : database.getCollection("Tasks").find()){
            try {
                JSONObject object = (JSONObject) new JSONParser().parse(docs.toJson());
                array.add(object);
            }catch (ParseException e){
                e.printStackTrace();
            }
        }
        json.put("Tasks", array);
        return json;
    }

    @Override
    public JSONObject getAllTasksByNameJSON() {
        database = mongoClient.getDatabase("TaskDB");
        JSONArray array = new JSONArray();
        JSONObject json = new JSONObject();
        for(Document docs : database.getCollection("Tasks").find()){
            try {
                JSONObject object = (JSONObject) new JSONParser().parse(docs.toJson());
                array.add(object);
            }catch (ParseException e){
                e.printStackTrace();
            }
        }
        json.put("Tasks", array);
        return json;
    }
}
