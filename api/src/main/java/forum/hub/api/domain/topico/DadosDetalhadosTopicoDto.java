package forum.hub.api.domain.topico;

public record DadosDetalhadosTopicoDto(
        Long id,
        String titulo,
        String mensagem,
        Boolean status,
        String curso) {
    public DadosDetalhadosTopicoDto(Topico dadosTopico) {
        this(
                dadosTopico.getId(),
                dadosTopico.getTitulo(),
                dadosTopico.getMensagem(),
                dadosTopico.getStatus(),
                dadosTopico.getCurso().name());
    }
}
