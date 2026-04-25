package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 255)
    private String description;

    /**
     * One-to-Many relationship to Employee
     * - FetchType.LAZY: Employees are loaded only when accessed
     * - CascadeType.ALL: All operations (persist, merge, remove, refresh) cascade to employees
     * - orphanRemoval = true: Removes employee if removed from the list
     */
    @OneToMany(
        mappedBy = "department",
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Employee> employees = new ArrayList<>();

    /**
     * Helper method to add an employee to the department
     */
    public void addEmployee(Employee employee) {
        employee.setDepartment(this);
        this.employees.add(employee);
    }

    /**
     * Helper method to remove an employee from the department
     */
    public void removeEmployee(Employee employee) {
        this.employees.remove(employee);
        employee.setDepartment(null);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", employeeCount=" + employees.size() +
                '}';
    }
}
