package sk.kosickaakademia.sk.scratchpad.database;

import sk.kosickaakademia.sk.scratchpad.util.Tasks;
import java.util.List;


public class Database implements Mongo {
//dbname: TaskDB
//collection: Tasks
    @Override
    public void insertTask(Tasks task) {

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
