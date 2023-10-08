package com.mitocode.fullstack.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mitocode.fullstack.model.ConsultDetail;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ConsultDTO {
    @EqualsAndHashCode.Include
    private Integer idConsult;
    //private Integer idPatient;
    private PatientDTO patient;
    @NotNull
    private SpecialtyDTO specialty;
    @NotNull
    private MedicDTO medic;
    @NotNull
    private String numConsult;
    @NotNull
    private LocalDateTime consultDate;

    @JsonManagedReference
    @NotNull
    private List<ConsultDetailDTO> details;
}
