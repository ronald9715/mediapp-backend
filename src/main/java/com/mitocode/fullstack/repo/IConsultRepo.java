package com.mitocode.fullstack.repo;

import com.mitocode.fullstack.dto.ConsultProcDTO;
import com.mitocode.fullstack.dto.IConsultProcDTO;
import com.mitocode.fullstack.model.Consult;
import com.mitocode.fullstack.model.ConsultExamPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IConsultRepo extends IGenericRepo<Consult, Integer>{

    @Query("FROM Consult c where c.patient.dni = :dni or lower(c.patient.firstName)  like %:fullname% or lower(c.patient.lastName)  like %:fullname%") //JPQL Java Persistence Query Language
    List<Consult> search(@Param("dni") String dni, @Param("fullname") String fullname);

    @Query("FROM Consult c where c.consultDate between :date1 and :date2")
    List<Consult> searchByDates(@Param("date1")LocalDateTime date1, @Param("date2") LocalDateTime date2);

    //Query Nativo: tal cual se escribe en el motor de BD usando SQL
    @Query(value = "select * from fn_list()", nativeQuery = true)
    List<Object[]> callProcedureOrFunctionNative();

    @Query(value = "select * from fn_list()", nativeQuery = true)
    List<IConsultProcDTO> callProcedureOrFunctionProjection();

}

