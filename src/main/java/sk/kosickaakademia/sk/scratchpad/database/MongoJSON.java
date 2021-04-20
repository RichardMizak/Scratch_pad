package sk.kosickaakademia.sk.scratchpad.database;

import org.json.simple.JSONObject;
import sk.kosickaakademia.sk.scratchpad.util.Tasks;

import java.util.List;

public interface MongoJSON {
    public void insertTaskJSON(JSONObject task);

    public JSONObject getAllTasksJSON();
}
