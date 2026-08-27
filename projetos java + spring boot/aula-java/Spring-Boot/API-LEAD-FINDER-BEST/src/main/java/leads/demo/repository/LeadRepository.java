package leads.demo.repository;

import leads.demo.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LeadRepository extends JpaRepository<Lead, Long> {

    List<Lead> findByCidade(String cidade);
    List<Lead> findByCategoria(String categoria);
    List<Lead> findByClassificacao(String classificacao);
    List<Lead> findByPossuiSite(boolean possuiSite);
}