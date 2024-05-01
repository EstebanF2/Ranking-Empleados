package com.rankingEmpleados.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "contraseña")
    private String password;

    @Column(name = "cita")
    private int numberOfAppointments;


}
