package LiterAlura.controller;

import LiterAlura.model.Libro;
import LiterAlura.model.Autor;
import LiterAlura.service.LibroService;
import LiterAlura.repository.AutorRepository;
import LiterAlura.viewmodel.LibroForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/libros") // mantenemos tu base para libros
public class VistaController {

    private final LibroService libroService;
    private final AutorRepository autorRepository;

    public VistaController(LibroService libroService, AutorRepository autorRepository) {
        this.libroService = libroService;
        this.autorRepository = autorRepository;
    }

    // ===============================
    // LIBROS (tu código intacto)
    // ===============================

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping
    public String listarLibros(Model model) {
        model.addAttribute("libros", libroService.listarLibros());
        return "libros";
    }

    @GetMapping("/registrar")
    public String registrarLibroForm(Model model) {
        model.addAttribute("libro", new LibroForm());
        model.addAttribute("accion", "Registrar");
        return "registrar-libro";
    }

    @PostMapping("/registrar")
    public String registrarLibroSubmit(@ModelAttribute LibroForm libroForm) {
        libroService.guardarLibroDesdeForm(libroForm);
        return "redirect:/libros";
    }

    @GetMapping("/editar/{id}")
    public String editarLibroForm(@PathVariable Long id, Model model) {
        Libro libro = libroService.obtenerPorId(id);

        LibroForm libroForm = new LibroForm(
                libro.getTitulo(),
                libro.getIdioma(),
                libro.getDescargas(),
                libro.getAutor() != null ? libro.getAutor().getNombre() : "",
                libro.getAutor() != null ? libro.getAutor().getBirthYear() : null,
                libro.getAutor() != null ? libro.getAutor().getDeathYear() : null
        );

        model.addAttribute("libroId", id);
        model.addAttribute("libro", libroForm);
        model.addAttribute("accion", "Editar");

        return "registrar-libro";
    }

    @PostMapping("/editar/{id}")
    public String editarLibroSubmit(@PathVariable Long id, @ModelAttribute LibroForm libroForm) {
        libroService.actualizarLibro(id, libroForm);
        return "redirect:/libros";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
        return "redirect:/libros";
    }

    // ===============================
    // AUTORES (nuevos métodos)
    // ===============================

    @GetMapping("/autores")
    public String listarAutores(Model model) {
        model.addAttribute("autores", autorRepository.findAll());
        return "autores";
    }

    @GetMapping("/autores/registrar")
    public String registrarAutorForm(Model model) {
        model.addAttribute("autor", new Autor());
        model.addAttribute("accion", "Registrar");
        return "registrar-autor";
    }

    @PostMapping("/autores/registrar")
    public String registrarAutorSubmit(@ModelAttribute Autor autor) {
        autorRepository.save(autor);
        return "redirect:/libros/autores";
    }

    @GetMapping("/autores/editar/{id}")
    public String editarAutorForm(@PathVariable Long id, Model model) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Autor no encontrado"));
        model.addAttribute("autor", autor);
        model.addAttribute("accion", "Editar");
        return "registrar-autor";
    }

    @PostMapping("/autores/editar/{id}")
    public String editarAutorSubmit(@PathVariable Long id, @ModelAttribute Autor autorForm) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Autor no encontrado"));

        autor.setNombre(autorForm.getNombre());
        autor.setBirthYear(autorForm.getBirthYear());
        autor.setDeathYear(autorForm.getDeathYear());

        autorRepository.save(autor);
        return "redirect:/libros/autores";
    }

    @GetMapping("/autores/eliminar/{id}")
    public String eliminarAutor(@PathVariable Long id) {
        autorRepository.deleteById(id);
        return "redirect:/libros/autores";
    }
}
