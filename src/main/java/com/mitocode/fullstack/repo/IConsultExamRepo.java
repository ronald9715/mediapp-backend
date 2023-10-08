package com.mitocode.fullstack.repo;

import com.mitocode.fullstack.model.ConsultExam;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IConsultExamRepo extends IGenericRepo<ConsultExam, Integer>{
    //Query Nativa: Se escribe tal cual lo escribirias en un motor de BD
    //Se utiliza la anotacion Modifyin para insertar, actualizar y delete
    //@Transactional
    @Modifying
    @Query(value = "INSERT INTO consult_exam(id_consult,id_exam) VALUES (:idConsult,:idExam)", nativeQuery = true)
    Integer saveExam(@Param("idConsult") Integer idConsult, @Param("idExam") Integer idExam);

    @Query("select new ConsultExam (ce.exam) FROM ConsultExam ce where ce.consult.idConsult = :idConsult")
    List<ConsultExam> getExamsByConsultId(@Param("idConsult") Integer idConsult);

}
