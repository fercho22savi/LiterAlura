package LiterAlura.service;

import LiterAlura.dto.GutendexResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GutendexService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String URL = "https://gutendex.com/books/";

    public GutendexResponseDTO obtenerLibros() {
        return restTemplate.getForObject(URL, GutendexResponseDTO.class);
    }
}
