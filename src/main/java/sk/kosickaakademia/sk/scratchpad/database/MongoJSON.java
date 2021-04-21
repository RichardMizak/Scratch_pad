package sk.kosickaakademia.sk.scratchpad.database;

import org.json.simple.JSONObject;
import sk.kosickaakademia.sk.scratchpad.util.Tasks;

import java.util.List;

public interface MongoJSON {
    public boolean insertTaskJSON(JSONObject task);

    public JSONObject getAllTasksJSON();


    public JSONObject getAllTasksByPriorityJSON();

    public JSONObject getAllTasksByNameJSON();
}
