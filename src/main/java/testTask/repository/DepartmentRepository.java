package testTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import testTask.entity.Department;
import testTask.entity.Lector;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT d.departmentHead FROM Department d WHERE d.departmentName = :departmentName")
    Lector findDepartmentHead(@Param("departmentName") String departmentName);


    @Query("SELECT d.lectors FROM Department d WHERE d.departmentName = :departmentName")
    List<Lector> findAllDepartmentLectors(@Param("departmentName") String departmentName);
}
