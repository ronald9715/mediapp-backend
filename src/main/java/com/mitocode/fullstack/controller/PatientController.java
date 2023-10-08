package com.mitocode.fullstack.controller;

import com.mitocode.fullstack.dto.PatientDTO;
import com.mitocode.fullstack.model.Patient;
import com.mitocode.fullstack.service.IPatientService;
import com.mitocode.fullstack.service.impl.PatientServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final IPatientService service;
    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<PatientDTO>>  readAll() throws Exception{

        List<PatientDTO> list = service.readAll().stream().map(e->mapper.map(e,PatientDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Patient patient = service.readById(id);
        return new ResponseEntity<>(mapper.map(patient,PatientDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PatientDTO> create(@Valid @RequestBody PatientDTO dto) throws Exception{
        Patient patient1 = service.create(mapper.map(dto, Patient.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(patient1.getIdPatient()).toUri();
        //return new ResponseEntity<>(patient1,HttpStatus.CREATED);
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> update(@PathVariable("id") Integer id,@Valid @RequestBody PatientDTO dto) throws Exception{
        dto.setIdPatient(id);
        Patient obj1 = service.update(id, mapper.map(dto, Patient.class));
        return new ResponseEntity<>(mapper.map(obj1,PatientDTO.class) , HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception{
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<PatientDTO> findByIdHateoas(@PathVariable("id") Integer id) throws Exception{
        EntityModel<PatientDTO> resource = EntityModel.of( mapper.map(service.readById(id), PatientDTO.class) );
        WebMvcLinkBuilder link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findByIdHateoas(id));
        resource.add(link1.withRel("patient-info"));
        return resource;
    }
    //Pageable

    @GetMapping("/pageable")
    public ResponseEntity<Page<PatientDTO>> listPage(Pageable pageable){
        Page<PatientDTO> page = service.listPage(pageable).map(el-> mapper.map(el, PatientDTO.class));
        return new ResponseEntity<>(page, HttpStatus.OK);
    }


}
