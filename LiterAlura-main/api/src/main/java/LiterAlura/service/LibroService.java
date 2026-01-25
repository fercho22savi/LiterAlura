package LiterAlura.service;

import LiterAlura.model.Autor;
import LiterAlura.model.Libro;
import LiterAlura.repository.AutorRepository;
import LiterAlura.repository.LibroRepository;
import LiterAlura.viewmodel.LibroForm;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public LibroService(LibroRepository libroRepository,
                        AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    // ============================
    // CREAR / EDITAR
    // ============================
    @Transactional
    public void guardarLibroDesdeForm(LibroForm form) {

        Autor autor = null;

        // Buscar autor por ID
        if (form.getAutorId() != null) {

            autor = autorRepository.findById(form.getAutorId())
                    .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        }
        // Buscar o crear por nombre
        else if (form.getAutorNombre() != null && !form.getAutorNombre().isBlank()) {

            autor = autorRepository.findByNombreIgnoreCase(form.getAutorNombre())
                    .orElseGet(() -> {

                        Autor nuevoAutor = new Autor(
                                form.getAutorNombre(),
                                form.getAutorBirthYear(),
                                form.getAutorDeathYear()
                        );

                        return autorRepository.save(nuevoAutor);
                    });
        }

        // Buscar libro existente o crear nuevo
        Libro libro = (form.getId() != null)
                ? libroRepository.findById(form.getId()).orElse(new Libro())
                : new Libro();

        libro.setTitulo(form.getTitulo());
        libro.setIdioma(form.getIdioma());
        libro.setDescargas(form.getDescargas());
        libro.setAutor(autor);

        libroRepository.save(libro);
    }

    // ============================
    // LISTAR TODOS
    // ============================
    public List<Libro> listarTodos() {
        return libroRepository.findAll();
    }

    // ============================
    // LISTAR (ALIAS)
    // ============================
    public List<Libro> listarLibros() {
        return listarTodos();
    }

    // ============================
    // OBTENER PARA EDITAR
    // ============================
    @Transactional
    public LibroForm obtenerLibroForm(Long id) {

        Libro libro = libroRepository.findByIdWithAutor(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        LibroForm form = new LibroForm();

        form.setId(libro.getId());
        form.setTitulo(libro.getTitulo());
        form.setIdioma(libro.getIdioma());
        form.setDescargas(libro.getDescargas());

        if (libro.getAutor() != null) {

            Autor autor = libro.getAutor();

            form.setAutorId(autor.getId());
            form.setAutorNombre(autor.getNombre());
            form.setAutorBirthYear(autor.getFechaNacimiento());
            form.setAutorDeathYear(autor.getFechaMuerte());
        }

        return form;
    }

    // ============================
    // BUSCAR POR TÍTULO
    // ============================
    public List<Libro> buscarPorTitulo(String titulo) {

        if (titulo == null || titulo.isBlank()) {
            return listarTodos();
        }

        return libroRepository
                .findByTituloContainingIgnoreCase(titulo);
    }

    // ============================
    // ACTUALIZAR
    // ============================
    @Transactional
    public void actualizar(Long id, @Valid LibroForm libroForm) {

        if (!libroRepository.existsById(id)) {
            throw new RuntimeException("Libro no existe");
        }

        libroForm.setId(id);

        guardarLibroDesdeForm(libroForm);
    }

    // ============================
    // ELIMINAR
    // ============================
    @Transactional
    public void eliminarLibro(Long id) {

        if (!libroRepository.existsById(id)) {
            throw new RuntimeException("Libro no encontrado");
        }

        libroRepository.deleteById(id);
    }

    public void eliminar(Long id) {
    }
}
