package LiterAlura.dto;

import LiterAlura.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AutorDTO {

    // ------------------- Getters y Setters -------------------
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
