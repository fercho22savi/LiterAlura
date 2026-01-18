package LiterAlura.dto;

import LiterAlura.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AutorDTO {

    private Long id;

    @NotBlank(message = "El nombre del autor es obligatorio")
    private String nombre;

    private Integer birthYear;
    private Integer deathYear;

    // ------------------- Constructor -------------------
    public AutorDTO() {
    }

    public AutorDTO(Long id, String nombre, Integer birthYear, Integer deathYear) {
        this.id = id;
        this.nombre = nombre;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    // ------------------- Getters y Setters -------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    // ------------------- Métodos de conversión -------------------

    /**
     * Convierte entidad Autor a DTO
     */
    public static @NotNull AutorDTO fromEntity(Autor autor) {
        if (autor == null) return null;
        return new AutorDTO(
                autor.getId(),
                autor.getNombre(),
                autor.getBirthYear(),
                autor.getDeathYear()
        );
    }

    /**
     * Convierte DTO a entidad Autor
     */
    public Autor toEntity() {
        return new Autor(
                this.nombre,
                this.birthYear,
                this.deathYear
        );
    }
}
