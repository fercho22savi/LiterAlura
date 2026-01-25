package LiterAlura.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "libros")
@Getter
@Setter
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String idioma;

    @Column(name = "descargas")
    private Integer descargas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;
    /*Constructor sin parametros*/
    public Libro() {

    }
    /*Constructor con parametros*/
    public Libro(String titulo, String idioma, Integer descargas, Autor autor) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.descargas = descargas;
        this.autor = autor;
    }
}
