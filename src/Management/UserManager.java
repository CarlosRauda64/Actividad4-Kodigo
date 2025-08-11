package Management;

import Model.User;
import Storage.IStorage;
import java.util.List;

public class UserManager {

    private IStorage storage;

    /**
     * Constructor de la clase
     * @param storage
     */
    public UserManager(IStorage storage) {
        this.storage = storage;
    }

    public void createUser(String name, int age, String jobTitle){
        User newUser = new User(name, jobTitle, age);
        this.storage.add(newUser);
    }

    public void editUser(User user, String newName, String newJobTitle, int newAge){
        user.setName(newName);
        user.setJobTitle(newJobTitle);
        user.setAge(newAge);
    }

    public void deleteUser(User user){
        this.storage.delete(user);
    }

    public List<User> getAllUsers(){
        return this.storage.getAll();
    }

    public User getUserById(int id){
        return (User) this.storage.getById(id);
    }

}
