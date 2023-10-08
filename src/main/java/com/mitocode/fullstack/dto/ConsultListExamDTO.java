package com.mitocode.fullstack.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultListExamDTO {

    @NotNull
    private ConsultDTO consultDTO;
    @NotNull
    private List<ExamDTO> listExam;
}
