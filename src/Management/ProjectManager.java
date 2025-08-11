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
        project.addTask(task);
        task.setProject(project);
    }

    public void removeTaskFromProject(Project project, Task task){
        project.removeTask(task);
        task.setProject(null);
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
