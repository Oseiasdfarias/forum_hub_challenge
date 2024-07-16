package forum.hub.api.domain.resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarRespostaDto(
        @NotNull
        Long id,
        @NotBlank(message = "Mensagem é obrigatória.")
        String mensagem) {
}
