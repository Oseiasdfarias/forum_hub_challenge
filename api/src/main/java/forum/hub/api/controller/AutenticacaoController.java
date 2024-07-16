package forum.hub.api.controller;

import forum.hub.api.domain.autor.Autor;
import forum.hub.api.domain.autor.DadosAutenticacao;
import forum.hub.api.infra.security.DadosTokenJWT;
import forum.hub.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var autenticationToken = new UsernamePasswordAuthenticationToken(
                dados.login(), dados.senha());
        var authentication = manager.authenticate(autenticationToken);

        var tokenJWT = tokenService.gerarToken((Autor) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}