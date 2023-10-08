package com.mitocode.fullstack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Consult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idConsult;
    @ManyToOne
    @JoinColumn(name = "id_patient", nullable = false, foreignKey = @ForeignKey(name = "FK_CONSULT_PATIENT"))
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "id_specialty", nullable = false)
    private Specialty specialty;
    @ManyToOne
    @JoinColumn(name = "id_medic", nullable = false)
    private Medic medic;
    @Column(length = 3, nullable = false)
    private String numConsult;
    @Column(nullable = false)
    private LocalDateTime consultDate;
    //Maestro-Detalle
    @OneToMany(mappedBy = "consult", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ConsultDetail> details;

}
