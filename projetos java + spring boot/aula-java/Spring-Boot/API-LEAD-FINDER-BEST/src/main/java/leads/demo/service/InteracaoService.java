package leads.demo.service;

import leads.demo.model.Interacao;
import leads.demo.repository.InteracaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteracaoService {

    private final InteracaoRepository repository;

    public InteracaoService(InteracaoRepository repository) {
        this.repository = repository;
    }

    public Interacao salvar(Interacao interacao) {
        return repository.save(interacao);
    }

    public List<Interacao> listar() {
        return repository.findAll();
    }
}
