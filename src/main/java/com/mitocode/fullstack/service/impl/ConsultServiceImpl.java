package com.mitocode.fullstack.service.impl;

import com.mitocode.fullstack.dto.ConsultProcDTO;
import com.mitocode.fullstack.dto.IConsultProcDTO;
import com.mitocode.fullstack.model.Consult;
import com.mitocode.fullstack.model.Exam;
import com.mitocode.fullstack.repo.IConsultExamRepo;
import com.mitocode.fullstack.repo.IConsultRepo;
import com.mitocode.fullstack.repo.IGenericRepo;
import com.mitocode.fullstack.service.IConsultService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ConsultServiceImpl extends CRUDImpl<Consult, Integer> implements IConsultService {

    private final IConsultRepo repo;
    private final IConsultExamRepo examRepo;

    @Override
    public IGenericRepo<Consult, Integer> getRepo() {
        return repo;
    }

    @Transactional
    @Override
    public Consult saveTransactional(Consult consult, List<Exam> exams) {
        repo.save(consult);
        exams.forEach(exam -> {
            examRepo.saveExam(consult.getIdConsult(),exam.getIdExam());
        });
        return consult;
    }

    @Override
    public List<Consult> search(String dni, String fullname) {
        return repo.search(dni, fullname);
    }

    @Override
    public List<Consult> searchByDates(LocalDateTime date1, LocalDateTime date2) {
        final int OFFSET_DAYS = 1;
        return repo.searchByDates(date1, date2.plusDays(OFFSET_DAYS));
    }

    @Override
    public List<ConsultProcDTO> callProcedureOrFunctionNative() {
        List<ConsultProcDTO> lst = new ArrayList<>();

        repo.callProcedureOrFunctionNative().forEach(el->{
            ConsultProcDTO proc = new ConsultProcDTO();
            proc.setQuantity((Integer) el[0]);
            proc.setConsultDate((String)el[1]);
            lst.add(proc);
        });
        return lst;
    }

    @Override
    public List<IConsultProcDTO> callProcedureOrFunctionProjection() {

        return repo.callProcedureOrFunctionProjection();
    }

    @Override
    public byte[] generateReport() throws Exception {
        byte[] data = null;
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("txt_title", "Report Title");

        File file = new ClassPathResource("/reports/report.jasper").getFile();//new File("src/main/resources/reports/report.jasper");
        JasperPrint print = JasperFillManager.fillReport(file.getPath(), parameters, new JRBeanCollectionDataSource(callProcedureOrFunctionNative()));
        data = JasperExportManager.exportReportToPdf(print);
        return data;
    }
}
