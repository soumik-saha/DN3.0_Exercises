public class EmployeeManagementSystem {
    private Employee[] employees;
    private int size;

    public EmployeeManagementSystem(int capacity) {
        employees = new Employee[capacity];
        size = 0;
    }

    public void addEmployee(Employee employee) {
        if (size < employees.length) {
            employees[size++] = employee;
        } else {
            System.out.println("Array is full.");
        }
    }

    public Employee searchEmployee(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                return employees[i];
            }
        }
        return null;
    }

    public void traverseEmployees() {
        for (int i = 0; i < size; i++) {
            System.out.println("ID: " + employees[i].getEmployeeId() + ", Name: " + employees[i].getName());
        }
    }

    public void deleteEmployee(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                for (int j = i; j < size - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--size] = null;
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem(10);

        ems.addEmployee(new Employee(1, "Soumik Saha", "Manager", 80000));
        ems.addEmployee(new Employee(2, "Sourav Saha", "Developer", 60000));

        ems.traverseEmployees();

        Employee emp = ems.searchEmployee(1);
        if (emp != null) {
            System.out.println("Found employee: " + emp.getName());
        } else {
            System.out.println("Employee not found.");
        }

        ems.deleteEmployee(2);
        ems.traverseEmployees();
    }
}
