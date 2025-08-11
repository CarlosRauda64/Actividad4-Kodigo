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
