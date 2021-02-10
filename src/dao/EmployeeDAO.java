package dao;

import entities.Employees;

import java.util.List;
import java.util.Objects;

public interface EmployeeDAO {
    List<Employees> getEmployeesList(String nameLike1, String nameLike2, Integer department);
    List<Employees> getEmployeesWithSalaryBetween(Double minSalary, Double maxSalary);
    List<Employees> getAllEmployeesOrderedBySalary();
    List<Object[]> getEmployeeInfoByDepartment();
}
