package ModelacaoEstrutural;

public class Alergenio {

    private String nome;

    public Alergenio(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString() {
        return "Alergenio: " + nome;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Alergenio that = (Alergenio) obj;
        return nome != null ? nome.equals(that.nome) : that.nome == null;
    }
    
    @Override
    public int hashCode() {
        return nome != null ? nome.hashCode() : 0;
    }
}