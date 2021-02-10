package dao;

import entities.Countries;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import util.HibernateUtil;

import java.util.List;

public class CountriesServices implements CountriesDAO {

    private Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public List<Countries> getCountriesByRegion(Integer regionId) {
        return session.createCriteria(Countries.class)
                .add(Restrictions.eq("regionId", regionId)
                )
                .list();
    }

    @Override
    public List<Object[]> getCountryCountByRegion() {
        return session.createCriteria(Countries.class)
                .setProjection(Projections.projectionList()
                        .add(Projections.count("countryName"))
                        .add(Projections.groupProperty("regionId").as("region"))
                )
                .addOrder(Order.asc("region"))
                .list();
    }
}
