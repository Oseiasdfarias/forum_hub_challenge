package forum.hub.api.controller;

import forum.hub.api.domain.resposta.DadosRespostaDto;
import forum.hub.api.domain.resposta.RespostaRepository;
import forum.hub.api.domain.resposta.RespostaService;
import jakarta.transaction.Transactional;
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

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirResposta(@PathVariable Long id) {

        repository.deletarPorId(id);

        return ResponseEntity.noContent().build();
    }
}
