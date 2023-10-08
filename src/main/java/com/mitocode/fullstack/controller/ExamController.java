package com.mitocode.fullstack.controller;

import com.mitocode.fullstack.dto.ExamDTO;
import com.mitocode.fullstack.model.Exam;
import com.mitocode.fullstack.service.IExamService;
import com.mitocode.fullstack.service.IExamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/exams")
@RequiredArgsConstructor

public class ExamController {
    private final IExamService service;
    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ExamDTO>> readAll() throws Exception{

        List<ExamDTO> list = service.readAll().stream().map(e->mapper.map(e,ExamDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ExamDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Exam exam = service.readById(id);
        return new ResponseEntity<>(mapper.map(exam,ExamDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ExamDTO> create(@Valid @RequestBody ExamDTO dto) throws Exception{
        Exam exam1 = service.create(mapper.map(dto, Exam.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(exam1.getIdExam()).toUri();
        //return new ResponseEntity<>(exam1,HttpStatus.CREATED);
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ExamDTO> update(@PathVariable("id") Integer id,@Valid @RequestBody ExamDTO dto) throws Exception{
        dto.setIdExam(id);
        Exam obj1 = service.update(id, mapper.map(dto, Exam.class));
        return new ResponseEntity<>(mapper.map(obj1,ExamDTO.class) , HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception{
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
