package com.mitocode.fullstack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Signos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSignos;
    @ManyToOne
    @JoinColumn(name = "id_patient", nullable = false)
    private Patient patient;
    @Column(nullable = false)
    private LocalDate date;
    @Column(length = 10, nullable = false)
    private String temperatura;
    @Column(length = 10, nullable = false)
    private String pulso;
    @Column(length = 10, nullable = false)
    private String ritmo;
}
