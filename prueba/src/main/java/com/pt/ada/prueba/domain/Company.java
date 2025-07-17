package com.pt.ada.prueba.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "company", schema = "adasas")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_company")
    private Long id;

    @Column(name = "codigo_company", nullable = false, length = 50)
    private String codigoCompany;

    @Column(name = "name_company", nullable = false)
    private String nameCompany;

    @Lob
    @Column(name = "description_company")
    private String descriptionCompany;

}