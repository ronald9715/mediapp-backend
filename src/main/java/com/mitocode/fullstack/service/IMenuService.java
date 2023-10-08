package com.mitocode.fullstack.service;

import com.mitocode.fullstack.model.Menu;

import java.util.List;

public interface IMenuService extends ICRUD<Menu, Integer>{

    List<Menu> getMenusByUsername(String username);
}
