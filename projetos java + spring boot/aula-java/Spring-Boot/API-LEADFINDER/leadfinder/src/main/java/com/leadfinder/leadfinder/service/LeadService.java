package com.leadfinder.leadfinder.service;
import com.leadfinder.leadfinder.model.Lead;
import com.leadfinder.leadfinder.repository.LeadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadService {

    private final LeadRepository repository;

    public LeadService(LeadRepository repository){
        this.repository = repository;
    }

    public List<Lead> listar(){
        return repository.findAll();
    }

    public Lead salvar(Lead lead){
        return repository.save(lead);
    }

}