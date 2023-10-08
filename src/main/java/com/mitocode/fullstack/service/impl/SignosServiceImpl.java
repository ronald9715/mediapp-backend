package com.mitocode.fullstack.service.impl;

import com.mitocode.fullstack.model.Signos;
import com.mitocode.fullstack.repo.IGenericRepo;
import com.mitocode.fullstack.repo.ISignosRepo;
import com.mitocode.fullstack.service.ISignosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignosServiceImpl extends CRUDImpl<Signos, Integer> implements ISignosService {
    private final ISignosRepo repo;
    @Override
    public IGenericRepo<Signos, Integer> getRepo() {
        return repo;
    }
}
