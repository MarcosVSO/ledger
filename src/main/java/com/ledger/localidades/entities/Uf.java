package com.ledger.localidades.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ufs")
public class Uf {
    @Id
    private Integer id;

    @Column(name = "sigla")
    private String sigla;

    @Column(name = "nome")
    private String nome;

    @OneToMany(mappedBy = "uf", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Municipio> municipios = new ArrayList<>();

}
