package LiterAlura.controller;

import LiterAlura.model.Libro;
import LiterAlura.service.LibroService;
import LiterAlura.viewmodel.LibroForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/libros") // Unificamos todas las rutas bajo /libros
public class VistaController {

    private final LibroService libroService;

    public VistaController(LibroService libroService) {
        this.libroService = libroService;
    }

    // ===============================
    // Página principal
    // ===============================
    @GetMapping("/")
    public String index() {
        return "index"; // templates/index.html
    }

    // ===============================
    // Listado de libros
    // ===============================
    @GetMapping
    public String listarLibros(Model model) {
        model.addAttribute("libros", libroService.listarLibros());
        return "libros"; // templates/libros.html
    }

    // ===============================
    // Formulario Registrar Libro
    // ===============================
    @GetMapping("/registrar")
    public String registrarLibroForm(Model model) {
        model.addAttribute("libro", new LibroForm());
        model.addAttribute("accion", "Registrar");
        return "form-libro"; // templates/form-libro.html
    }

    @PostMapping("/registrar")
    public String registrarLibroSubmit(@ModelAttribute LibroForm libroForm) {
        libroService.guardarLibroDesdeForm(libroForm);
        return "redirect:/libros";
    }

    // ===============================
    // Formulario Editar Libro
    // ===============================
    @GetMapping("/editar/{id}")
    public String editarLibroForm(@PathVariable Long id, Model model) {
        Libro libro = libroService.obtenerPorId(id);

        LibroForm libroForm = new LibroForm(
                libro.getTitulo(),
                libro.getIdioma(),
                libro.getAutor() != null ? libro.getAutor().getNombre() : "",
                libro.getDescargas(),
                libro.getAutor() != null ? libro.getAutor().getBirthYear() : null,
                libro.getAutor() != null ? libro.getAutor().getDeathYear() : null
        );

        model.addAttribute("libroId", id); // necesario para el th:action dinámico
        model.addAttribute("libro", libroForm);
        model.addAttribute("accion", "Editar");

        return "registrar-libro"; // mismo HTML que registrar
    }

    @PostMapping("/editar/{id}")
    public String editarLibroSubmit(@PathVariable Long id, @ModelAttribute LibroForm libroForm) {
        libroService.actualizarLibro(id, libroForm);
        return "redirect:/libros";
    }

    // ===============================
    // Eliminar Libro
    // ===============================
    @GetMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
        return "redirect:/libros";
    }
}
