package com.rankingEmpleados.service;

import com.rankingEmpleados.customException.EmployeeNotFoundExcepcion;
import com.rankingEmpleados.model.Employee;
import com.rankingEmpleados.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    //Agregar Empleado
    public void addEmployee(String name, String pass) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setPassword(pass);
        employeeRepository.save(employee);
    }

    //Listar empleados
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    //Modificar empleado
    public void updateEmployee(long id, String newName, String newPass) {
        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee != null) {
            employee.setName(newName);
            employee.setPassword(newPass);
            employeeRepository.save(employee);
        } else {
            throw new EmployeeNotFoundExcepcion("Empleado no encontrado con el ID: " +
                    id);
        }
    }

    //Eliminar empleado
    public void deleteEmployee(String employee) {
        Employee employee1 = employeeRepository.findByName(employee);
        employeeRepository.delete(employee1);
    }

    //Agregar cita al empleado
    public void addAppointmentForEmployee(String employeeName) {
        Employee employee = employeeRepository.findByName(employeeName);

        if (employee != null) {
            employee.setNumberOfAppointments(employee.getNumberOfAppointments() + 1);
            employeeRepository.save(employee);
        } else {
            throw new EmployeeNotFoundExcepcion("El empleado no se encontró:" + employeeName);
        }
    }

    public List<Employee> rankingEmployee() {
        return employeeRepository.findAll();
    }

}
