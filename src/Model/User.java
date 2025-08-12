/**
 * Clase que representa la entidad "Usuario" en el sistema de gestión de proyectos.
 * Almacena los atributos básicos de un usuario como su ID único, nombre, edad y puesto de trabajo.
 *
 * Esta clase cumple con el Principio de Responsabilidad Única (SRP), ya que su
 * única responsabilidad es modelar los datos del usuario. Toda la lógica de negocio
 * relacionada con la gestión de usuarios (creación, edición, eliminación) se
 * maneja en la clase {@link Management.UserManager}.
 */
package Model;

public class User implements IUser {
    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
