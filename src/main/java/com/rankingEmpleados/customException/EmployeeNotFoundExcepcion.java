package com.rankingEmpleados.customException;

public class EmployeeNotFoundExcepcion extends RuntimeException{
    public EmployeeNotFoundExcepcion(String message) {
        super(message);
    }
}
