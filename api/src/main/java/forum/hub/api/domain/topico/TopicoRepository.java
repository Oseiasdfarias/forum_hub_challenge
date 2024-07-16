package forum.hub.api.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findAllByStatusTrue(Pageable paginacao);

    @Modifying
    @Query("DELETE FROM Topico t WHERE t.id = :id")
    void deletarPorId(Long id);
}
