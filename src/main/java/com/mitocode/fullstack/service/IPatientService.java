package com.mitocode.fullstack.service;

import com.mitocode.fullstack.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPatientService extends ICRUD<Patient, Integer>{
    /*Patient create(Patient patient) throws Exception;
    Patient update(Patient patient) throws Exception;
    List<Patient> readAll() throws Exception;
    Patient readById(Integer id) throws Exception;
    void deleteById(Integer id) throws Exception;*/
    Page<Patient> listPage(Pageable pageable);
}
