package com.envisionnepal.gunasho.file;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VariableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long variableId ;

    private String ProfImg1;

    private String ProfImg2;

    private String ProfImg3;

    private String BannerImg1;

    private String BannerImg2;

    private String BannerImg3;

}