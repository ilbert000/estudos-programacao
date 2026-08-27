package com.example.primeira.controller;

import com.example.primeira.model.Usuario;
import com.example.primeira.repository.UsuarioRepository;
import com.example.primeira.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service){
        this.service = service;
    }

    @GetMapping
    public List<Usuario> listar(){
        return service.listar();
    }

    @PostMapping
    public Usuario salvar(@RequestBody Usuario usuario){
        return service.salvar(usuario);
    }
}
