package com.mitocode.fullstack.controller;

import com.mitocode.fullstack.dto.SpecialtyDTO;
import com.mitocode.fullstack.model.Specialty;
import com.mitocode.fullstack.service.ISpecialtyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/specialties")
@RequiredArgsConstructor
public class SpecialtyController {
    private final ISpecialtyService service;
    @Qualifier("defaultMapper")
    private final ModelMapper mapper;


    @GetMapping
    public ResponseEntity<List<SpecialtyDTO>> readAll() throws Exception{
        List<SpecialtyDTO> list = service.readAll().stream().map(e-> mapper.map(e, SpecialtyDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Specialty obj = service.readById(id);
        return new ResponseEntity<>(mapper.map(obj, SpecialtyDTO.class),HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<SpecialtyDTO> create(@RequestBody SpecialtyDTO dto) throws Exception{
        Specialty specialty = service.create(mapper.map(dto, Specialty.class));
        return new ResponseEntity<>(mapper.map(specialty, SpecialtyDTO.class) , HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> update(@PathVariable("id") Integer id, @RequestBody SpecialtyDTO dto) throws Exception{
        dto.setIdSpecialty(id);
        Specialty specialty = service.update(id,mapper.map(dto, Specialty.class));
        return new ResponseEntity<>(mapper.map(specialty, SpecialtyDTO.class), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception{
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
