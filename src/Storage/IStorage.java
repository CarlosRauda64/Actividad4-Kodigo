package Storage;

import java.util.List;

public interface IStorage<Item> {
    void add(Item item);
    List<Item> getAll();
    void delete(Item item);
    Item getByName(String name);
}
