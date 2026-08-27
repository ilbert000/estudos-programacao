import java.time.LocalDate;
import java.time.LocalTime;

public class CustomerData {
    private String nome;
    private LocalDate data;
    private LocalTime hora;
    private String telefone;
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}