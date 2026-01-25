package LiterAlura.controller;

import LiterAlura.service.LibroService;
import LiterAlura.viewmodel.LibroForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vista")
public class VistaController {

    private final LibroService libroService;

    public VistaController(LibroService libroService) {
        this.libroService = libroService;
    }

    // LISTAR LIBROS (solo para vistas personalizadas)
    @GetMapping("/libros")
    public String listarLibros(Model model) {
        model.addAttribute("libros", libroService.listarLibros());
        return "libros";
    }

    // FORMULARIO REGISTRAR / EDITAR USANDO LibroForm
    @GetMapping("/libros/form")
    public String libroForm(@RequestParam(required = false) Long id, Model model) {
        LibroForm libroForm = (id != null) ? (LibroForm) libroService.obtenerLibroForm(id) : new LibroForm();
        model.addAttribute("libro", libroForm);
        model.addAttribute("accion", (id != null) ? "Editar" : "Registrar");
        return "registrar-libro";
    }

    // ELIMINAR LIBRO (vista personalizada)
    @GetMapping("/libros/eliminar")
    public String eliminarLibro(@RequestParam Long id) {
        libroService.eliminarLibro(id);
        return "redirect:/vista/libros";
    }

    // BUSCAR LIBRO POR TÍTULO
    @GetMapping("/libros/buscar")
    public String buscarLibro(@RequestParam String titulo, Model model) {
        model.addAttribute("libros", libroService.buscarPorTitulo(titulo));
        return "libros";
    }
}
