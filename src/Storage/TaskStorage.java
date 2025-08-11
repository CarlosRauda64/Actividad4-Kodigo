package Storage;

import Model.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskStorage implements IStorage<Task> {
    private List<Task> tasks = new ArrayList<>();

    @Override
    public void add(Task task){
        tasks.add(task);
    }

    @Override
    public List<Task> getAll() {
        return tasks;
    }

    @Override
    public void delete(Task task){
        tasks.remove(task);
    }

    @Override
    public Task getByName(String name){
        for (Task task : tasks){
            if(task.getName().equals(name)){
                return task;
            }
        }
        return null;
    }
}
