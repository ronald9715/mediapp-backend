package com.mitocode.fullstack.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
/*@Getter
@Setter
@EqualsAndHashCode
@ToString*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idPatient;
    @Column(length = 30, nullable = false)
    private String firstName;
    @Column(length = 30, nullable = false)
    private String lastName;
    @Column(length = 8, nullable = false)
    private String dni;
    @Column(length = 30, nullable = false)
    private String address;
    @Column(length = 9, nullable = false)
    private String phone;
    @Column(nullable = false)
    private String email;


}
