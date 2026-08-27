package com.leadfinder.leadfinder.controller;

import com.leadfinder.leadfinder.model.Lead;
import com.leadfinder.leadfinder.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.leadfinder.leadfinder.service.OpenStreetMapService;

import java.util.List;

@RestController
@RequestMapping("/leads")
@CrossOrigin
public class LeadController {

    private final LeadService service;

    public LeadController(LeadService service){
        this.service = service;
    }

    @GetMapping
    public List<Lead> listar(){
        return service.listar();
    }

    @PostMapping
    public Lead salvar(@RequestBody Lead lead){
        return service.salvar(lead);
    }

    @GetMapping("/buscar-openmap")
    public String buscarOpenMap(@RequestParam String categoria){

        return mapService.buscarEmpresas(categoria);

    }

    @Autowired
    private OpenStreetMapService mapService;

}