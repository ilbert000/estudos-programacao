package leads.demo.controller;

import leads.demo.model.Lead;
import leads.demo.service.LeadService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leads")
@CrossOrigin
public class LeadController {

    private final LeadService service;

    public LeadController(LeadService service) {
        this.service = service;
    }

    @PostMapping
    public Lead criar(@RequestBody Lead lead) {
        return service.salvar(lead);
    }

    @GetMapping
    public List<Lead> listar() {
        return service.listar();
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @GetMapping("/{id}/mensagem")
    public String gerarMensagem(@PathVariable Long id) {
        Lead lead = service.listar()
                .stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElseThrow();

        return service.gerarMensagem(lead);
    }
}