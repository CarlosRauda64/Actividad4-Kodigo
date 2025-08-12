/**
 * Implementación de la interfaz IStorage para la persistencia de objetos de tipo Task.
 * Utiliza una lista en memoria para almacenar y gestionar las tareas.
 *
 * Esta clase cumple con el Principio de Responsabilidad Única (SRP) al
 * tener como única función la gestión del almacenamiento de tareas.
 * Su implementación de la interfaz {@link IStorage} refuerza el Principio de Inversión
 * de Dependencias (DIP), separando la lógica de negocio de la capa de persistencia.
 */
package Storage;

import Model.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskStorage implements IStorage<Task> {
    private List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    @Override
    public void add(Task task){
        task.setId(nextId);
        this.nextId++;
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
    public Task getById(int id){
        for (Task task : tasks){
            if(task.getId() == id){
                return task;
            }
        }
        return null;
    }
}
