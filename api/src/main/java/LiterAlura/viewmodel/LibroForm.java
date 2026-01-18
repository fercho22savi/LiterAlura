package LiterAlura.viewmodel;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LibroForm {

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @NotBlank(message = "El idioma es obligatorio")
    private String idioma;

    private Integer descargas; // ⚠ coincide con Libro.descargas

    @NotBlank(message = "El nombre del autor es obligatorio")
    private String autorNombre;

    private Integer autorBirthYear;
    private Integer autorDeathYear;

    // Constructor vacío obligatorio para Thymeleaf
    public LibroForm() {}

    // Constructor con parámetros para inicializar al editar
    public LibroForm(String titulo, String idioma, String autorNombre, Integer descargas, Integer autorBirthYear, Integer autorDeathYear) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.autorNombre = autorNombre;
        this.descargas = descargas;
        this.autorBirthYear = autorBirthYear;
        this.autorDeathYear = autorDeathYear;
    }

    // Constructor simplificado (opcional) solo con lo mínimo
    public LibroForm(String titulo, String idioma, String autorNombre) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.autorNombre = autorNombre;
    }
}
