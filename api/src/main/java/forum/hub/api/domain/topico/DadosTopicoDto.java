package forum.hub.api.domain.topico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record DadosTopicoDto(
        @NotBlank(message = "Título é obrigatório.")
        String titulo,
        @NotBlank(message = "Mensagem é obrigatório.")
        String mensagem,
        @NotBlank(message = "Status é obrigatório.")
        String status,
        @NotBlank(message = "Autor é obrigatório.")
        @Valid
        Long IdAutor,
        @NotBlank(message = "Curso é obrigatório.")
        @Valid
        Curso curso) {
}
