package LiterAlura.service;

import LiterAlura.model.Autor;
import LiterAlura.model.Libro;
import LiterAlura.repository.AutorRepository;
import LiterAlura.repository.LibroRepository;
import LiterAlura.viewmodel.LibroForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    /* ===============================
       LISTAR LIBROS CON AUTORES
       =============================== */
    public List<Libro> listarTodos() {
        // Carga los libros junto con su autor para evitar LazyInitializationException
        return libroRepository.findAllWithAutor();
    }

    public List<Libro> listarLibros() {
        return listarTodos();
    }

    /* ===============================
       BUSCAR POR ID
       =============================== */
    public Optional<Libro> buscarPorId(Long id) {
        return libroRepository.findById(id);
    }

    public Optional<Libro> obtenerLibro(Long id) {
        return buscarPorId(id);
    }

    /* ===============================
       GUARDAR / ACTUALIZAR LIBRO
       =============================== */
    public Libro guardar(Libro libro) {
        return libroRepository.save(libro);
    }

    public void guardarLibro(Libro libro) {
        guardar(libro);
    }

    public void guardarLibroDesdeForm(LibroForm form) {
        Autor autor = null;

        if (form.getAutorId() != null) {
            autor = autorRepository.findById(form.getAutorId()).orElse(null);
        } else if (form.getAutorNombre() != null && !form.getAutorNombre().isBlank()) {
            autor = new Autor(form.getAutorNombre(), form.getAutorBirthYear(), form.getAutorDeathYear());
            autorRepository.save(autor);
        }

        Libro libro = new Libro(
                form.getTitulo(),
                form.getIdioma(),
                form.getDescargas(),
                autor
        );

        guardar(libro);
    }

    public Libro actualizar(Long id, Libro libro) {
        libro.setId(id);
        return guardar(libro);
    }

    public Libro actualizar(Long id, LibroForm form) {
        Libro libro = buscarPorId(id).orElse(new Libro());
        libro.setTitulo(form.getTitulo());
        libro.setIdioma(form.getIdioma());
        libro.setDescargas(form.getDescargas());

        Autor autor = null;
        if (form.getAutorId() != null) {
            autor = autorRepository.findById(form.getAutorId()).orElse(null);
        } else if (form.getAutorNombre() != null && !form.getAutorNombre().isBlank()) {
            autor = new Autor(form.getAutorNombre(), form.getAutorBirthYear(), form.getAutorDeathYear());
            autorRepository.save(autor);
        }
        libro.setAutor(autor);

        return guardar(libro);
    }

    /* ===============================
       ELIMINAR LIBRO
       =============================== */
    public void eliminar(Long id) {
        libroRepository.deleteById(id);
    }

    public void eliminarLibro(Long id) {
        eliminar(id);
    }

    /* ===============================
       BUSCAR POR TÍTULO
       =============================== */
    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    /* ===============================
       OBTENER LIBRO FORM
       =============================== */
    public LibroForm obtenerLibroForm(Long id) {
        Optional<Libro> optionalLibro = buscarPorId(id);
        if (optionalLibro.isEmpty()) return new LibroForm();

        Libro libro = optionalLibro.get();
        LibroForm form = new LibroForm();
        form.setId(libro.getId());
        form.setTitulo(libro.getTitulo());
        form.setIdioma(libro.getIdioma());
        form.setDescargas(libro.getDescargas());

        if (libro.getAutor() != null) {
            form.setAutorId(libro.getAutor().getId());
            form.setAutorNombre(libro.getAutor().getNombre());
            form.setAutorBirthYear(libro.getAutor().getBirthYear());
            form.setAutorDeathYear(libro.getAutor().getDeathYear());
        }

        return form;
    }
}
