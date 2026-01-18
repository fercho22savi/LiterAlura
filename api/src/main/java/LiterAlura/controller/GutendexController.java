package LiterAlura.controller;

import LiterAlura.dto.GutendexResponseDTO;
import LiterAlura.service.GutendexService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Gutendex", description = "Consumo de la API Gutendex")
public class GutendexController {

    private final GutendexService service;

    public GutendexController(GutendexService service) {
        this.service = service;
    }

    @Operation(summary = "Obtener libros desde Gutendex con filtros opcionales")
    @GetMapping("/books")
    public GutendexResponseDTO obtenerLibros(
            @RequestParam(required = false) Integer author_year_start,
            @RequestParam(required = false) Integer author_year_end,
            @RequestParam(required = false) String languages,     // ej: "en,fr"
            @RequestParam(required = false) Boolean copyright,    // true o false
            @RequestParam(required = false) String ids,           // ej: "11,12,13"
            @RequestParam(required = false) String search         // ej: "dickens great"
    ) {
        return service.obtenerLibrosConFiltros(
                author_year_start,
                author_year_end,
                languages,
                copyright,
                ids,
                search
        );
    }
}
