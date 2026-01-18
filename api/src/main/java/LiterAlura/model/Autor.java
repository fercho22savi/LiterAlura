package LiterAlura.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
        name = "autores",
        uniqueConstraints = @UniqueConstraint(columnNames = "nombre"),
        indexes = @Index(name = "idx_autor_nombre", columnList = "nombre")
)
@Getter
@Setter
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false, unique = true)
    private String nombre;

    @Positive(message = "El año de nacimiento debe ser positivo")
    private Integer birthYear;

    @Positive(message = "El año de fallecimiento debe ser positivo")
    private Integer deathYear;

    // Constructor JPA
    public Autor() {}

    // Constructor de uso normal
    public Autor(String nombre, Integer birthYear, Integer deathYear) {
        this.nombre = nombre;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
