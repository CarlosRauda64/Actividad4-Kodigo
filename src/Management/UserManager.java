package Management;

import Model.User;
import Storage.IStorage;
import java.util.List;

public class UserManager {

    private IStorage storage;

    /**
     * Constructor para inicializar un objeto de tipo UserManager.
     * @param storage La instancia de almacenamiento de usuarios (IStorage).
     */
    public UserManager(IStorage storage) {
        this.storage = storage;
    }

    /**
     * Crea un nuevo usuario y lo añade al almacenamiento.
     * @param name El nombre del nuevo usuario.
     * @param age La edad del usuario.
     * @param jobTitle El puesto de trabajo del usuario.
     */
    public void createUser(String name, int age, String jobTitle){
        User newUser = new User(name, jobTitle, age);
        this.storage.add(newUser);
    }

    /**
     * Edita la información de un usuario existente.
     * @param user El usuario a editar.
     * @param newName El nuevo nombre.
     * @param newJobTitle El nuevo puesto de trabajo.
     * @param newAge La nueva edad.
     */
    public void editUser(User user, String newName, String newJobTitle, int newAge){
        user.setName(newName);
        user.setJobTitle(newJobTitle);
        user.setAge(newAge);
    }

    /**
     * Elimina un usuario del almacenamiento.
     * @param user El usuario a eliminar.
     */
    public void deleteUser(User user){
        this.storage.delete(user);
    }

    /**
     * Retorna la lista completa de todos los usuarios.
     * @return Una lista de todos los usuarios.
     */
    public List<User> getAllUsers(){
        return this.storage.getAll();
    }

    /**
     * Obtiene un usuario específico por su ID.
     * @param id El ID del usuario a buscar.
     * @return El usuario encontrado, o null si no existe.
     */
    public User getUserById(int id){
        return (User) this.storage.getById(id);
    }

}
