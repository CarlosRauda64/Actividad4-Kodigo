import Data.Estado;
import Data.Task;
import Management.TaskManager;
import Storage.TaskStorage;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TaskStorage taskStorage = new TaskStorage();
        TaskManager taskManager = new TaskManager(taskStorage);

        taskManager.createTask("Tarea 1", Estado.PENDIENTE, "Esto es una descripcion");
        taskManager.createTask("Tarea 2", Estado.CANCELADO, "Soy la tarea 2");
        displayTasks(taskManager.getAllTasks());
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
}