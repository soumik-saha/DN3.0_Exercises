package main.java.com.soumik.EmployeeManagementSystem.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "employees")
@Data
@NamedQuery(name = "Employee.findByEmail", query = "SELECT e FROM Employee e WHERE e.email = :email")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
