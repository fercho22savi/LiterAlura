package LiterAlura.controller;

import LiterAlura.service.LibroService;
import LiterAlura.viewmodel.LibroForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VistaController {

    private final LibroService libroService;

    // Inyección de dependencia
    public VistaController(LibroService libroService) {
        this.libroService = libroService;
    }

    // Página principal
    @GetMapping("/")
    public String index() {
        return "index"; // templates/index.html
    }

    // Listado de libros
    @GetMapping("/libros")
    public String libros(Model model) {
        model.addAttribute("libros", libroService.listarLibros());
        return "libros"; // templates/libros.html
    }

    // Formulario para registrar libro
    @GetMapping("/libros/registrar")
    public String registrarLibroForm(Model model) {
        model.addAttribute("libro", new LibroForm());
        return "registrar-libro"; // ✅ templates/registrar-libro.html
    }

    // Procesar formulario
    @PostMapping("/libros/registrar")
    public String registrarLibroSubmit(@ModelAttribute("libro") LibroForm libroForm) {
        libroService.guardarLibroDesdeForm(libroForm);
        return "redirect:/libros";
    }
}
