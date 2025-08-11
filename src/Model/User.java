package Model;

public class User implements IUser {
    private String name;
    private int age;
    private String jobTitle;

    public User(String name, String jobTitle, int age) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return "Nombre: " + name +
                "Edad: " + age +
                "Puesto: " + jobTitle;
    }
}
