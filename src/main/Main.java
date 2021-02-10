package main;

import dao.CountriesServices;
import dao.EmployeeServices;
import dao.JobsServices;
import entities.Countries;
import entities.Employees;

import java.text.DecimalFormat;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("$###,###.00");

        CountriesServices cntDAO = new CountriesServices();
        EmployeeServices empDAO = new EmployeeServices();
        JobsServices jobDAO = new JobsServices();

        System.out.println();
        System.out.println("Countries from a specific region");
        List<Countries> cnt = cntDAO.getCountriesByRegion(2);
        cnt.forEach(System.out::println);

        System.out.println();
        System.out.println("Employees with first name like and from specific department");
        List<Employees> empsList = empDAO.getEmployeesList("Al%", "Da%", 80);
        empsList.forEach(System.out::println);

        System.out.println();
        System.out.println("Employees with salaries in a range");
        empDAO.getEmployeesWithSalaryBetween(10000d, 24000d).forEach(System.out::println);

        System.out.println();
        System.out.println("Jobs by salary range");
        jobDAO.getJobsBySalaryRange(2000, 6000).forEach(System.out::println);

        System.out.println();
        System.out.println("Employees ordered by salary");
        empsList = empDAO.getAllEmployeesOrderedBySalary();
        for(Employees emp : empsList) {
            System.out.println(emp.getFirstName() + " " + emp.getLastName() + ": " + emp.getSalary());
        }

        System.out.println();
        System.out.println("Employee info by department");
        List<Object[]> empsInfo =  empDAO.getEmployeeInfoByDepartment();
        for (Object[] empInfo : empsInfo) {
//            System.out.println(Arrays.toString(empInfo));
            System.out.println("Department ID: " + empInfo[3]);
            System.out.println("\tMinimum Salary: " + df.format(empInfo[2]));
            System.out.println("\tMaximum Salary: " + df.format(empInfo[1]));
            System.out.println("\tAverage Salary: " + df.format(empInfo[0]));
            System.out.println();
        }

        System.out.println();
        System.out.println("Country Count By Region");
        List<Object[]> regionsCount = cntDAO.getCountryCountByRegion();
        for (Object[] region : regionsCount) {
//            System.out.println(Arrays.toString(region));
            System.out.println("Region " + region[1] + ": " + region[0] + " countries");
        }
    }
}

