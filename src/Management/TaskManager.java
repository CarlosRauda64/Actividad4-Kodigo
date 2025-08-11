package Management;

import Model.Estado;
import Model.IUser;
import Model.Task;
import Storage.IStorage;

import java.util.List;

public class TaskManager {

    private IStorage storage;

    /**
     * Constructor que sirve para inicializar un
     * objeto de tipo TaskManager
     * @param storage
     */
    public TaskManager(IStorage storage) {
        this.storage = storage;
    }

    /**
     * Sirve para crear una nueva tarea
     * @param name
     * @param estado
     * @param description
     */
    public void createTask(String name, Estado estado, String description){
        Task newTask = new Task(name, estado, description);
        this.storage.add(newTask);
    }

    /**
     * Metodo que sirve para editar una tarea
     * @param task
     * @param newName
     * @param newDescrition
     */
    public void editTask(Task task, String newName, String newDescrition){
        task.setName(newName);
        task.setDescription(newDescrition);
    }

    public void deleteTask(Task task){
        this.storage.delete(task);
    }

    /**
     * Se encarga de retornar la lista de tareas.
     * @return la lista de tareas guardadas
     */
    public List<Task> getAllTasks(){
        return this.storage.getAll();
    }

    /**
     * Sirve para obtener una tarea por su nombre
     * @param id
     * @return Task
     */
    public Task getTaskById(int id){
        return (Task) this.storage.getById(id);
    }

    /**
     * Metodo que sirve para asignar un usuario a una tarea.
     * @param user
     * @param task
     * @return Mensaje de confirmación
     */
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

    /**
     * Sirve para desasignar un usuario
     * @param task
     * @return Mensaje
     */
    public void unassignUserTask(Task task){
        if(task.getUser() != null){
            String userName = task.getUser().getName();
            task.setUser(null);
            System.out.println("El usuario " + userName + " fue desasignado de la tarea" +
                    task.getName());
        } else{
            System.out.println("La tarea" + task.getName() + "no tiene un usuario asignado");
        }
    }

    public void changeTaskStatus(Task task, Estado newStatus){
        task.setEstado(newStatus);
    }


}
