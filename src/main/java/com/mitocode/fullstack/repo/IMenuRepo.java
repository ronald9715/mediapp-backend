package com.mitocode.fullstack.repo;

import com.mitocode.fullstack.model.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMenuRepo extends IGenericRepo<Menu, Integer>{

    @Query(value = """
            select m.* from menu_rol mr
            inner join user_rol ur on ur.id_rol = mr.id_rol
            inner join menu m on m.id_menu = mr.id_menu
            inner join user_data u on u.id_user = ur.id_user
            where u.username =:username
""", nativeQuery = true)
    List<Menu> getMenusByUsername(@Param("username") String username);
}
