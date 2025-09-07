package org.example.springdatajpapractice.repository;

import org.example.springdatajpapractice.entity.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends CrudRepository<Department, Integer> {
}
