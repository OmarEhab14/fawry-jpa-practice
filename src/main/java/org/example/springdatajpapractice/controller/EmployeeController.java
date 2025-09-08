package org.example.springdatajpapractice.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.springdatajpapractice.DTOs.PagedResponse;
import org.example.springdatajpapractice.entity.Employee;
import org.example.springdatajpapractice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<PagedResponse<Employee>> findAll(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "8") int pageSize,
                                      @RequestParam(defaultValue = "id,asc") String[] sort,
                                      HttpServletRequest request) {

        Sort.Direction direction = sort[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(direction, sort[0]));

        String baseUrl = request.getRequestURL().toString();
        Page<Employee> employeePage = employeeService.getAllEmployees(pageable);

        String nextPageUrl = employeePage.hasNext() ?  baseUrl + "?page=" + (page + 1) + "&pageSize=" + pageable.getPageSize() + "&sort=" + sort[0] + "," + sort[1] : null;
        String prevPageUrl = employeePage.hasPrevious() ? baseUrl + "?page=" + (page - 1) + "&pageSize=" + pageable.getPageSize() + "&sort=" + sort[0] + "," + sort[1] : null;

        PagedResponse<Employee> response = new PagedResponse<>(employeePage.getContent(), page, pageSize, nextPageUrl, prevPageUrl);

        return ResponseEntity.ok(response);
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
