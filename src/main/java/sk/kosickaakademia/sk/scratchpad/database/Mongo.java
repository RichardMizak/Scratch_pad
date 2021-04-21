package sk.kosickaakademia.sk.scratchpad.database;

import sk.kosickaakademia.sk.scratchpad.util.Tasks;

import java.util.List;

public interface Mongo {
    /***
     * description
     * @param task
     * @author Richard
     * @version 1.0
     */

    public boolean insertTask(String title, String task, int priority, double price);

    public boolean setTaskToDone();

    public List<Tasks> getAllTasks();

    public List<Tasks> getAllTasks(boolean done);

    public List<Tasks> getAllTasksByPriority(int prior);

    public List<Tasks> getAllTasksByName(String name);

    public void DeleteDoneTasks();

}
