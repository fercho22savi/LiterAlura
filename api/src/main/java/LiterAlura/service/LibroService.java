package LiterAlura.service;

import LiterAlura.model.Autor;
import LiterAlura.model.Libro;
import LiterAlura.repository.AutorRepository;
import LiterAlura.repository.LibroRepository;
import LiterAlura.viewmodel.LibroForm;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    @Getter
    private final AutorRepository autorRepository;

    public LibroService(LibroRepository libroRepository,
                        AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    // =========================
    // CRUD BÁSICO
    // =========================

    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Libro> buscarPorIdioma(String idioma) {
        return libroRepository.findByIdiomaIgnoreCase(idioma);
    }

    public Libro obtenerPorId(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con id: " + id));
    }

    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }

    // =========================
    // AUTORES VIVOS EN UN AÑO
    // =========================
    public List<Autor> autoresVivosEn(int year) {
        return autorRepository.findAutoresVivosEn(year);
    }

    // =========================
    // GUARDAR DESDE FORMULARIO (THYMELEAF)
    // =========================
    public void guardarLibroDesdeForm(LibroForm libroForm) {

        // 1️⃣ Buscar autor por nombre
        Optional<Autor> autorExistente =
                autorRepository.findByNombreIgnoreCase(libroForm.getAutorNombre());

        Autor autor = autorExistente.orElseGet(() -> {
            Autor nuevoAutor = new Autor(
                    libroForm.getAutorNombre(),
                    libroForm.getAutorBirthYear(),
                    libroForm.getAutorDeathYear()
            );
            return autorRepository.save(nuevoAutor);
        });

        // 2️⃣ Evitar libros duplicados
        if (libroRepository.findByTitulo(libroForm.getTitulo()).isPresent()) {
            return; // libro ya existe → no se guarda
        }

        // 3️⃣ Crear libro
        Libro libro = new Libro(
                libroForm.getTitulo(),
                libroForm.getIdioma(),
                libroForm.getDescargas(),
                autor
        );

        // 4️⃣ Guardar libro
        libroRepository.save(libro);
    }

    // =========================
    // ACTUALIZAR LIBRO DESDE FORMULARIO
    // =========================
    public void actualizarLibro(Long id, LibroForm libroForm) {
        Libro libro = libroRepository.findById(id).orElse(null);
        if (libro == null) return;

        libro.setTitulo(libroForm.getTitulo());
        libro.setIdioma(libroForm.getIdioma());
        libro.setDescargas(libroForm.getDescargas());

        // Actualizar autor o crear nuevo
        Optional<Autor> autorExistente = autorRepository.findByNombreIgnoreCase(libroForm.getAutorNombre());
        Autor autor = autorExistente.orElseGet(() -> {
            Autor nuevoAutor = new Autor(
                    libroForm.getAutorNombre(),
                    libroForm.getAutorBirthYear(),
                    libroForm.getAutorDeathYear()
            );
            return autorRepository.save(nuevoAutor);
        });

        libro.setAutor(autor);
        libroRepository.save(libro);
    }

    // =========================
    // CRUD COMPLETO CON ENTIDAD PARA EDITAR DIRECTAMENTE
    // =========================

    public Libro actualizarLibro(Long id, Libro libroActualizado) {
        Libro libro = obtenerPorId(id);
        libro.setTitulo(libroActualizado.getTitulo());
        libro.setIdioma(libroActualizado.getIdioma());
        libro.setDescargas(libroActualizado.getDescargas());

        if (libroActualizado.getAutor() != null) {
            libro.setAutor(libroActualizado.getAutor());
        }

        return libroRepository.save(libro);
    }

    public Libro buscarPorId(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));
    }

}
