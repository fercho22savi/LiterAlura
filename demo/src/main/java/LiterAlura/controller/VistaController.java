package LiterAlura.controller;

import LiterAlura.service.LibroService;
import LiterAlura.viewmodel.LibroForm;
import org.jetbrains.annotations.Contract;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VistaController {

    private final LibroService libroService;

    // Inyección de dependencia de LibroService
    @Contract(pure = true)
    public VistaController(LibroService libroService) {
        this.libroService = libroService;
    }

    // Página principal
    @GetMapping("/")
    public String index() {
        return "index"; // renderiza index.html
    }

    // Listado de libros
    @GetMapping("/libros")
    public String libros(@org.jetbrains.annotations.NotNull Model model) {
        // Agrega al modelo la lista de libros desde la base de datos
        model.addAttribute("libros", libroService.listarLibros());
        return "libros"; // renderiza libros.html
    }

    // Formulario para registrar un libro
    @GetMapping("/libros/registrar")
    public String registrarLibroForm(Model model) {
        // Creamos un objeto LibroForm vacío para el formulario
        model.addAttribute("libro", new LibroForm());
        return "registrarlibros"; // renderiza registrarlibros.html
    }

    // Procesa el formulario de registro de libro
    @PostMapping("/libros/registrar")
    public String registrarLibroSubmit(@ModelAttribute LibroForm libroForm) {
        // Convierte LibroForm en entidad y lo guarda en la base de datos
        libroService.guardarLibroDesdeForm(libroForm);
        // Redirige a la lista de libros actualizada
        return "redirect:/libros";
    }
}
