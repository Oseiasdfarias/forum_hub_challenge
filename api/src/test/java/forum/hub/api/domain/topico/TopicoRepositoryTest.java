package forum.hub.api.domain.topico;

import forum.hub.api.domain.autor.Autor;
import forum.hub.api.domain.autor.AutorRepository;
import forum.hub.api.domain.autor.DadosAutorDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;



@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class TopicoRepositoryTest {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria verificar todos os Topicos ativos")
    void findAllByStatusTrueCenario1() {

        var topico = criarTopico();
        var topicoSalvo = topicoRepository.findById(topico.getId());
        assertThat(topico).isEqualTo(topicoSalvo.get());
    }

    private Autor cadastrarAutor(
            String nome,
            String email,
            String senha,
            String perfis) {
        var autor = new Autor(dadosAutor(nome, email, senha, perfis));
        em.persist(autor);
        return autor;
    }

    private Topico criarTopico() {

        var autor = cadastrarAutor("ana",
                "ana@forum.hub",
                "123456",
                "desenvolvedor Python");

        var topico = new Topico(
                null,
                "TESTE",
                "TESTE",
                LocalDateTime.now(),
                true,
                autor,
                Curso.Python);
        em.persist(topico);
        return topico;
    }

    private DadosAutorDto dadosAutor (
            String nome,
            String email,
            String senha,
            String perfis) {
        return new DadosAutorDto (nome, email, senha, perfis);
    }
}