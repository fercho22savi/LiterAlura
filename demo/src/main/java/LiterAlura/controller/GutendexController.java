package LiterAlura.controller;


import LiterAlura.dto.GutendexResponseDTO;
import LiterAlura.service.GutendexService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Gutendex", description = "Consumo de la API Gutendex")
public class GutendexController {

    private final GutendexService service;

    public GutendexController(GutendexService service) {
        this.service = service;
    }

    @Operation(summary = "Obtener libros desde Gutendex")
    @GetMapping("/api/gutendex")
    public GutendexResponseDTO obtenerLibros() {
        return service.obtenerLibros();
    }
}
