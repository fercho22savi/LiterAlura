package LiterAlura.service;

import LiterAlura.config.GutendexMapper;
import LiterAlura.dto.GutendexResponseDTO;
import LiterAlura.dto.GutendexBookDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class GutendexService {

    private static final String URL = "https://gutendex.com/books";

    private final RestTemplate restTemplate;
    private final GutendexMapper mapper;

    public GutendexService(RestTemplate restTemplate, GutendexMapper mapper) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
    }

    /**
     * Importa libros desde Gutendex y los persiste en PostgreSQL
     */
    public void importarLibros() {
        GutendexResponseDTO response = restTemplate.getForObject(URL, GutendexResponseDTO.class);

        Optional.ofNullable(response)
                .map(GutendexResponseDTO::results)
                .ifPresent(list -> list.forEach(mapper::mapearYGuardar));
    }

    /**
     * Obtiene los libros desde Gutendex sin persistir
     */
    public GutendexResponseDTO obtenerLibros() {
        return restTemplate.getForObject(URL, GutendexResponseDTO.class);
    }

    /**
     * Obtiene libros de una página específica de Gutendex
     */
    public GutendexResponseDTO obtenerLibros(int page) {
        String url = URL + "?page=" + page;
        return restTemplate.getForObject(url, GutendexResponseDTO.class);
    }

    /**
     * Método para obtener libros con filtros opcionales
     */
    public GutendexResponseDTO obtenerLibrosConFiltros(
            Integer authorYearStart,
            Integer authorYearEnd,
            String languages,
            Boolean copyright,
            String ids,
            String search
    ) {
        // Construir la URL con parámetros opcionales
        StringBuilder sb = new StringBuilder(URL);
        boolean firstParam = true;

        if (authorYearStart != null) {
            sb.append(firstParam ? "?" : "&").append("author_year_start=").append(authorYearStart);
            firstParam = false;
        }

        if (authorYearEnd != null) {
            sb.append(firstParam ? "?" : "&").append("author_year_end=").append(authorYearEnd);
            firstParam = false;
        }

        if (languages != null && !languages.isBlank()) {
            sb.append(firstParam ? "?" : "&").append("languages=").append(languages);
            firstParam = false;
        }

        if (copyright != null) {
            sb.append(firstParam ? "?" : "&").append("copyright=").append(copyright);
            firstParam = false;
        }

        if (ids != null && !ids.isBlank()) {
            sb.append(firstParam ? "?" : "&").append("ids=").append(ids);
            firstParam = false;
        }

        if (search != null && !search.isBlank()) {
            sb.append(firstParam ? "?" : "&").append("search=").append(search.replace(" ", "%20"));
        }

        String finalUrl = sb.toString();
        System.out.println("URL construida: " + finalUrl); // Para debug

        return restTemplate.getForObject(finalUrl, GutendexResponseDTO.class);
    }

    /**
     * DTO para resumen de la importación
     */
    public record ImportSummary(int librosImportados, int autoresImportados) {
    }
}
