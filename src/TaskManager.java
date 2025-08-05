public class TaskManager {

    public TaskManager() {
    }

    public String assignUserTask(IUser user, Task task){
        if(task.getUser() == null){
            task.setUser(user);
            return "La tarea " + task.getName() +
                    "\n se asigno al usuario: " + user.getName();
        } else {
            return "La tarea " + task.getName() +
                    "\n ya tiene un usuario asignado: " + task.getUser().getName();
        }
    }
}
