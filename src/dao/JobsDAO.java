package dao;

import entities.Jobs;
import java.util.List;

public interface JobsDAO {
    List<Jobs> getJobsBySalaryRange(Integer minSalary, Integer maxSalary);
}
