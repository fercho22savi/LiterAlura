package LiterAlura.repository;

import LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNombreIgnoreCase(String nombre);

    @Query("""
        SELECT a FROM Autor a
        WHERE a.birthYear <= :year
        AND (a.deathYear IS NULL OR a.deathYear > :year)
    """)
    List<Autor> findAutoresVivosEn(@Param("year") int year);

    Optional<Autor> findByNombre(String name);
}
