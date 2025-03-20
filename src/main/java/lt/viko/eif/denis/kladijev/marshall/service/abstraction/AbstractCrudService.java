package lt.viko.eif.denis.kladijev.marshall.service.abstraction;

import java.util.List;
import java.util.Optional;

/**
 * Abstraction for all service classes.
 * @param <T> Generic type of the entity (Player, Game, Achievement, Item).
 * @param <ID> Generic type identifier (Long in my case).
 */

public abstract class AbstractCrudService<T, ID>
{
    public abstract List<T> getAll();
    public abstract Optional<T> getById(ID id);
    public abstract T save(T entity);
    public abstract void delete(ID id);
}
