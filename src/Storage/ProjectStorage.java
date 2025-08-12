/**
 * Implementación de la interfaz IStorage para la persistencia de objetos de tipo Project.
 * Utiliza una lista en memoria para almacenar y gestionar los proyectos.
 *
 * Esta clase cumple con el Principio de Responsabilidad Única (SRP) al
 * tener como única función la gestión del almacenamiento de proyectos.
 * La implementación de la interfaz genérica {@link IStorage} es un claro ejemplo
 * del Principio de Inversión de Dependencias (DIP) y del Principio Abierto/Cerrado (OCP).
 */
package Storage;

import Model.Project;
import java.util.ArrayList;
import java.util.List;

public class ProjectStorage implements IStorage<Project>{
    private List<Project> projects = new ArrayList<>();
    private int nextId = 1;

    @Override
    public void add(Project project){
        project.setId(nextId);
        this.nextId++;
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
    public Project getById(int id){
        for(Project project : projects){
            if(project.getId() == id){
                return project;
            }
        }
        return null;
    }
}
