package com.mitocode.fullstack.controller;

import com.mitocode.fullstack.dto.MedicDTO;
import com.mitocode.fullstack.model.Medic;
import com.mitocode.fullstack.service.IMedicService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medics")
@RequiredArgsConstructor

public class MedicController {

    private final IMedicService service;
    @Qualifier("DTOToMedicMapper")
    private final ModelMapper mapper;

    //@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<MedicDTO>> readAll() throws Exception{
        List<MedicDTO> list = service.readAll().stream().map(e->mapper.map(e, MedicDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Medic obj = service.readById(id);
        return new ResponseEntity<>(mapper.map(obj, MedicDTO.class) , HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<MedicDTO> create(@RequestBody MedicDTO medic) throws Exception{
        Medic obj = service.create(mapper.map(medic, Medic.class) );
        return new ResponseEntity<>(mapper.map(obj,MedicDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicDTO> update(@PathVariable("id") Integer id,@RequestBody MedicDTO medic) throws Exception{
        medic.setIdMedic(id);
        Medic obj2 = service.update(id,mapper.map(medic, Medic.class) );
        return new ResponseEntity<>(mapper.map(obj2, MedicDTO.class) , HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception{
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
