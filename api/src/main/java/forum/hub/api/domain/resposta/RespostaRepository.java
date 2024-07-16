package forum.hub.api.domain.resposta;

import forum.hub.api.domain.topico.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
    @Query("""
                select r from Resposta r
                where
                r.topico = :topico
                """)
    Page<Resposta> listarRespostaPorTopico(
            Pageable paginacao,
            @Param("topico") Topico topico);

    @Modifying
    @Query("DELETE FROM Resposta r WHERE r.id = :id")
    void deletarPorId(Long id);
}
