package com.mitocode.fullstack.service.impl;

import com.mitocode.fullstack.model.Menu;
import com.mitocode.fullstack.repo.IGenericRepo;
import com.mitocode.fullstack.repo.IMenuRepo;
import com.mitocode.fullstack.service.ICRUD;
import com.mitocode.fullstack.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends CRUDImpl<Menu, Integer> implements IMenuService {
    private final IMenuRepo repo;
    @Override
    public IGenericRepo<Menu, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Menu> getMenusByUsername(String username) {
        //String contextSessionUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return repo.getMenusByUsername(username);
    }
}
