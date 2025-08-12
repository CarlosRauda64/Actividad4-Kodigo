package Management;

import Model.Project;
import Model.Task;
import Model.IUser;
import Storage.IStorage;

import javax.xml.namespace.QName;
import java.util.List;

public class ProjectManager{
    private IStorage storage;

    /**
     * Constructor para inicializar un objeto de tipo ProjectManager.
     * @param storage La instancia de almacenamiento de proyectos (IStorage).
     */
    public ProjectManager(IStorage storage) {
        this.storage = storage;
    }

    /**
     * Crea un nuevo proyecto y lo añade al almacenamiento.
     * @param title El título del nuevo proyecto.
     */
    public void createProject(String title){
        Project newProject = new Project(title);
        this.storage.add(newProject);
    }

    /**
     * Edita un proyecto existente con un nuevo título.
     * @param project El proyecto a editar.
     * @param newTitle El nuevo título para el proyecto.
     */
    public void editProject(Project project, String newTitle){
        project.setTitle(newTitle);
    }

    /**
     * Asigna un líder a un proyecto.
     * @param project El proyecto al que se asignará el líder.
     * @param leader El usuario que será el líder.
     */
    public void assignLeaderToProject(Project project, IUser leader){
        project.setUser(leader);
    }

    /**
     * Remueve al líder de un proyecto.
     * @param project El proyecto del que se removerá al líder.
     */
    public void removeLeaderToProject(Project project){
        project.setUser(null);
    }

    /**
     * Añade una tarea a un proyecto. La tarea no puede estar ya asignada a otro proyecto.
     * @param project El proyecto al que se añadirá la tarea.
     * @param task La tarea a añadir.
     */
    public void addTaskToProject(Project project, Task task){
        if(task.getProject() == null){
            project.addTask(task);
            task.setProject(project);
        } else if(!project.getTasks().contains(task)){
            System.out.println("Error: La tarea ya está asignada al proyecto '" + task.getProject().getTitle() + "'.");
        } else {
            System.out.println("La tarea ya se encuentra en este proyecto.");
        }
    }

    /**
     * Remueve una tarea de un proyecto.
     * @param project El proyecto del que se removerá la tarea.
     * @param task La tarea a remover.
     */
    public void removeTaskFromProject(Project project, Task task){
        if(project.getTasks().contains(task)){
            project.removeTask(task);
            task.setProject(null);
            System.out.println("Se elimino la tarea satisfactorimanete del proyecto: " + project.getTitle());
        } else{
            System.out.println("No se encontro la tarea en el proyecto, intentalo de nuevo");
        }
    }

    /**
     * Elimina un proyecto del almacenamiento.
     * @param project El proyecto a eliminar.
     */
    public void deleteProject(Project project){
        this.storage.delete(project);
    }

    /**
     * Retorna la lista completa de todos los proyectos.
     * @return Una lista de todos los proyectos.
     */
    public List<Project> getAllProjects(){
        return this.storage.getAll();
    }

    /**
     * Obtiene un proyecto específico por su ID.
     * @param id El ID del proyecto a buscar.
     * @return El proyecto encontrado, o null si no existe.
     */
    public Project getProjectById(int id){
        return (Project) this.storage.getById(id);
    }
}
