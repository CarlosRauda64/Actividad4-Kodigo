import Management.ProjectManager;
import Management.TaskManager;
import Management.UserManager;
import Model.Estado;
import Model.IUser;
import Model.Project;
import Model.Task;
import Model.User;
import Storage.IStorage;
import Storage.ProjectStorage;
import Storage.TaskStorage;
import Storage.UserStorage;
import Service.EliminationService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final IStorage<Task> taskStorage = new TaskStorage();
    private static final IStorage<User> userStorage = new UserStorage();
    private static final IStorage<Project> projectStorage = new ProjectStorage();
    private static final TaskManager taskManager = new TaskManager(taskStorage);
    private static final UserManager userManager = new UserManager(userStorage);
    private static final ProjectManager projectManager = new ProjectManager(projectStorage);
    private static final EliminationService eliminationService = new EliminationService(taskManager, projectManager, userManager);

    public static void main(String[] args) {
        // Datos de prueba iniciales
        initializeData();

        int choice;
        do {
            displayMainMenu();
            try {
                choice = Integer.parseInt(scanner.nextLine());
                handleMainMenuChoice(choice);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                choice = -1; // Mantener el bucle
            }
        } while (choice != 0);

        scanner.close();
        System.out.println("Saliendo de la aplicación. ¡Hasta pronto!");
    }

    private static void initializeData() {
        userManager.createUser("Manuel Araujo", 40, "Desarrollador Senior");
        userManager.createUser("Pedro Lopez", 25, "Desarrollador Junior");
        userManager.createUser("Kevin Jimenez", 40, "QA");

        taskManager.createTask("Configurar base de datos", Estado.PENDIENTE, "Instalar y configurar PostgreSQL");
        taskManager.createTask("Diseñar UI/UX", Estado.EN_PROGRESO, "Crear mockups para la interfaz de usuario");
        taskManager.createTask("Crear documentacion", Estado.PENDIENTE, "Documentar la arquitectura del sistema");

        projectManager.createProject("Proyecto A");
        projectManager.createProject("Proyecto B");

        // Asignaciones iniciales
        User user1 = userManager.getUserByName("Manuel Araujo");
        Task task1 = taskManager.getTaskByName("Configurar base de datos");
        Project project1 = projectManager.getProjectByTitle("Proyecto A");
        if (user1 != null && task1 != null && project1 != null) {
            taskManager.assignUserTask(user1, task1);
            projectManager.assignLeaderToProject(project1, user1);
            projectManager.addTaskToProject(project1, task1);
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Gestionar Tareas");
        System.out.println("2. Gestionar Usuarios");
        System.out.println("3. Gestionar Proyectos");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void handleMainMenuChoice(int choice) {
        switch (choice) {
            case 1:
                manageTasksMenu();
                break;
            case 2:
                manageUsersMenu();
                break;
            case 3:
                manageProjectsMenu();
                break;
            case 0:
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void manageTasksMenu() {
        int choice;
        do {
            System.out.println("\n--- Menú de Tareas ---");
            System.out.println("1. Crear Tarea");
            System.out.println("2. Ver todas las Tareas");
            System.out.println("3. Asignar Usuario a Tarea");
            System.out.println("4. Cambiar estado de Tarea");
            System.out.println("5. Editar Tarea");
            System.out.println("6. Eliminar Tarea");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                handleTaskMenuChoice(choice);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida.");
                choice = -1;
            }
        } while (choice != 0);
    }

    private static void handleTaskMenuChoice(int choice) {
        String taskName;
        Task task;
        String userName;
        User user;

        switch (choice) {
            case 1:
                System.out.print("Nombre de la nueva tarea: ");
                String name = scanner.nextLine();
                System.out.print("Descripción de la tarea: ");
                String description = scanner.nextLine();
                taskManager.createTask(name, Estado.PENDIENTE, description);
                System.out.println("Tarea '" + name + "' creada con éxito.");
                break;
            case 2:
                displayTasks(taskManager.getAllTasks());
                break;
            case 3:
                System.out.print("Nombre de la tarea a asignar: ");
                taskName = scanner.nextLine();
                task = taskManager.getTaskByName(taskName);
                if (task == null) {
                    System.out.println("Tarea no encontrada.");
                    break;
                }
                System.out.print("Nombre del usuario a asignar: ");
                userName = scanner.nextLine();
                user = userManager.getUserByName(userName);
                if (user == null) {
                    System.out.println("Usuario no encontrado.");
                    break;
                }
                System.out.println(taskManager.assignUserTask(user, task));
                break;
            case 4:
                System.out.print("Nombre de la tarea para cambiar estado: ");
                taskName = scanner.nextLine();
                task = taskManager.getTaskByName(taskName);
                if (task == null) {
                    System.out.println("Tarea no encontrada.");
                    break;
                }
                System.out.println("Estados disponibles: PENDIENTE, EN_PROGRESO, COMPLETADO, CANCELADO");
                System.out.print("Nuevo estado: ");
                try {
                    Estado newStatus = Estado.valueOf(scanner.nextLine().toUpperCase());
                    taskManager.changeTaskStatus(task, newStatus);
                    System.out.println("Estado de la tarea '" + taskName + "' actualizado a " + newStatus);
                } catch (IllegalArgumentException e) {
                    System.out.println("Estado no válido.");
                }
                break;
            case 5:
                System.out.print("Nombre de la tarea a editar: ");
                taskName = scanner.nextLine();
                task = taskManager.getTaskByName(taskName);
                if (task == null) {
                    System.out.println("Tarea no encontrada.");
                    break;
                }
                System.out.print("Nuevo nombre (dejar en blanco para no cambiar): ");
                String newName = scanner.nextLine();
                System.out.print("Nueva descripción (dejar en blanco para no cambiar): ");
                String newDescription = scanner.nextLine();
                if (!newName.isBlank() || !newDescription.isBlank()) {
                    taskManager.editTask(task, newName.isBlank() ? task.getName() : newName, newDescription.isBlank() ? task.getDescription() : newDescription);
                    System.out.println("Tarea editada con éxito.");
                } else {
                    System.out.println("No se realizaron cambios.");
                }
                break;
            case 6:
                System.out.print("Nombre de la tarea a eliminar: ");
                taskName = scanner.nextLine();
                task = taskManager.getTaskByName(taskName);
                if (task == null) {
                    System.out.println("Tarea no encontrada.");
                    break;
                }
                eliminationService.removeTask(task);
                System.out.println("Tarea '" + taskName + "' eliminada con éxito.");
                break;
            case 0:
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    // Métodos para el menú de Usuarios y Proyectos
    private static void manageUsersMenu() {
        // Implementar la lógica del menú para usuarios (similar a tareas)
        // Opciones: Crear Usuario, Ver Usuarios, Editar Usuario, Eliminar Usuario
        int choice;
        do {
            System.out.println("\n--- Menú de Usuarios ---");
            System.out.println("1. Crear Usuario");
            System.out.println("2. Ver todos los Usuarios");
            System.out.println("3. Editar Usuario");
            System.out.println("4. Eliminar Usuario");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                handleUserMenuChoice(choice);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida.");
                choice = -1;
            }
        } while (choice != 0);
    }

    private static void handleUserMenuChoice(int choice) {
        String userName;
        User user;

        switch (choice) {
            case 1:
                System.out.print("Nombre del nuevo usuario: ");
                String name = scanner.nextLine();
                System.out.print("Puesto: ");
                String jobTitle = scanner.nextLine();
                System.out.print("Edad: ");
                int age = Integer.parseInt(scanner.nextLine());
                userManager.createUser(name, age, jobTitle);
                System.out.println("Usuario '" + name + "' creado con éxito.");
                break;
            case 2:
                displayUsers(userManager.getAllUsers());
                break;
            case 3:
                System.out.print("Nombre del usuario a editar: ");
                userName = scanner.nextLine();
                user = userManager.getUserByName(userName);
                if (user == null) {
                    System.out.println("Usuario no encontrado.");
                    break;
                }
                System.out.print("Nuevo nombre (dejar en blanco para no cambiar): ");
                String newName = scanner.nextLine();
                System.out.print("Nuevo puesto (dejar en blanco para no cambiar): ");
                String newJobTitle = scanner.nextLine();
                System.out.print("Nueva edad (dejar en blanco para no cambiar): ");
                String newAgeStr = scanner.nextLine();
                int newAge = newAgeStr.isBlank() ? user.getAge() : Integer.parseInt(newAgeStr);

                userManager.editUser(user, newName.isBlank() ? user.getName() : newName, newJobTitle.isBlank() ? user.getJobTitle() : newJobTitle, newAge);
                System.out.println("Usuario editado con éxito.");
                break;
            case 4:
                System.out.print("Nombre del usuario a eliminar: ");
                userName = scanner.nextLine();
                user = userManager.getUserByName(userName);
                if (user == null) {
                    System.out.println("Usuario no encontrado.");
                    break;
                }
                eliminationService.removeUser(user);
                System.out.println("Usuario '" + userName + "' eliminado con éxito.");
                break;
            case 0:
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void manageProjectsMenu() {
        // Implementar la lógica del menú para proyectos
        // Opciones: Crear Proyecto, Ver Proyectos, Asignar Líder, Añadir Tarea, Eliminar Proyecto
        int choice;
        do {
            System.out.println("\n--- Menú de Proyectos ---");
            System.out.println("1. Crear Proyecto");
            System.out.println("2. Ver todos los Proyectos");
            System.out.println("3. Asignar Líder a Proyecto");
            System.out.println("4. Añadir Tarea a Proyecto");
            System.out.println("5. Eliminar Proyecto");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                handleProjectMenuChoice(choice);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida.");
                choice = -1;
            }
        } while (choice != 0);
    }

    private static void handleProjectMenuChoice(int choice) {
        String projectTitle;
        Project project;
        String userName;
        User user;
        String taskName;
        Task task;

        switch (choice) {
            case 1:
                System.out.print("Título del nuevo proyecto: ");
                String title = scanner.nextLine();
                projectManager.createProject(title);
                System.out.println("Proyecto '" + title + "' creado con éxito.");
                break;
            case 2:
                displayProjects(projectManager.getAllProjects());
                break;
            case 3:
                System.out.print("Título del proyecto: ");
                projectTitle = scanner.nextLine();
                project = projectManager.getProjectByTitle(projectTitle);
                if (project == null) {
                    System.out.println("Proyecto no encontrado.");
                    break;
                }
                System.out.print("Nombre del líder a asignar: ");
                userName = scanner.nextLine();
                user = userManager.getUserByName(userName);
                if (user == null) {
                    System.out.println("Usuario no encontrado.");
                    break;
                }
                projectManager.assignLeaderToProject(project, user);
                System.out.println("Líder asignado al proyecto '" + projectTitle + "' con éxito.");
                break;
            case 4:
                System.out.print("Título del proyecto para añadir tarea: ");
                projectTitle = scanner.nextLine();
                project = projectManager.getProjectByTitle(projectTitle);
                if (project == null) {
                    System.out.println("Proyecto no encontrado.");
                    break;
                }
                System.out.print("Nombre de la tarea a añadir: ");
                taskName = scanner.nextLine();
                task = taskManager.getTaskByName(taskName);
                if (task == null) {
                    System.out.println("Tarea no encontrada.");
                    break;
                }
                projectManager.addTaskToProject(project, task);
                System.out.println("Tarea '" + taskName + "' añadida al proyecto '" + projectTitle + "' con éxito.");
                break;
            case 5:
                System.out.print("Título del proyecto a eliminar: ");
                projectTitle = scanner.nextLine();
                project = projectManager.getProjectByTitle(projectTitle);
                if (project == null) {
                    System.out.println("Proyecto no encontrado.");
                    break;
                }
                eliminationService.removeProject(project);
                System.out.println("Proyecto '" + projectTitle + "' eliminado con éxito.");
                break;
            case 0:
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void displayTasks(List<Task> tasks) {
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

    private static void displayUsers(List<User> users) {
        System.out.println("--- Listado de Usuarios ---");
        if (users.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            for (User user : users) {
                System.out.println("-------------------------");
                System.out.println("Nombre: " + user.getName());
                System.out.println("Puesto: " + user.getJobTitle());
                System.out.println("Edad: " + user.getAge());
                System.out.println("-------------------------");
            }
        }
    }

    private static void displayProjects(List<Project> projects) {
        System.out.println("--- Listado de Proyectos ---");
        if (projects.isEmpty()) {
            System.out.println("No hay proyectos registrados.");
        } else {
            for (Project project : projects) {
                System.out.println("-------------------------");
                System.out.println("Título: " + project.getTitle());
                if (project.getUser() != null) {
                    System.out.println("Líder: " + project.getUser().getName());
                } else {
                    System.out.println("Líder: (sin asignar)");
                }
                System.out.println("Tareas:");
                if (project.getTasks().isEmpty()) {
                    System.out.println("    (ninguna)");
                } else {
                    for (Task task : project.getTasks()) {
                        System.out.println("    - " + task.getName() + " (" + task.getEstado() + ")");
                    }
                }
                System.out.println("-------------------------");
            }
        }
    }
}