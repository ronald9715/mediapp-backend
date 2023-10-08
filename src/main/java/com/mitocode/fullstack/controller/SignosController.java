package com.mitocode.fullstack.controller;

import com.mitocode.fullstack.model.Signos;
import com.mitocode.fullstack.service.ISignosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/signos")
@RequiredArgsConstructor
public class SignosController {
    private final ISignosService service;

    @GetMapping
    public ResponseEntity<List<Signos>> findAll() throws Exception{
        List<Signos> list = service.readAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Signos> readById(@PathVariable Integer id) throws Exception{
        Signos signos = service.readById(id);
        return new ResponseEntity<>(signos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Signos> create(@RequestBody Signos signos) throws Exception{
        Signos signos1 = service.create(signos);
        return new ResponseEntity<>(signos1, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Signos> update(@PathVariable Integer id, @RequestBody Signos signos) throws Exception{
        signos.setIdSignos(id);
        Signos rpt = service.update(id, signos);
        return new ResponseEntity<>(rpt, HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception{
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
