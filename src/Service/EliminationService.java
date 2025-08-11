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

    public EliminationService(TaskManager taskManager, ProjectManager projectManager, UserManager userManager) {
        this.taskManager = taskManager;
        this.projectManager = projectManager;
        this.userManager = userManager;
    }

    // Método para eliminar un usuario de forma segura
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

    // Método para eliminar una tarea de forma segura
    public void removeTask(Task task) {
        // Si la tarea está en un proyecto, eliminarla de la lista de tareas de ese proyecto
        if (task.getProject() != null) {
            task.getProject().removeTask(task);
        }

        // Finalmente, eliminar la tarea del almacenamiento a través del TaskManager
        taskManager.deleteTask(task);
    }

    // Método para eliminar un proyecto de forma segura
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
