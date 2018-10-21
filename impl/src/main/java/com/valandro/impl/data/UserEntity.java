package com.valandro.impl.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "usuarios")
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "id_client")
    private Integer clientId;
    @Column(name = "access_level")
    private Integer accessLevel;
    private String name;
    private String password;
    private Integer blocked;
}
