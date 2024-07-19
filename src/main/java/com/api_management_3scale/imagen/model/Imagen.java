package com.api_management_3scale.imagen.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "imagen")
public class Imagen {
    @Id
    @JsonProperty("id")
    @Column(name = "id")
    private Long id;
    @JsonProperty("nombre")
    @Column(name = "nombre")
    private String nombre;
    @JsonProperty("base64")
    @Column(name = "base64")
    private String base64;
    @JsonProperty("idTraspaso")
    @Column(name = "idTraspaso")
    private String idTraspaso;
}
