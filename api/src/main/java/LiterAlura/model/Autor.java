package LiterAlura.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
        name = "autores",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "nombre")
        }
)
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false, unique = true)
    private String nombre;

    private Integer nacimiento;
    private Integer fallecimiento;

    public Autor(String name, Integer integer, Integer integer1) {
    }

    public Long getId() {
        return 0L;
    }

    public Integer getBirthYear() {
        return 0;
    }

    public Integer getDeathYear() {
        return 0;
    }

    // getters y setters
}
