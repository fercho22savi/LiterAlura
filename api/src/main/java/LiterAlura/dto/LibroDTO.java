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
        int downloadCount,
        @NotNull(message = "El autor es obligatorio")
        AutorDTO autor
) {

    // Convierte entidad a DTO
    public static LibroDTO fromEntity(Libro libro) {
        return new LibroDTO(
                libro.getId(),
                libro.getTitulo(),
                libro.getIdioma(),
                libro.getDownloadCount(),
                AutorDTO.fromEntity(libro.getAutor())
        );
    }

    // Convierte DTO a entidad
    public Libro toEntity() {
        Autor autorEntity = autor.toEntity();
        return new Libro(titulo, idioma, downloadCount, autorEntity);
    }
}
