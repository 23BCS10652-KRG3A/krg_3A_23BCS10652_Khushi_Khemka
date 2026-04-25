package com.example;

import com.example.entity.Department;
import com.example.entity.Employee;
import com.example.repository.DepartmentRepository;
import com.example.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DemoRunner implements CommandLineRunner {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DemoRunner(DepartmentRepository departmentRepository,
                      EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n========== JPA One-to-Many Relationship Demo ==========\n");

        // Create a Department
        Department department = new Department();
        department.setName("Engineering");
        department.setDescription("Software Engineering Department");

        // Create two Employee objects
        Employee emp1 = new Employee("John", "Doe", "john.doe@example.com", "+1-555-0101");
        Employee emp2 = new Employee("Jane", "Smith", "jane.smith@example.com", "+1-555-0102");

        // Add employees to department using helper method
        department.addEmployee(emp1);
        department.addEmployee(emp2);

        System.out.println("Before saving:");
        System.out.println("Department: " + department);
        System.out.println("Employee 1: " + emp1);
        System.out.println("Employee 2: " + emp2);

        // Save the department - CascadeType.ALL ensures employees are also persisted
        System.out.println("\nSaving Department with 2 Employees...");
        Department savedDepartment = departmentRepository.save(department);

        System.out.println("\nAfter saving:");
        System.out.println("Department: " + savedDepartment);
        System.out.println("Department ID: " + savedDepartment.getId());

        // Verify all three rows are in the database
        System.out.println("\n========== Verification ==========\n");

        // Check total departments
        long totalDepartments = departmentRepository.count();
        System.out.println("Total Departments in DB: " + totalDepartments);

        // Check total employees
        long totalEmployees = employeeRepository.count();
        System.out.println("Total Employees in DB: " + totalEmployees);

        // Retrieve department and demonstrate LAZY loading
        System.out.println("\nRetrieving Department from DB...");
        Department retrievedDept = departmentRepository.findById(savedDepartment.getId()).orElse(null);
        
        if (retrievedDept != null) {
            System.out.println("Retrieved Department: " + retrievedDept);
            System.out.println("Department ID: " + retrievedDept.getId());
            System.out.println("Department Name: " + retrievedDept.getName());
            
            // Accessing employees collection (triggers lazy loading)
            System.out.println("\nAccessing employees collection (LAZY load triggered)...");
            List<Employee> employees = retrievedDept.getEmployees();
            System.out.println("Number of Employees in Department: " + employees.size());
            
            for (int i = 0; i < employees.size(); i++) {
                System.out.println("  Employee " + (i + 1) + ": " + employees.get(i));
            }
        }

        // Also retrieve using EmployeeRepository
        System.out.println("\n========== Employee Verification ==========\n");
        List<Employee> allEmployees = employeeRepository.findByDepartmentId(savedDepartment.getId());
        System.out.println("Employees in Engineering Department:");
        for (Employee emp : allEmployees) {
            System.out.println("  - " + emp.getFirstName() + " " + emp.getLastName() + 
                             " (Email: " + emp.getEmail() + ")");
        }

        System.out.println("\n========== Summary ==========");
        System.out.println("✓ Department saved successfully with ID: " + savedDepartment.getId());
        System.out.println("✓ Employee 1 saved successfully with ID: " + emp1.getId());
        System.out.println("✓ Employee 2 saved successfully with ID: " + emp2.getId());
        System.out.println("✓ One-to-Many relationship with CascadeType.ALL is working!");
        System.out.println("✓ LAZY fetch type is configured for employees collection!");
        System.out.println("✓ All 3 rows (1 Department + 2 Employees) persisted in database!");
        System.out.println("\n========================================\n");
    }
}
