package services.Impl;

import dao.API.OptionDAO;
import dao.API.TariffDAO;
import dao.Impl.OptionDAOImpl;
import dao.Impl.TariffDAOImpl;
import dao.JpaUtil;
import entities.Option;
import entities.Tariff;
import org.apache.log4j.Logger;
import services.API.TariffService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandra on 26.02.2016.
 */
public class TariffServiceImpl implements TariffService {

    private TariffDAO tariffDAO;
    private OptionDAO optionDAO;

    private final static Logger logger = Logger.getLogger(TariffServiceImpl.class);

    public boolean addTariff(String name, String price, String[] possibleOptions) {
        Tariff tariff = new Tariff();
        tariff.setName(name);
        tariff.setPrice(Integer.valueOf(price));

        EntityManager em = JpaUtil.getEntityManager();
        tariffDAO = new TariffDAOImpl(em);
        optionDAO = new OptionDAOImpl(em);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        if (possibleOptions != null) {
            List<Option> options = new ArrayList<Option>();
            for (String optionId : possibleOptions) {
                options.add(optionDAO.findById(Option.class, Integer.valueOf(optionId)));
            }
            tariff.setOptions(options);
        }
        tariffDAO.save(tariff);
        logger.trace("Log");
        transaction.commit();
        em.close();
        return true;
    }

    public List<Tariff> getAllTariffs() {
        EntityManager em = JpaUtil.getEntityManager();
        tariffDAO = new TariffDAOImpl(em);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<Tariff> tariffs = tariffDAO.findAll(Tariff.class);
        transaction.commit();
        em.close();
        return tariffs;
    }

    public List<Tariff> findTariffByName(String name){
        EntityManager em = JpaUtil.getEntityManager();
        tariffDAO = new TariffDAOImpl(em);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<Tariff> tariffs = tariffDAO.findTariffByName("%" + name + "%");
        transaction.commit();
        em.close();
        return tariffs;
    }

    public Tariff getTariffById(String id){
        EntityManager em = JpaUtil.getEntityManager();
        tariffDAO = new TariffDAOImpl(em);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Tariff tariff = tariffDAO.findById(Tariff.class, Integer.valueOf(id));
        transaction.commit();
        em.close();
        return tariff;
    }

    public void removeTariffById(String id){
        EntityManager em = JpaUtil.getEntityManager();
        tariffDAO = new TariffDAOImpl(em);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        tariffDAO.removeTariffById(Integer.valueOf(id));
        transaction.commit();
        em.close();
    }

    public void changeTariff(String id, String name, String price, String[] possibleOptions) {

        EntityManager em = JpaUtil.getEntityManager();
        tariffDAO = new TariffDAOImpl(em);
        optionDAO = new OptionDAOImpl(em);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Tariff tariff = tariffDAO.findById(Tariff.class, Integer.valueOf(id));
        tariff.setName(name);
        tariff.setPrice(Integer.valueOf(price));

        if (possibleOptions != null) {
            List<Option> options = new ArrayList<Option>();
            for (String optionId : possibleOptions) {
                options.add(optionDAO.findById(Option.class, Integer.valueOf(optionId)));
            }
            tariff.setOptions(options);
        }
        transaction.commit();
        em.close();
    }
}
