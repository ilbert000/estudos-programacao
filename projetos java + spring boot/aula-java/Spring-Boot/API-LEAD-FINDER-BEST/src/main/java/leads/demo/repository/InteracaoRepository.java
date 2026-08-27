package leads.demo.repository;

import leads.demo.model.Interacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InteracaoRepository extends JpaRepository<Interacao, Long> {
}