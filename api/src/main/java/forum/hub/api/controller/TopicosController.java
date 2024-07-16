package forum.hub.api.controller;

import forum.hub.api.domain.ValidacaoException;
import forum.hub.api.domain.resposta.DadosDetalhamentoRespostaDto;
import forum.hub.api.domain.resposta.DadosRespostaDto;
import forum.hub.api.domain.resposta.Resposta;
import forum.hub.api.domain.resposta.RespostaRepository;
import forum.hub.api.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    TopicoRepository topicoRepository;

    @Autowired
    TopicoService topicoService;

    @Autowired
    RespostaRepository respostaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity criarTopico(
            @RequestBody DadosTopicoDto dados,
            UriComponentsBuilder uriBuilder){

        var topicoDetalhadoDto = topicoService.criarTopico(dados);

        var uri = uriBuilder
                .path("/topicos/{id}")
                .buildAndExpand(topicoDetalhadoDto.id())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(topicoDetalhadoDto);

    }

    @PutMapping
    @Transactional
    public ResponseEntity autalizarTopico(
            @RequestBody @Valid DadosAtualizarTopico dados) {

        if (!topicoRepository.existsById(dados.id())) {
            throw new ValidacaoException("Id do Tópico informado não existe!");
        }

        var topico = topicoRepository.getReferenceById(dados.id());
        topico.atualizarInfos(dados);

        return ResponseEntity.ok(new DadosDetalhadosTopicoDto(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listarTopicos(
            @PageableDefault(page = 0, size = 5, sort = {"titulo"})
            Pageable paginacao) {

        var page = topicoRepository.findAllByStatusTrue(paginacao)
                .map(DadosListagemTopico::new);

        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirTopico(@PathVariable Long id) {

        if (!topicoRepository.existsById(id)) {
            throw new ValidacaoException("Id do Tópico informado não existe!");
        }

        var medico = topicoRepository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharTopico(@PathVariable Long id) {

        if (!topicoRepository.existsById(id)) {
            throw new ValidacaoException("Id do Tópico informado não existe!");
        }

        var topico = topicoRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhadosTopicoDto(topico));
    }

    @GetMapping("/listar-respostas/{id}")
    public ResponseEntity<Page<DadosDetalhamentoRespostaDto>> listarRespostasPorTopicos(
            @PageableDefault(page = 0, size = 5, sort = {"autor"})
            Pageable paginacao,
            @PathVariable Long id) {

        var topico = topicoRepository.getReferenceById(id);

        var page = respostaRepository.listarRespostaPorTopico(paginacao, topico)
                .map(DadosDetalhamentoRespostaDto::new);

        return ResponseEntity.ok(page);
    }
}
