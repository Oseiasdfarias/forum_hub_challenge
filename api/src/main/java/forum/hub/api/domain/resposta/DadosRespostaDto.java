package forum.hub.api.domain.resposta;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record DadosRespostaDto(
        @NotBlank(message = "Mensagem é obrigatória.")
        String mensagem,
        @Valid
        Long IdAutor,
        @NotBlank(message = "Id do Tópico é obrigatório.")
        @Valid
        Long IdTopico,
        Boolean solucao) {
}
