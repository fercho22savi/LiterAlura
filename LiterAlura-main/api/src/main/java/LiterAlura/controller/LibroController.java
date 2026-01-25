package LiterAlura.controller;

import LiterAlura.model.Libro;
import LiterAlura.service.LibroService;
import LiterAlura.viewmodel.LibroForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    // LISTAR TODOS LOS LIBROS
    @GetMapping
    public String listarLibros(Model model) {
        model.addAttribute("libros", libroService.listarTodos());
        return "libros";
    }

    // Mostrar formulario para registrar nuevo
    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("libro", new LibroForm());
        model.addAttribute("accion", "Registrar");
        return "registrar-libro";
    }

    // Mostrar formulario para editar existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        model.addAttribute("libro", libroService.obtenerLibroForm(id));
        model.addAttribute("accion", "Editar");
        return "registrar-libro";
    }


    // GUARDAR O ACTUALIZAR LIBRO
    @PostMapping("/guardar")
    public String guardarLibro(@Valid @ModelAttribute("libro") LibroForm libroForm,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("accion", libroForm.getId() != null ? "Editar" : "Registrar");
            return "registrar-libro";
        }

        if (libroForm.getId() != null) {
            // Actualizar libro existente
            libroService.actualizar(libroForm.getId(), libroForm);
        } else {
            // Guardar nuevo libro
            libroService.guardarLibroDesdeForm(libroForm);
        }

        return "redirect:/libros";
    }

    // ELIMINAR LIBRO
    @GetMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable Long id) {
        libroService.eliminar(id);
        return "redirect:/libros";
    }
}
