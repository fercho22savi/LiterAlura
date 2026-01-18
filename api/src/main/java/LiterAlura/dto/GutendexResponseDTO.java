package LiterAlura.dto;

import java.util.List;

public record GutendexResponseDTO(
        Integer count,
        String next,
        String previous,
        List<GutendexBookDTO> results
) {
}