package ModelacaoEstrutural;

public class Elemento {
    
    private String tipo;
    private String conteudo;
    
    public Elemento() {
        this.tipo = "Generico";
        this.conteudo = "";
    }
    
    public Elemento(String tipo, String conteudo) {
        this.tipo = tipo;
        this.conteudo = conteudo;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getConteudo() {
        return conteudo;
    }
    
    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
    
    @Override
    public String toString() {
        return "Elemento[tipo=" + tipo + ", conteudo=" + conteudo + "]";
    }
}