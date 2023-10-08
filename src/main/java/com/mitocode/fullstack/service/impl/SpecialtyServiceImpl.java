package com.mitocode.fullstack.service.impl;

import com.mitocode.fullstack.model.Specialty;
import com.mitocode.fullstack.repo.IGenericRepo;
import com.mitocode.fullstack.repo.ISpecialtyRepo;
import com.mitocode.fullstack.service.ISpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecialtyServiceImpl extends CRUDImpl<Specialty, Integer> implements ISpecialtyService {
    private final ISpecialtyRepo repo;
    @Override
    public IGenericRepo<Specialty, Integer> getRepo() {
        return repo;
    }
}
