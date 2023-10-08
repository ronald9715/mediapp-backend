package com.mitocode.fullstack.repo;

import com.mitocode.fullstack.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientRepo extends IGenericRepo<Patient, Integer> {

}

