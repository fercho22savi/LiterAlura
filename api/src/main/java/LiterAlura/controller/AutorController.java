package LiterAlura.controller;

import LiterAlura.model.Autor;
import LiterAlura.repository.AutorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/autores")
public class AutorController {

    private final AutorRepository autorRepository;

    public AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    // ===============================
    // Listado de autores
    // ===============================
    @GetMapping
    public String listarAutores(Model model) {
        model.addAttribute("autores", autorRepository.findAll());
        return "autores"; // templates/autores.html
    }

    // ===============================
    // Formulario registrar autor
    // ===============================
    @GetMapping("/registrar")
    public String registrarAutorForm(Model model) {
        model.addAttribute("autor", new Autor());
        return "registrar-autor"; // templates/registrar-autor.html
    }

    // ===============================
    // Procesar formulario
    // ===============================
    @PostMapping("/registrar")
    public String registrarAutorSubmit(@ModelAttribute Autor autor) {
        autorRepository.save(autor);
        return "redirect:/autores";
    }
}
