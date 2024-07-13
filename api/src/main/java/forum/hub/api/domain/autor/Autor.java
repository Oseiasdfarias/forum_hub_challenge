package forum.hub.api.domain.autor;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuario")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String perfis;

    public Autor(DadosAutorDto dados) {

        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
        this.perfis = dados.perfis();

    }
}
