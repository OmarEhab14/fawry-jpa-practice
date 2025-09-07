package org.example.springdatajpapractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.example.springdatajpapractice.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    @Query("SELECT emp FROM Employee emp WHERE emp.name LIKE %:name%")
    List<Employee> findByName(String name);

    @Query("SELECT emp FROM Employee emp JOIN emp.department dept WHERE dept.id = :deptId")
    List<Employee> findByDepartment(Integer deptId);
}
