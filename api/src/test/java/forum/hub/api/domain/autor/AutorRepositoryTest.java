package forum.hub.api.domain.autor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class AutorRepositoryTest {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria cadastrar autor")
    void cadastarAutorCenario1() {

        var autor = cadastrarAutor("ana",
                "ana@forum.hub",
                "123456",
                "desenvolvedor Python");

        var autorRetornado = autorRepository.findById(autor.getId());
        assertThat(autor).isEqualTo(autorRetornado.get());
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

    private DadosAutorDto dadosAutor (
            String nome,
            String email,
            String senha,
            String perfis) {
        return new DadosAutorDto (nome, email, senha, perfis);
    }
}