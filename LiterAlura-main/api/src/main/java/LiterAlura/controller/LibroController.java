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

    // FORMULARIO REGISTRAR / EDITAR LIBRO
    @GetMapping({"/registrar", "/editar/{id}"})
    public String libroForm(@PathVariable(required = false) Long id, Model model) {
        LibroForm libroForm = (id != null) ?
                libroService.obtenerLibroForm(id) :
                new LibroForm();
        model.addAttribute("libro", libroForm);
        model.addAttribute("accion", (id != null) ? "Editar" : "Registrar");
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
