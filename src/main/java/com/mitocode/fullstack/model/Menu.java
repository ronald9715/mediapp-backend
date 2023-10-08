package com.mitocode.fullstack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Menu {
    @Id
    @EqualsAndHashCode.Include
    private Integer idMenu;
    @Column(length = 20, nullable = false)
    private String icon;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 60, nullable = false)
    private String url;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "menu_rol",
    joinColumns = @JoinColumn(name = "id_menu", referencedColumnName = "idMenu"),
            inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "idRol")
    )
    private List<Role> roles;
}
