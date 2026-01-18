package LiterAlura.service;

import LiterAlura.model.Autor;
import LiterAlura.model.Libro;
import LiterAlura.repository.AutorRepository;
import LiterAlura.repository.LibroRepository;
import LiterAlura.viewmodel.LibroForm;
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

    // Guardar libro
    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    // Listar todos los libros
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    // Buscar libros por título
    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    // Buscar libros por idioma
    public List<Libro> buscarPorIdioma(String idioma) {
        return libroRepository.findByIdiomaIgnoreCase(idioma);
    }

    // Autores vivos en un año específico
    public List<Autor> autoresVivosEn(int year) {
        return null;
    }

    public void guardarLibroDesdeForm(LibroForm libroForm) {
    }

    public AutorRepository getAutorRepository() {
        return autorRepository;
    }
}
