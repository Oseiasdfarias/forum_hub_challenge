package forum.hub.api.domain.curso;

import jakarta.validation.constraints.NotBlank;

public record DadosCursoDto(
        @NotBlank(message = "Nome é obrigatório.")
        String nome,
        @NotBlank(message = "Categoria é obrigatória.")
        String categoria) {
}
