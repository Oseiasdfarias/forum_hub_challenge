package forum.hub.api.domain.topico;


import forum.hub.api.domain.autor.AutorRepository;
import forum.hub.api.domain.resposta.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private AutorRepository autorRepository;


    public DadosDetalhadosTopicoDto criarTopico(
            DadosTopicoDto dados) {

        var autor = autorRepository.getReferenceById(dados.IdAutor());

        var topico = new Topico(
                null,
                dados.titulo(),
                dados.mensagem(),
                LocalDateTime.now(),
                true,
                autor,
                dados.curso()
        );

        topicoRepository.save(topico);

        return new DadosDetalhadosTopicoDto(topico);
    }
}
