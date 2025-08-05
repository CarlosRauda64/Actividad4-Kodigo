public class Task{
    private String name;
    private Estado estado;
    private IUser user;
    private String description;
    private Project project;

    public Task(String name, Estado estado, String description) {
        this.name = name;
        this.estado = estado;
        this.description = description;
        this.project = null;
        this.user = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IUser getUser() {
        return user;
    }

    public void setUser(IUser user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}