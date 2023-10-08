package com.mitocode.fullstack.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ExamDTO {
    @EqualsAndHashCode.Include
    private Integer idExam;
    @NotNull
    @NotEmpty
    private String nameExam;
    @NotNull
    @NotEmpty
    private String descriptionExam;
}
