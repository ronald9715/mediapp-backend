package com.mitocode.fullstack.controller;

import com.mitocode.fullstack.dto.ExamDTO;
import com.mitocode.fullstack.dto.MenuDTO;
import com.mitocode.fullstack.model.Exam;
import com.mitocode.fullstack.model.Menu;
import com.mitocode.fullstack.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuController {
    private final IMenuService service;
    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

    @PostMapping("/user")
    public ResponseEntity<List<MenuDTO>> getMenusByUser(@RequestBody String username) throws Exception{
        List<Menu> menus = service.getMenusByUsername(username);
        List<MenuDTO> menuDTOS = menus.stream().map(
                menu -> {
                    menu.setRoles(new ArrayList<>());
                    return mapper.map(menu, MenuDTO.class);
                }
        ).collect(Collectors.toList());

        return new ResponseEntity<>(menuDTOS, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<MenuDTO>> readAll() throws Exception{
        List<MenuDTO> list = service.readAll().stream().map(e-> mapper.map(e, MenuDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Menu obj = service.readById(id);
        return new ResponseEntity<>(mapper.map(obj, MenuDTO.class),HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<MenuDTO> create(@RequestBody MenuDTO dto) throws Exception{
        Menu menu = service.create(mapper.map(dto, Menu.class));
        return new ResponseEntity<>(mapper.map(menu, MenuDTO.class) , HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MenuDTO> update(@PathVariable("id") Integer id, @RequestBody MenuDTO dto) throws Exception{
        dto.setIdMenu(1);
        Menu menu = service.update(id,mapper.map(dto, Menu.class));
        return new ResponseEntity<>(mapper.map(menu, MenuDTO.class), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception{
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
