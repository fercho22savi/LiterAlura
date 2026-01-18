package LiterAlura.repository;

import LiterAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    List<Libro> findByIdiomaIgnoreCase(String idioma);

    Optional<Object> findByTitulo(String title);
}
