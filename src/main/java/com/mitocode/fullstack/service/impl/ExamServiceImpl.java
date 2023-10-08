package com.mitocode.fullstack.service.impl;

import com.mitocode.fullstack.model.Exam;
import com.mitocode.fullstack.repo.IExamRepo;
import com.mitocode.fullstack.repo.IGenericRepo;
import com.mitocode.fullstack.service.IExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl extends CRUDImpl<Exam, Integer> implements IExamService {
    private final IExamRepo repo;
    @Override
    public IGenericRepo<Exam, Integer> getRepo() {
        return repo;
    }
}
