package com.mitocode.fullstack.service.impl;

import com.mitocode.fullstack.model.Patient;
import com.mitocode.fullstack.repo.IGenericRepo;
import com.mitocode.fullstack.repo.IPatientRepo;
import com.mitocode.fullstack.service.IPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PatientServiceImpl extends CRUDImpl<Patient, Integer> implements IPatientService {
    private final IPatientRepo repo;

    @Override
    public IGenericRepo<Patient, Integer> getRepo() {
        return repo;
    }

    @Override
    public Page<Patient> listPage(Pageable pageable) {
        return repo.findAll(pageable);
    }
}
