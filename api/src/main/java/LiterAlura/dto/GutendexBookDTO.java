package LiterAlura.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public record GutendexBookDTO(

        Integer id,
        String title,

        List<GutendexAuthorDTO> authors,
        List<String> summaries,
        List<GutendexAuthorDTO> editors,
        List<GutendexAuthorDTO> translators,

        List<String> subjects,
        List<String> bookshelves,
        List<String> languages,

        Boolean copyright,

        @JsonProperty("media_type")
        String mediaType,

        Map<String, String> formats,

        @JsonProperty("download_count")
        Integer downloadCount
) {
}