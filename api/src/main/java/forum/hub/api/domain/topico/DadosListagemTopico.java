package forum.hub.api.domain.topico;

import java.time.LocalDateTime;

public record DadosListagemTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime data,
        Boolean status,
        Curso curso) {
    public DadosListagemTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getData(),
                topico.getStatus(),
                topico.getCurso()
        );
    }
}
