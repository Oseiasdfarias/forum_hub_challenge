package forum.hub.api.domain.topico;

import com.fasterxml.jackson.annotation.JsonFormat;
import forum.hub.api.domain.autor.Autor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime data;

    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @Enumerated(EnumType.STRING)
    private Curso curso;

    public void atualizarInfos(DadosAtualizarTopico dados) {

        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
        if (dados.status() != null) {
            this.status = dados.status();
        }
        if (dados.curso() != null) {
            this.curso = dados.curso();
        }
    }

    public void excluir() {
        this.status = false;
    }
}