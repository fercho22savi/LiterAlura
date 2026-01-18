package LiterAlura.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GutendexAuthorDTO(

        String name,

        @JsonProperty("birth_year")
        Integer birthYear,

        @JsonProperty("death_year")
        Integer deathYear
) {
}