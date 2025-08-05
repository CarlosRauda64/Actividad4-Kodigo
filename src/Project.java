import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Project{
    private String title;
    private IUser user;
    private List<Task> tasks;

    public Project(User user, String title) {
        this.user = user;
        this.title = title;
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks(){
        return Collections.unmodifiableList(tasks);
    }

    public void addTask(Task task){
        if(task != null && !tasks.contains(task)){
            this.tasks.add(task);
        }
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTask(Task task){
        this.tasks.add(task);
    }

    public IUser getUser() {
        return user;
    }

    public void setUser(IUser user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }
}
