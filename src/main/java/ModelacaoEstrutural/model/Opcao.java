package ModelacaoEstrutural.model;

import java.util.function.Predicate;

public class Opcao {

    private String texto;
    private Predicate<String> condition;

    public Opcao(String textoBase, Predicate<String> cond) {
        this.texto = textoBase;
        this.condition = cond;
    }

    public boolean isValid() {
        if (condition == null) {
            return true;
        }
        return condition.test(texto);
    }

    public String getTexto() {
        return this.texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    public Predicate<String> getCondition() {
        return condition;
    }
    
    public void setCondition(Predicate<String> condition) {
        this.condition = condition;
    }
    
    @Override
    public String toString() {
        return texto;
    }
}