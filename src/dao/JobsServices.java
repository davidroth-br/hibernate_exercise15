package dao;

import entities.Jobs;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

import java.util.List;

public class JobsServices implements JobsDAO {

    private Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public List<Jobs> getJobsBySalaryRange(Integer minSalary, Integer maxSalary) {
        return session.createCriteria(Jobs.class).add(Restrictions.ge("minSalary", minSalary)).add(Restrictions.le("maxSalary", maxSalary)).list();
    }
}
