package LiterAlura.dto;

import LiterAlura.model.Autor;
import LiterAlura.model.Libro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LibroDTO(
        Long id,

        @NotBlank(message = "El título es obligatorio")
        String titulo,

        @NotBlank(message = "El idioma es obligatorio")
        String idioma,

        Integer descargas,

        @NotNull(message = "El autor es obligatorio")
        AutorDTO autor
) {

    public static LibroDTO fromEntity(Libro libro) {
        return new LibroDTO(
                libro.getId(),
                libro.getTitulo(),
                libro.getIdioma(),
                libro.getDescargas(),
                AutorDTO.fromEntity(libro.getAutor())
        );
    }

    public Libro toEntity() {
        Autor autorEntity = autor.toEntity();
        return new Libro(titulo, idioma, descargas, autorEntity);
    }
}
