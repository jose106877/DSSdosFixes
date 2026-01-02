package ModelacaoEstrutural.model;

public class Nota {

    private String texto;

    public Nota(String texto) {
        this.texto = texto;
    }
    
    public String getTexto() {
        return texto;
    }
    
    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    @Override
    public String toString() {
        return "Nota: " + texto;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Nota nota = (Nota) obj;
        return texto != null ? texto.equals(nota.texto) : nota.texto == null;
    }
    
    @Override
    public int hashCode() {
        return texto != null ? texto.hashCode() : 0;
    }
}