package LiterAlura.config;

import LiterAlura.dto.GutendexAuthorDTO;
import LiterAlura.dto.GutendexBookDTO;
import LiterAlura.model.Autor;
import LiterAlura.model.Libro;
import LiterAlura.repository.AutorRepository;
import LiterAlura.repository.LibroRepository;
import org.springframework.stereotype.Component;

@Component
public class GutendexMapper {

    private final AutorRepository autorRepo;
    private final LibroRepository libroRepo;

    public GutendexMapper(AutorRepository autorRepo, LibroRepository libroRepo) {
        this.autorRepo = autorRepo;
        this.libroRepo = libroRepo;
    }

    public void mapearYGuardar(GutendexBookDTO dto) {

        // Validar autores
        if (dto.authors() == null || dto.authors().isEmpty()) {
            return; // libro sin autor, se ignora
        }

        GutendexAuthorDTO autorDTO = dto.authors().getFirst();

        Autor autor = autorRepo.findByNombre(autorDTO.name())
                .orElseGet(() -> autorRepo.save(
                        new Autor(
                                autorDTO.name(),
                                autorDTO.birthYear(),
                                autorDTO.deathYear()
                        )
                ));

        //  Evitar libros duplicados
        if (libroRepo.findByTitulo(dto.title()).isPresent()) {
            return;
        }

        // Idioma principal
        String idioma = (dto.languages() == null || dto.languages().isEmpty())
                ? "desconocido"
                : dto.languages().getFirst();

        Libro libro = new Libro(
                dto.title(),
                idioma,
                dto.downloadCount(),
                autor
        );

        libroRepo.save(libro);
    }

    /**
     * Retorna la cantidad total de autores registrados
     */
    public int contarAutores() {
        return (int) autorRepo.count(); // count() devuelve long, convertimos a int
    }

    /**
     * Retorna la cantidad total de libros registrados
     */
    public int contarLibros() {
        return (int) libroRepo.count(); // count() devuelve long, convertimos a int
    }
}
