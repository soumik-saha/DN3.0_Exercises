package main.java.com.soumik.EmployeeManagementSystem.repository;


import main.java.com.soumik.EmployeeManagementSystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
