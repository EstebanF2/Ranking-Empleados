package com.rankingEmpleados.controller;

import com.rankingEmpleados.customException.EmployeeNotFoundExcepcion;
import com.rankingEmpleados.model.Employee;
import com.rankingEmpleados.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/agregar-empleado")
    public ResponseEntity<String> addEmployee(@RequestBody employeeRequest employeeRequest) {
        try {
            employeeService.addEmployee(employeeRequest.getName(), employeeRequest.getPass());
            return new ResponseEntity<>("Empleado agregado exitosamente: "
                    + employeeRequest.getName(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ocurrio un error al agregar el empleado",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/modificar-empleado")
    public ResponseEntity<String> updateEmployee(
            @RequestParam Long id, @RequestParam String newName, @RequestParam String newPass) {
        try {
            employeeService.updateEmployee(id, newName, newPass);
            return new ResponseEntity<>("Empleado actualizado exitosamente",
                    HttpStatus.OK);
        } catch (EmployeeNotFoundExcepcion e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Ocurrio un error al actualizar el empleado",
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/listar-empleados")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @DeleteMapping("/eliminar/{employee}")
    public String deleteEmployee(@PathVariable String employee) {
        employeeService.deleteEmployee(employee);
        return "Empleado eliminado correctamente " + employee;
    }

    @PostMapping("/agregar-cita")
    public ResponseEntity<String> addApointmentForEmployee(@RequestBody AppointmentRequest appointmentRequest) {

        try {
            employeeService.addAppointmentForEmployee(appointmentRequest.getEmployeeName());
            return new ResponseEntity<>("Cita agregada exitosamente para el empleado: " + appointmentRequest.getEmployeeName(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ocurrio un error al agregar la cita para el empleado: " + appointmentRequest.getEmployeeName(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("ranking")
    public List<Employee> ranking() {
        return employeeService.rankingEmployee();
    }

    //Clases anidadas.
    public static class AppointmentRequest {
        private String employeeName;

        public String getEmployeeName() {
            return employeeName;
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
        }
    }

    public static class employeeRequest {
        private String name;
        private String pass;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPass(){return pass;}

        public void setPass(String pass) {this.pass = pass;}
    }

}
