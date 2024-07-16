package forum.hub.api.domain.resposta;

public record DadosDetalhamentoRespostaDto(
        Long id,
        String resposta,
        Long idTopico,
        String topicoMensagem,
        String autor,
        Boolean solucao) {

    public DadosDetalhamentoRespostaDto(Resposta dados) {
        this(
                dados.getId(),
                dados.getMensagem(),
                dados.getTopico().getId(),
                dados.getTopico().getMensagem(),
                dados.getAutor().getNome(),
                dados.getSolucao());
    }
}
