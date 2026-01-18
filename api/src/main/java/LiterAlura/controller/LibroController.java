package LiterAlura.controller;

import LiterAlura.model.Libro;
import LiterAlura.model.Autor;
import LiterAlura.service.LibroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
@Tag(name = "Libros", description = "Gestión del catálogo de libros")
public class LibroController {

    private final LibroService service;

    public LibroController(LibroService service) {
        this.service = service;
    }

    @Operation(summary = "Registrar un libro en la base de datos")
    @PostMapping
    public Libro registrar(@RequestBody Libro libro) {
        return service.guardarLibro(libro);
    }

    @Operation(summary = "Listar todos los libros registrados")
    @GetMapping
    public List<Libro> listar() {
        return service.listarLibros();
    }

    @Operation(summary = "Buscar libros por título")
    @GetMapping("/buscar")
    public List<Libro> buscarPorTitulo(@RequestParam String titulo) {
        return service.buscarPorTitulo(titulo);
    }

    @Operation(summary = "Listar libros por idioma (switch)")
    @GetMapping("/idioma/{idioma}")
    public List<Libro> porIdioma(@PathVariable String idioma) {
        return service.buscarPorIdioma(idioma);
    }

    @Operation(summary = "Listar autores vivos en un año determinado")
    @GetMapping("/autores/vivos/{year}")
    public List<Autor> autoresVivos(@PathVariable int year) {
        return service.autoresVivosEn(year);
    }
}
