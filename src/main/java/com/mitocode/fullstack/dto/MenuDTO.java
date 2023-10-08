package com.mitocode.fullstack.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {
    @EqualsAndHashCode.Include
    private Integer idMenu;
    private String icon;
    private String name;
    private String url;
    private List<RoleDTO> roles;
}
