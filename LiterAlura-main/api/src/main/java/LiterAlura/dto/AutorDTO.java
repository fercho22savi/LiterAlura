package LiterAlura.dto;

import LiterAlura.model.Autor;

public record AutorDTO(
        Long id,
        String nombre,
        Integer birthYear,
        Integer deathYear
) {
    public static AutorDTO fromEntity(Autor autor) {
        if (autor == null) return null;
        return new AutorDTO(
                autor.getId(),
                autor.getNombre(),
                autor.getBirthYear(),
                autor.getDeathYear()
        );
    }

    public Autor toEntity() {
        Autor autor = new Autor();
        autor.setId(id);
        autor.setNombre(nombre);
        autor.setBirthYear(birthYear);
        autor.setDeathYear(deathYear);
        return autor;
    }
}
