package LiterAlura.repository;

import LiterAlura.model.Libro;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Trae todos los libros junto con su autor
    @Query("SELECT l FROM Libro l LEFT JOIN FETCH l.autor")
    List<Libro> findAllWithAutor();
    Optional<Libro> findByTitulo(String title);

    List<Libro> findByTituloContainingIgnoreCase(String titulo);
}
