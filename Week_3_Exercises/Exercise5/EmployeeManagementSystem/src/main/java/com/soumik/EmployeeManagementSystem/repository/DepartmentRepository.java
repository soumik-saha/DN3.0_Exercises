package main.java.com.soumik.EmployeeManagementSystem.repository;

import main.java.com.soumik.EmployeeManagementSystem.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
