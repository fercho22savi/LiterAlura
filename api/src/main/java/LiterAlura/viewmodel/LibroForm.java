package LiterAlura.viewmodel;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LibroForm {

    // Getters y Setters
    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @NotBlank(message = "El idioma es obligatorio")
    private String idioma;

    private int downloadCount;

    @NotBlank(message = "El nombre del autor es obligatorio")
    private String autorNombre;

    private Integer autorBirthYear;
    private Integer autorDeathYear;

    // Constructor vacío obligatorio para Thymeleaf
    public LibroForm() {

    }

}
