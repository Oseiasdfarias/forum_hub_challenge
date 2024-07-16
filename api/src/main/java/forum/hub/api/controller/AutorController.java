package forum.hub.api.controller;

import forum.hub.api.domain.ValidacaoException;
import forum.hub.api.domain.autor.Autor;
import forum.hub.api.domain.autor.AutorRepository;
import forum.hub.api.domain.autor.DadosAutorDetalhamentoDto;
import forum.hub.api.domain.autor.DadosAutorDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarAutor(
            @RequestBody @Valid DadosAutorDto dados,
            UriComponentsBuilder uriBuilder) {
        var autor = new Autor(dados);
        repository.save(autor);

        var uri = uriBuilder
                .path("/autores/{id}")
                .buildAndExpand(autor.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(new DadosAutorDetalhamentoDto(autor));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirAutor(@PathVariable Long id) {

        if (!repository.existsById(id)) {
            throw new ValidacaoException("Id do autor informado não existe!");
        }

        repository.deletarPorId(id);

        return ResponseEntity.noContent().build();
    }
}
