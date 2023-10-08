package com.mitocode.fullstack.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PatientDTO {
    @EqualsAndHashCode.Include
    private Integer idPatient;
    @NotNull
    @NotEmpty
    @Size(min = 3,max = 50, message = "{firstName.size}")
    private String firstName;
    @NotNull
    @NotEmpty
    private String lastName;
    @NotNull
    @NotEmpty
    private String dni;
    @NotNull
    @NotEmpty
    private String address;
    @NotNull
    @NotEmpty
    private String phone;
    @NotNull
    @NotEmpty
    private String email;
}
