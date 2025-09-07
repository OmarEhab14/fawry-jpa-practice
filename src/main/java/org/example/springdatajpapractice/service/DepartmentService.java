package org.example.springdatajpapractice.service;

import org.example.springdatajpapractice.entity.Department;
import org.example.springdatajpapractice.entity.Employee;
import org.example.springdatajpapractice.repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    private final DepartmentRepo departmentRepo;

    @Autowired
    public DepartmentService(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    public Department getDepartmentById(Integer id) {
        return departmentRepo.findById(id).orElse(null);
    }

    public Iterable<Department> getAllDepartments() {
        return departmentRepo.findAll();
    }

    public Department addDepartment(Department department) {
        return departmentRepo.save(department);
    }

    public Department updateDepartment(Integer id, Department department) {
        Department current = departmentRepo.findById(id).orElse(null);
        if (current == null) {
            return null;
        }
        current.setName(department.getName());
        return departmentRepo.save(current);
    }

    public Department deleteDepartment(Integer id) {
        Department current = departmentRepo.findById(id).orElse(null);
        if (current == null) {
            return null;
        }
        departmentRepo.deleteById(id);
        return current;
    }
}
