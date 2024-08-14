package main.java.com.soumik.EmployeeManagementSystem.service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public void saveEmployees(List<Employee> employees) {
        employeeRepository.saveAll(employees);
    }
}
