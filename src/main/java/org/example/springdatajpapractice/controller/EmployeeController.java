package org.example.springdatajpapractice.controller;

import org.example.springdatajpapractice.entity.Employee;
import org.example.springdatajpapractice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Employee>> findByName(@RequestParam(name = "empName") String name) {
        return ResponseEntity.ok(employeeService.findByName(name));
    }

    @GetMapping("/department/{deptId}")
    public ResponseEntity<List<Employee>> findByDepartment(@PathVariable Integer deptId) {
        return ResponseEntity.ok(employeeService.findByDepartment(deptId));
    }

    @GetMapping("")
    public Iterable<Employee> findAll() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("")
    public Employee insertEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        if (updatedEmployee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Integer id) {
        Employee deletedEmployee = employeeService.deleteEmployee(id);
        if (deletedEmployee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deletedEmployee);
    }
}
