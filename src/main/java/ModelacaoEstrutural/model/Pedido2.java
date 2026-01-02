package ModelacaoEstrutural.model;

import java.util.ArrayList;
import java.util.List;

public class Pedido2 {

    private int id;
    private int tempoEsperado;
    private String nota;
    private List<Proposta> artigos;
    private boolean takeaway;

    public Pedido2(int id) {
        this.id = id;
        this.tempoEsperado = 0;
        this.nota = "";
        this.artigos = new ArrayList<>();
        this.takeaway = false;
    }
    
    public Pedido2(int id, int tempoEsperado, String nota) {
        this.id = id;
        this.tempoEsperado = tempoEsperado;
        this.nota = nota;
        this.artigos = new ArrayList<>();
        this.takeaway = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTempoEsperado() {
        return tempoEsperado;
    }

    public void setTempoEsperado(int tempoEsperado) {
        this.tempoEsperado = tempoEsperado;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
    
    public List<Proposta> getArtigos() {
        return new ArrayList<>(artigos);
    }
    
    public void adicionarArtigo(Proposta proposta) {
        artigos.add(proposta);
    }
    
    public boolean isTakeaway() {
        return takeaway;
    }
    
    public void setTakeaway(boolean takeaway) {
        this.takeaway = takeaway;
    }
    
    @Override
    public String toString() {
        return "Pedido #" + id + " (Tempo esperado: " + tempoEsperado + " min)";
    }
}