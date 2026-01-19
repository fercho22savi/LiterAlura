package LiterAlura.viewmodel;

import LiterAlura.model.Libro;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class LibroForm {

    private Long id;

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @NotBlank(message = "El idioma es obligatorio")
    private String idioma;

    private Integer descargas;

    // Para crear un autor nuevo
    @NotBlank(message = "El nombre del autor es obligatorio")
    private String autorNombre;

    private Integer autorBirthYear;
    private Integer autorDeathYear;

    // Getter y Setter de autorId (ya generados por Lombok, opcionales si quieres lógica extra)
    // Para seleccionar un autor existente
    private Long autorId;

    /**
     * Convierte un objeto Libro a LibroForm para llenar formularios de edición.
     */
    public static LibroForm fromLibro(Libro libro) {
        LibroForm form = new LibroForm();
        form.setId(libro.getId());
        form.setTitulo(libro.getTitulo());
        form.setIdioma(libro.getIdioma());
        form.setDescargas(libro.getDescargas());

        if (libro.getAutor() != null) {
            form.setAutorId(libro.getAutor().getId());
            form.setAutorNombre(libro.getAutor().getNombre());
            form.setAutorBirthYear(libro.getAutor().getBirthYear());
            form.setAutorDeathYear(libro.getAutor().getDeathYear());
        }

        return form;
    }

}
