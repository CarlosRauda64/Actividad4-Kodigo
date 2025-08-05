package Storage;
import Data.Task;
import Storage.IStorage;

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
}
