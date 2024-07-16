package forum.hub.api.domain.topico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarTopico(
        @NotNull
        Long id,
        String titulo,
        String mensagem,
        Boolean status,
        @Valid
        Curso curso) {
}
