package forum.hub.api.domain.resposta;

import forum.hub.api.domain.ValidacaoException;
import forum.hub.api.domain.autor.AutorRepository;
import forum.hub.api.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RespostaService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private AutorRepository autorRepository;

    public DadosDetalhamentoRespostaDto responderTopico(
            DadosRespostaDto dados
    ) {

        if (dados.IdTopico() == null) {
            throw new ValidacaoException("Id do Tópico necessário");
        }
        if (!topicoRepository.existsById(dados.IdTopico())) {
            throw new ValidacaoException("Id do Tópico informado não existe!");
        }

        var topico = topicoRepository.getReferenceById(dados.IdTopico());
        var autor = autorRepository.getReferenceById(topico.getAutor().getId());

        var resposta = new Resposta(
                null,
                dados.mensagem(),
                topico,
                LocalDateTime.now(),
                autor,
                false
        );

        respostaRepository.save(resposta);

        return new DadosDetalhamentoRespostaDto(
                resposta.getId(),
                resposta.getMensagem(),
                topico.getId(),
                topico.getMensagem(),
                autor.getNome(),
                resposta.getSolucao());
    }
}
