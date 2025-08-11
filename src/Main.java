import Management.UserManager;
import Model.Estado;
import Model.Task;
import Management.TaskManager;
import Model.User;
import Storage.TaskStorage;
import Storage.UserStorage;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TaskStorage taskStorage = new TaskStorage();
        TaskManager taskManager = new TaskManager(taskStorage);
        UserStorage userStorage = new UserStorage();
        UserManager userManager = new UserManager(userStorage);

        taskManager.createTask("Tarea 1", Estado.PENDIENTE, "Esto es una descripcion");
        taskManager.createTask("Tarea 2", Estado.CANCELADO, "Soy la tarea 2");
        taskManager.createTask("Tarea 3", Estado.PENDIENTE, "Soy la tarea 3");
        taskManager.changeTaskStatus(taskManager.getTaskByName("Tarea 1"), Estado.COMPLETADO);
        displayTasks(taskManager.getAllTasks());
        userManager.createUser("Manuel Araujo", 40, "Dev Senior");
        userManager.createUser("Pedro Lopez", 25, "Dev Junior");
        userManager.createUser("Kevin Jimenez", 40, "QA");
        taskManager.assignUserTask(userManager.getUserByName("Manuel Araujo"), taskManager.getTaskByName("Tarea 1"));
        taskManager.assignUserTask(userManager.getUserByName("Pedro Lopez"), taskManager.getTaskByName("Tarea 2"));
        displayTasks(taskManager.getAllTasks());
        displayUsers(userManager.getAllUsers());
    }

    // Nuevo método para mostrar la lista de tareas
    public static void displayTasks(List<Task> tasks) {
        System.out.println("--- Listado de Tareas ---");
        if (tasks.isEmpty()) {
            System.out.println("No hay tareas registradas.");
        } else {
            for (Task task : tasks) {
                System.out.println("-------------------------");
                System.out.println("Nombre: " + task.getName());
                System.out.println("Estado: " + task.getEstado());
                System.out.println("Descripción: " + task.getDescription());
                if (task.getUser() != null) {
                    System.out.println("Asignada a: " + task.getUser().getName());
                } else {
                    System.out.println("Asignada a: (sin usuario)");
                }
                if (task.getProject() != null) {
                    System.out.println("Proyecto: " + task.getProject().getTitle());
                } else {
                    System.out.println("Proyecto: (sin asignación de proyecto)");
                }
                System.out.println("-------------------------");
            }
        }
    }

    // Nuevo método para mostrar la lista de usuarios
    public static void displayUsers(List<User> users) {
        System.out.println("--- Listado de Usuarios ---");
        if (users.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            for (User user : users) {
                System.out.println("-------------------------");
                System.out.println("Nombre: " + user.getName());
                System.out.println("Edad: " + user.getAge());
                System.out.println("Puesto: " + user.getJobTitle());
                System.out.println("-------------------------");
            }
        }
    }
}