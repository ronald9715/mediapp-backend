package com.mitocode.fullstack.service.impl;

import com.mitocode.fullstack.model.ConsultExam;
import com.mitocode.fullstack.repo.IConsultExamRepo;
import com.mitocode.fullstack.service.IConsultExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultExamServiceImpl implements IConsultExamService {

    private final IConsultExamRepo repo;
    @Override
    public List<ConsultExam> getExamsByConsultId(Integer idConsult) {
        return repo.getExamsByConsultId(idConsult);
    }
}
