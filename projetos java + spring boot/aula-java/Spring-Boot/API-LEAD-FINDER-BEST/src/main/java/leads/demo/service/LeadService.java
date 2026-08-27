package leads.demo.service;

import leads.demo.model.Lead;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeadService {

    private final List<Lead> leads = new ArrayList<>();

    public Lead salvar(Lead lead) {
        lead.setId((long) (leads.size() + 1)); // gera id simples
        leads.add(lead);
        return lead;
    }

    public List<Lead> listar() {
        return leads;
    }

    public void deletar(Long id) {
        leads.removeIf(l -> l.getId().equals(id));
    }

    public String gerarMensagem(Lead lead) {
        return "Olá " + lead.getNomeNegocio() + ", podemos ajudar com seu site!";
    }
}