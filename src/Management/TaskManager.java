package Management;

import Data.Estado;
import Data.IUser;
import Data.Task;
import Storage.IStorage;

import java.util.List;

public class TaskManager {

    private IStorage storage;

    public TaskManager(IStorage storage) {
        this.storage = storage;
    }

    public void createTask(String name, Estado estado, String description){
        Task newTask = new Task(name, estado, description);
        this.storage.add(newTask);
    }

    public List<Task> getAllTasks(){
        return this.storage.getAll();
    }

    public String assignUserTask(IUser user, Task task){
        if(task.getUser() == null){
            task.setUser(user);
            return "La tarea " + task.getName() +
                    "\n se asigno al usuario: " + user.getName();
        } else {
            return "La tarea " + task.getName() +
                    "\n ya tiene un usuario asignado: " + task.getUser().getName();
        }
    }
}
