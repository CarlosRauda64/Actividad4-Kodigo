package Storage;

import Model.User;
import java.util.List;
import java.util.ArrayList;

public class UserStorage implements IStorage<User> {
    private List<User> users = new ArrayList<>();

    @Override
    public void add(User user) { users.add(user); }

    @Override
    public List<User> getAll() { return users; }

    @Override
    public void delete(User user) { users.remove(user); }

    @Override
    public User getByName(String name){
        for(User user : users){
            if(user.getName().equals(name)){
                return user;
            }
        }
        return null;
    }
}
