package LiterAlura.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @Column(name = "birth_year")
    private Integer birthYear;

    @Column(name = "death_year")
    private Integer deathYear;

    public Autor() {}

    public Autor(String nombre, Integer birthYear, Integer deathYear) {
        this.nombre = nombre;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public Integer getFechaNacimiento() {
        return birthYear;
    }

    public Integer getFechaMuerte() {
        return deathYear;
    }

}
