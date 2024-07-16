package forum.hub.api.domain.autor;

public record DadosAutorDetalhamentoDto(
        Long id,
        String nome) {

    public DadosAutorDetalhamentoDto(Autor dados) {
        this(
                dados.getId(),
                dados.getNome()
        );
    }
}
