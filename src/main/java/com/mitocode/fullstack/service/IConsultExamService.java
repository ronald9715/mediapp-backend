package com.mitocode.fullstack.service;

import com.mitocode.fullstack.model.ConsultExam;

import java.util.List;

public interface IConsultExamService {
    List<ConsultExam> getExamsByConsultId(Integer idConsult);
}
