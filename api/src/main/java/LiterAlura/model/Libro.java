package LiterAlura.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "libros", uniqueConstraints = {
        @UniqueConstraint(columnNames = "titulo")
})

public class Libro {
    //Atributos de la clase libro
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String idioma;

    private Integer descargas; // puede ser null

    @ManyToOne
    private Autor autor;

    // Constructor vacío obligatorio para JPA
    public Libro() {

    }

    // Constructor con todos los campos
    public Libro(String titulo, String idioma, Integer descargas, Autor autor) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.descargas = descargas;
        this.autor = autor;
    }

    /**
     * Método especial para obtener descargas como int.
     * Retorna 0 si descargas es null
     */
    public int getDownloadCount() {
        return (descargas != null) ? descargas : 0;
    }
}
