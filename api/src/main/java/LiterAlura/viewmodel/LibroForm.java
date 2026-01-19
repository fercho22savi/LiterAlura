package LiterAlura.viewmodel;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LibroForm {

    // ===== LIBRO =====
    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @NotBlank(message = "El idioma es obligatorio")
    private String idioma;

    private Integer descargas;

    // ===== AUTOR =====
    @NotBlank(message = "El nombre del autor es obligatorio")
    private String autorNombre;

    private Integer autorBirthYear;
    private Integer autorDeathYear;

    // Constructor vacío (OBLIGATORIO para Thymeleaf)
    public LibroForm() {}

    // Constructor para edición
    public LibroForm(String titulo, String idioma, Integer descargas,
                     String autorNombre, Integer autorBirthYear, Integer autorDeathYear) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.descargas = descargas;
        this.autorNombre = autorNombre;
        this.autorBirthYear = autorBirthYear;
        this.autorDeathYear = autorDeathYear;
    }
}
