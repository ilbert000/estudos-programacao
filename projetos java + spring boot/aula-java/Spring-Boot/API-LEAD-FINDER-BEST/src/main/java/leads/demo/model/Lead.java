package leads.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String nomeNegocio;
    private String categoria;
    private String cidade;

    @Column(length = 500)
    private String bio;

    private boolean possuiSite;
    private boolean possuiWhatsapp;

    private int seguidores;
    private String observacoes;

    private int score;
    private String classificacao;
}