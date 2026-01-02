package ModelacaoEstrutural.model;

import java.time.LocalTime;

public class Ingrediente {

    private String nome;
    private LocalTime tempoDeEspera;

    public Ingrediente() {
        this.nome = "";
        this.tempoDeEspera = LocalTime.of(0, 0);
    }
    
    public Ingrediente(String nome, LocalTime tempoDeEspera) {
        this.nome = nome;
        this.tempoDeEspera = tempoDeEspera;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalTime getTempoDeEspera() {
        return this.tempoDeEspera;
    }

    public void setTempoDeEspera(LocalTime tempoDeEspera) {
        this.tempoDeEspera = tempoDeEspera;
    }
    
    @Override
    public String toString() {
        return "Ingrediente: " + nome + " (Tempo de espera: " + tempoDeEspera + ")";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ingrediente that = (Ingrediente) obj;
        return nome != null ? nome.equals(that.nome) : that.nome == null;
    }
    
    @Override
    public int hashCode() {
        return nome != null ? nome.hashCode() : 0;
    }
}