package Storage;

import Model.Project;
import java.util.ArrayList;
import java.util.List;

public class ProjectStorage implements IStorage<Project>{
    private List<Project> projects = new ArrayList<>();

    @Override
    public void add(Project project){
        projects.add(project);
    }

    @Override
    public List<Project> getAll(){
        return projects;
    }

    @Override
    public void delete(Project project){
        projects.remove(project);
    }

    @Override
    public Project getByName(String title){
        for(Project project : projects){
            if(project.getTitle().equals(title)){
                return project;
            }
        }
        return null;
    }
}
