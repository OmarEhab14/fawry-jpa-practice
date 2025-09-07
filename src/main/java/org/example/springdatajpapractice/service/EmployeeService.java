package org.example.springdatajpapractice.service;

import org.example.springdatajpapractice.repository.DepartmentRepo;
import org.example.springdatajpapractice.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.springdatajpapractice.entity.Employee;

import java.util.List;


@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final DepartmentService departmentService;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo,  DepartmentService departmentService) {
        this.employeeRepo = employeeRepo;
        this.departmentService = departmentService;
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepo.findById(id).orElse(null);
    }

    public Iterable<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public List<Employee>  findByName(String name) {
        return employeeRepo.findByName(name);
    }

    public List<Employee> findByDepartment(Integer deptId) {
        return employeeRepo.findByDepartment(deptId);
    }

    public Employee addEmployee(Employee employee) {
        if (employee.getDepartment() != null && employee.getDepartment().getId() != null) {
            employee.setDepartment(departmentService.getDepartmentById(employee.getDepartment().getId()));
        }
        return employeeRepo.save(employee);
    }

    public Employee updateEmployee(Integer id, Employee employee) {
        Employee current = employeeRepo.findById(id).orElse(null);
        if (current == null) {
            return null;
        }
        current.setName(employee.getName());
        current.setSalary(employee.getSalary());
        current.setDepartment(employee.getDepartment());
        return employeeRepo.save(current);
    }

    public Employee deleteEmployee(Integer id) {
        Employee current = employeeRepo.findById(id).orElse(null);
        if (current == null) {
            return null;
        }
        employeeRepo.deleteById(id);
        return current;
    }
}
