package com.mitocode.fullstack.controller;

import com.mitocode.fullstack.dto.ConsultExamDTO;
import com.mitocode.fullstack.model.ConsultExam;
import com.mitocode.fullstack.service.IConsultExamService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/consultexams")
@RequiredArgsConstructor

public class ConsultExamController {
    @Qualifier("defaultMapper")
    private final ModelMapper mapper;
    private final IConsultExamService service;


    @GetMapping("/{idConsult}")
    public ResponseEntity<List<ConsultExam>> getConsultsById(@PathVariable("idConsult") Integer idConsult){
        List<ConsultExam> list = service.getExamsByConsultId(idConsult);//.stream().map(e->mapper.map(ConsultExam.class, ConsultExamDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
