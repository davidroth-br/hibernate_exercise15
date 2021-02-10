package dao;

import entities.Employees;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

import java.util.List;

public class EmployeeServices implements EmployeeDAO {

    private Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public List<Employees> getEmployeesList(String nameLike1, String nameLike2, Integer department) {
        Criteria query = session.createCriteria(Employees.class);
        query.add(Restrictions.or(Restrictions.like("firstName", nameLike1), Restrictions.like("firstName", nameLike2)));
        query.add(Restrictions.eq("departmentId", department));
        return query.list();
    }

    @Override
    public List<Employees> getEmployeesWithSalaryBetween(Double minSalary, Double maxSalary) {
        Criteria query = session.createCriteria(Employees.class);
        query.add(Restrictions.between("salary", minSalary, maxSalary));
        List list = query.list();
        return list;
    }

    @Override
    public List<Employees> getAllEmployeesOrderedBySalary() {
        return session.createCriteria(Employees.class).addOrder(Order.asc("salary")).list();
    }

    @Override
    public List<Object[]> getEmployeeInfoByDepartment() {
        return session.createCriteria(Employees.class)
                .setProjection(Projections.projectionList()
                        .add(Projections.avg("salary").as("avgSal"))
                        .add(Projections.max("salary"))
                        .add(Projections.min("salary"))
                        .add(Projections.groupProperty("departmentId"))
                )
                .addOrder(Order.desc("avgSal"))
                .list();
    }
}
