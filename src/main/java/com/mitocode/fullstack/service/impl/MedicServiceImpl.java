package com.mitocode.fullstack.service.impl;

import com.mitocode.fullstack.model.Medic;
import com.mitocode.fullstack.model.Patient;
import com.mitocode.fullstack.repo.IGenericRepo;
import com.mitocode.fullstack.repo.IMedicRepo;
import com.mitocode.fullstack.service.IMedicService;
import com.mitocode.fullstack.service.IPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicServiceImpl extends CRUDImpl<Medic, Integer> implements IMedicService {

    private final IMedicRepo repo;
    @Override
    public IGenericRepo<Medic, Integer> getRepo() {
        return repo;
    }
}
