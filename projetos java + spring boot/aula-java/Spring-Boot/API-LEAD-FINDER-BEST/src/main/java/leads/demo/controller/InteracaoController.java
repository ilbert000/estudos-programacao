package leads.demo.controller;

import leads.demo.model.Interacao;
import leads.demo.service.InteracaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interacoes")
@CrossOrigin
public class InteracaoController {

    private final InteracaoService service;

    public InteracaoController(InteracaoService service) {
        this.service = service;
    }

    @PostMapping
    public Interacao salvar(@RequestBody Interacao interacao) {
        return service.salvar(interacao);
    }

    @GetMapping
    public List<Interacao> listar() {
        return service.listar();
    }
}