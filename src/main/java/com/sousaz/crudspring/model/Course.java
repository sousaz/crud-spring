package com.sousaz.crudspring.model;


import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity // entidade que faz mapeamento no banco de dados
@SQLDelete(sql = "UPDATE Course SET status = 'inactive' WHERE id = ?") //soft delete
@SQLRestriction("status = 'active'") // retorna apenas cursos ativos
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id") // nome que quero usar
    private Long id;

    @NotBlank // não pode ter o conteudo em branco
    @NotNull
    @Size(min = 5, max = 100)
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull
    @Size(max = 10)
    @Pattern(regexp = "back-end|front-end")
    @Column(length = 10, nullable = false)
    private String category;

    @NotNull
    @Size(max = 10)
    @Pattern(regexp = "active|inactive")
    @Column(length = 10, nullable = false)
    private String status = "active";
}
