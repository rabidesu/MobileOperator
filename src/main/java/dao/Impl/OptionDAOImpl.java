package dao.Impl;

import dao.API.OptionDAO;
import entities.Option;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Alexandra on 25.02.2016.
 */
public class OptionDAOImpl extends GenericDAOImpl<Option, Integer> implements OptionDAO {

    public OptionDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    public List<Option> getAnotherOptions(int id){
        Query query = entityManager.createNamedQuery(Option.GET_ANOTHER_OPTIONS);
        query.setParameter(1, id);
        return findMany(query);
    }

    public void removeOptionById(int id){
        Query query = entityManager.createNamedQuery(Option.REMOVE_BY_ID);
        query.setParameter(1, id);
        query.executeUpdate();
    }

    public List<Option> getOptionsByName(String name){
        Query query = entityManager.createNamedQuery(Option.GET_BY_NAME);
        query.setParameter(1, name);
        return findMany(query);
    }

}
