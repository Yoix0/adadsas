package com.pt.ada.prueba.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "application", schema = "adasas")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_id", nullable = false)
    private Integer id;

    @Column(name = "app_code", nullable = false, length = 50)
    private String appCode;

    @Column(name = "app_name", nullable = false)
    private String appName;

    @Lob
    @Column(name = "app_description")
    private String appDescription;

}