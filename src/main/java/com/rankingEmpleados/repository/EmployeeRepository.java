package com.rankingEmpleados.repository;

import com.rankingEmpleados.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByName(String employeeName);

    Optional<Employee> findByUsername(String username);

}
