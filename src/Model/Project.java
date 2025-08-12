/**
 * Clase que representa la entidad "Proyecto".
 * Incluye un ID único, un título, la referencia al usuario líder y una lista
 * de las tareas asignadas a este proyecto.
 *
 * Esta clase cumple con el Principio de Responsabilidad Única (SRP) al ser
 * responsable solo de modelar los datos de un proyecto y la relación con
 * sus tareas. La lógica de negocio para gestionar proyectos se encuentra en
 * la clase {@link Management.ProjectManager}.
 */
package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Project{
    private int id;
    private String title;
    private IUser user;
    private List<Task> tasks;

    public Project(String title) {
        this.title = title;
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks(){
        return Collections.unmodifiableList(tasks);
    }

    public void addTask(Task task){
        if(task != null && !tasks.contains(task)){
            this.tasks.add(task);
        }
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTask(Task task){
        this.tasks.add(task);
    }

    public IUser getUser() {
        return user;
    }

    public void setUser(IUser user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
