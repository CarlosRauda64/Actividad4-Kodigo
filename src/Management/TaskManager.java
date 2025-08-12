package Management;

import Model.Estado;
import Model.IUser;
import Model.Task;
import Storage.IStorage;

import java.util.List;

public class TaskManager {

    private IStorage storage;

    /**
     * Constructor para inicializar un objeto de tipo TaskManager.
     * @param storage La instancia de almacenamiento de tareas (IStorage).
     */
    public TaskManager(IStorage storage) {
        this.storage = storage;
    }

    /**
     * Crea una nueva tarea y la añade al almacenamiento.
     * @param name El nombre de la nueva tarea.
     * @param estado El estado inicial de la tarea.
     * @param description La descripción de la tarea.
     */
    public void createTask(String name, Estado estado, String description){
        Task newTask = new Task(name, estado, description);
        this.storage.add(newTask);
    }

    /**
     * Edita una tarea existente con nueva información.
     * @param task La tarea a editar.
     * @param newName El nuevo nombre para la tarea.
     * @param newDescrition La nueva descripción para la tarea.
     */
    public void editTask(Task task, String newName, String newDescrition){
        task.setName(newName);
        task.setDescription(newDescrition);
    }

    /**
     * Elimina una tarea del almacenamiento.
     * @param task La tarea a eliminar.
     */
    public void deleteTask(Task task){
        this.storage.delete(task);
    }

    /**
     * Retorna la lista completa de todas las tareas almacenadas.
     * @return Una lista de todas las tareas.
     */
    public List<Task> getAllTasks(){
        return this.storage.getAll();
    }

    /**
     * Obtiene una tarea específica por su ID único.
     * @param id El ID de la tarea a buscar.
     * @return La tarea encontrada, o null si no existe.
     */
    public Task getTaskById(int id){
        return (Task) this.storage.getById(id);
    }

    /**
     * Asigna un usuario a una tarea. Si la tarea ya tiene un usuario asignado,
     * se retorna un mensaje de error.
     * @param user El usuario que se asignará a la tarea.
     * @param task La tarea a la que se asignará el usuario.
     * @return Un mensaje de confirmación o error.
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
     * Desasigna a un usuario de una tarea.
     * @param task La tarea a la que se le desasignará el usuario.
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

    /**
     * Cambia el estado de una tarea.
     * @param task La tarea cuyo estado se va a cambiar.
     * @param newStatus El nuevo estado para la tarea.
     */
    public void changeTaskStatus(Task task, Estado newStatus){
        task.setEstado(newStatus);
    }


}
