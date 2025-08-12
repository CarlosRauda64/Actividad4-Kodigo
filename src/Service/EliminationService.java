package Service;

import Management.ProjectManager;
import Management.TaskManager;
import Management.UserManager;
import Model.Project;
import Model.Task;
import Model.User;

import java.util.ArrayList;
import java.util.List;

public class EliminationService {
    private final TaskManager taskManager;
    private final ProjectManager projectManager;
    private final UserManager userManager;

    /**
     * Constructor para inicializar el servicio de eliminación.
     * @param taskManager El gestor de tareas.
     * @param projectManager El gestor de proyectos.
     * @param userManager El gestor de usuarios.
     */
    public EliminationService(TaskManager taskManager, ProjectManager projectManager, UserManager userManager) {
        this.taskManager = taskManager;
        this.projectManager = projectManager;
        this.userManager = userManager;
    }

    /**
     * Elimina un usuario de forma segura y en cascada.
     * Esta acción desasigna al usuario de todas las tareas y proyectos antes de su eliminación final.
     * @param user El usuario que se va a eliminar.
     */
    public void removeUser(User user) {
        // Desasignar al usuario de todas las tareas
        for (Task task : taskManager.getAllTasks()) {
            if (user.equals(task.getUser())) {
                task.setUser(null);
            }
        }

        // Desasignar al usuario como líder de todos los proyectos
        for (Project project : projectManager.getAllProjects()) {
            if (user.equals(project.getUser())) {
                project.setUser(null);
            }
        }

        // Finalmente, eliminar el usuario del almacenamiento a través del UserManager
        userManager.deleteUser(user);
    }

    /**
     * Elimina una tarea de forma segura y en cascada.
     * Si la tarea está asignada a un proyecto, la remueve de él antes de su eliminación final.
     * @param task La tarea que se va a eliminar.
     */
    public void removeTask(Task task) {
        // Si la tarea está en un proyecto, eliminarla de la lista de tareas de ese proyecto
        if (task.getProject() != null) {
            task.getProject().removeTask(task);
        }

        // Finalmente, eliminar la tarea del almacenamiento a través del TaskManager
        taskManager.deleteTask(task);
    }

    /**
     * Elimina un proyecto de forma segura y en cascada.
     * Esta acción desasigna todas las tareas asociadas al proyecto antes de su eliminación final.
     * @param project El proyecto que se va a eliminar.
     */
    public void removeProject(Project project) {
        // Establecer a null la referencia del proyecto en todas las tareas
        List<Task> tasksInProject = new ArrayList<>(project.getTasks());
        for (Task task : tasksInProject) {
            task.setProject(null);
        }

        // Finalmente, eliminar el proyecto del almacenamiento a través del ProjectManager
        projectManager.deleteProject(project);
    }
}
