package ModelacaoEstrutural;

public class Utilizador {

    private String nome;
    private String password;
    private String restaurante;
    private boolean coo;

    public Utilizador(String nome, String password, String restaurante, boolean coo) {
        this.nome = nome;
        this.password = password;
        this.restaurante = restaurante;
        this.coo = coo;
    }

    public String getRestaurante() {
        return this.restaurante;
    }
    
    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }

    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCoo() {
        return this.coo;
    }
    
    public void setCoo(boolean coo) {
        this.coo = coo;
    }
    
    @Override
    public String toString() {
        String tipo = coo ? "COO" : "Gestor";
        return tipo + ": " + nome + " (Restaurante: " + restaurante + ")";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Utilizador that = (Utilizador) obj;
        return nome != null ? nome.equals(that.nome) : that.nome == null;
    }
    
    @Override
    public int hashCode() {
        return nome != null ? nome.hashCode() : 0;
    }
}