package leads.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Interacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataContato;
    private String status;
    private String mensagem;

    @ManyToOne
    @JoinColumn(name = "lead_id")
    private Lead lead;
}