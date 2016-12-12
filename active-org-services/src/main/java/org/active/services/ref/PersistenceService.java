package org.active.services.ref;

import javax.persistence.Query;
import java.util.List;

/**
 *
 */
public interface PersistenceService<T> {

    public void save(T t);

    public List<T> getAll();
}
