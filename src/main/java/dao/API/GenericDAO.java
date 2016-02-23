package dao.API;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Alexandra on 19.02.2016.
 */
public interface GenericDAO<T, ID extends Serializable> {

    public void save(T entity);

    public void merge(T entity);

    public void delete(T entity);

    public List<T> findMany(Query query);

    public T findOne(Query query);

    public List findAll(Class clazz);

    public T findById(Class clazz, int id);

}
