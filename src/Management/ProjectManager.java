package Management;

import Model.Project;
import Model.Task;
import Model.IUser;
import Storage.IStorage;

import javax.xml.namespace.QName;
import java.util.List;

public class ProjectManager{
    private IStorage storage;

    public ProjectManager(IStorage storage) {
        this.storage = storage;
    }

    public void createProject(String title){
        Project newProject = new Project(title);
        this.storage.add(newProject);
    }

    public void editProject(Project project, String newTitle){
        project.setTitle(newTitle);
    }

    public void assignLeaderToProject(Project project, IUser leader){
        project.setUser(leader);
    }

    public void removeLeaderToProject(Project project){
        project.setUser(null);
    }

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

    public void removeTaskFromProject(Project project, Task task){
        if(project.getTasks().contains(task)){
            project.removeTask(task);
            task.setProject(null);
            System.out.println("Se elimino la tarea satisfactorimanete del proyecto: " + project.getTitle());
        } else{
            System.out.println("No se encontro la tarea en el proyecto, intentalo de nuevo");
        }
    }

    public void deleteProject(Project project){
        this.storage.delete(project);
    }

    public List<Project> getAllProjects(){
        return this.storage.getAll();
    }

    public Project getProjectById(int id){
        return (Project) this.storage.getById(id);
    }
}
