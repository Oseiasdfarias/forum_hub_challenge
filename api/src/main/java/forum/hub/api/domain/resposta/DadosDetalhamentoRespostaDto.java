package forum.hub.api.domain.resposta;

public record DadosDetalhamentoRespostaDto(
        Long id,
        String mensagem,
        String topico,
        String autor,
        Boolean solucao) {

    public DadosDetalhamentoRespostaDto(Resposta dados) {
        this(
                dados.getId(),
                dados.getMensagem(),
                dados.getTopico().getTitulo(),
                dados.getAutor().getNome(),
                dados.getSolucao());
    }
}
