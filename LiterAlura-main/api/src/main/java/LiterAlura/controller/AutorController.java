package LiterAlura.controller;

import LiterAlura.model.Autor;
import LiterAlura.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    // LISTAR AUTORES
    @GetMapping
    public String listarAutores(Model model) {
        model.addAttribute("autores", autorService.listarTodos());
        return "autores";
    }

    // FORMULARIO REGISTRAR / EDITAR AUTOR
    @GetMapping({"/registrar", "/editar/{id}"})
    public String autorForm(@PathVariable(required = false) Long id, Model model) {
        Autor autor = (id != null) ? autorService.buscarPorId(id) : new Autor();
        model.addAttribute("autor", autor);
        model.addAttribute("accion", (id != null) ? "Editar" : "Registrar");
        return "registrar-autor";
    }

    // GUARDAR O ACTUALIZAR AUTOR
    @PostMapping("/guardar")
    public String guardarAutor(@Valid @ModelAttribute("autor") Autor autor,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("accion", autor.getId() != null ? "Editar" : "Registrar");
            return "registrar-autor";
        }
        autorService.guardar(autor);
        return "redirect:/autores";
    }

    // ELIMINAR AUTOR
    @GetMapping("/eliminar/{id}")
    public String eliminarAutor(@PathVariable Long id) {
        autorService.eliminar(id);
        return "redirect:/autores";
    }
}
