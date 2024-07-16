package forum.hub.api.controller;

import forum.hub.api.domain.ValidacaoException;
import forum.hub.api.domain.resposta.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

    @Autowired
    RespostaRepository repository;

    @Autowired
    RespostaService respostaService;

    @PostMapping
    @Transactional
    public ResponseEntity responder(
            @RequestBody DadosRespostaDto dados,
            UriComponentsBuilder uriBuilder) {

        var respostaDetalhamentoDto = respostaService.responderTopico(dados);

        var uri = uriBuilder
                .path("/respostas/{id}")
                .buildAndExpand(respostaDetalhamentoDto.id())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(respostaDetalhamentoDto);
    }

    @PutMapping
    @Transactional
    public ResponseEntity autalizarResposta(
            @RequestBody @Valid DadosAtualizarRespostaDto dados) {

        if (!repository.existsById(dados.id())) {
            throw new ValidacaoException("Id da Resposta informado não existe!");
        }

        var resposta = repository.getReferenceById(dados.id());
        resposta.atualizarInfos(dados);

        return ResponseEntity.ok(new DadosDetalhamentoRespostaDto(resposta));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirResposta(@PathVariable Long id) {

        if (!repository.existsById(id)) {
            throw new ValidacaoException("Id de Resposta informado não existe!");
        }

        repository.deletarPorId(id);

        return ResponseEntity.noContent().build();
    }
}
