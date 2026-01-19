package LiterAlura.service;

import LiterAlura.model.Autor;
import LiterAlura.repository.AutorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AutorService {

    private final AutorRepository repository;

    public AutorService(AutorRepository repository) {
        this.repository = repository;
    }

    public List<Autor> listarTodos() {
        return repository.findAll();
    }

    public Autor buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Autor no encontrado"));
    }

    public Autor guardar(Autor autor) {
        if (autor.getId() == null && repository.existsByNombreIgnoreCase(autor.getNombre())) {
            throw new IllegalArgumentException("Ya existe un autor con ese nombre");
        }
        return repository.save(autor);
    }

    public Autor actualizar(Long id, Autor autor) {
        Autor existente = buscarPorId(id);
        existente.setNombre(autor.getNombre());
        existente.setBirthYear(autor.getBirthYear());
        existente.setDeathYear(autor.getDeathYear());
        return repository.save(existente);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
