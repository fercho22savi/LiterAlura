package LiterAlura.repository;

import LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Búsqueda parcial por nombre
    List<Autor> findByNombreIgnoreCaseContaining(String nombre);

    Optional<Autor> findByNombreIgnoreCase(String nombre);

    boolean existsByNombreIgnoreCase(String nombre);

    @Query("""
        SELECT a FROM Autor a
        WHERE a.birthYear <= :year
        AND (a.deathYear IS NULL OR a.deathYear > :year)
    """)
    List<Autor> findAutoresVivosEn(@Param("year") int year);
}
