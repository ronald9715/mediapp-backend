package com.mitocode.fullstack.repo;

import com.mitocode.fullstack.model.User;

public interface IUserRepo extends IGenericRepo<User, Integer>{
    //Query Derivado
    User findOneByUsername(String username);
}
