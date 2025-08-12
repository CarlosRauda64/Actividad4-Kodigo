/**
 * Implementación de la interfaz IStorage para la persistencia de objetos de tipo User.
 * Utiliza una lista en memoria para almacenar y gestionar los usuarios.
 *
 * Esta clase cumple con el Principio de Responsabilidad Única (SRP) al
 * tener como única función la gestión del almacenamiento de usuarios.
 * Al implementar {@link IStorage}, se adhiere al Principio de Inversión de Dependencias (DIP),
 * permitiendo que las clases de gestión dependan de la interfaz y no de esta
 * implementación de almacenamiento en memoria.
 */
package Storage;

import Model.User;
import java.util.List;
import java.util.ArrayList;

public class UserStorage implements IStorage<User> {
    private List<User> users = new ArrayList<>();
    private int nextId = 1;

    @Override
    public void add(User user) {
        user.setId(nextId);
        this.nextId++;
        users.add(user);
    }

    @Override
    public List<User> getAll() { return users; }

    @Override
    public void delete(User user) { users.remove(user); }

    @Override
    public User getById(int id){
        for(User user : users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }
}
