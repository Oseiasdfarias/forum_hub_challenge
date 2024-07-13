package forum.hub.api.domain.Topico;

import com.fasterxml.jackson.annotation.JsonFormat;
import forum.hub.api.domain.autor.Autor;
import forum.hub.api.domain.curso.Curso;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Table
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime data;

    private String status;
    private Autor autor;
    private Curso curso;
    private String resposta;

    public Topico() {
        this.data = LocalDateTime.now();
    }

}
