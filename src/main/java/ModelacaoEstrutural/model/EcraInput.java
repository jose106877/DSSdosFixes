package ModelacaoEstrutural.model;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class EcraInput extends Ecra {

    private Predicate<String> validacaoInput;
    private Consumer<String> acaoInput;

    public EcraInput(int numOpcoes, Opcao[] opcoes, boolean opNumeradas, Ecra proximoEcra, 
                     Predicate<String> validacao, Consumer<String> acao) {
        super(numOpcoes, opcoes, opNumeradas, proximoEcra);
        this.validacaoInput = validacao;
        this.acaoInput = acao;
    }

    public EcraInput(EcraInput original) {
        super(original);
        this.validacaoInput = original.validacaoInput;
        this.acaoInput = original.acaoInput;
    }

    @Override
    public void draw() {
        super.draw();
        System.out.println("Por favor, insira a sua opção:");
    }

    @Override
    public EcraInput clone() {
        return new EcraInput(this);
    }
    
    public boolean validarInput(String input) {
        if (validacaoInput != null) {
            return validacaoInput.test(input);
        }
        return true;
    }
    
    public void executarAcao(String input) {
        if (acaoInput != null && validarInput(input)) {
            acaoInput.accept(input);
        }
    }
    
    public Predicate<String> getValidacaoInput() {
        return validacaoInput;
    }
    
    public void setValidacaoInput(Predicate<String> validacaoInput) {
        this.validacaoInput = validacaoInput;
    }
    
    public Consumer<String> getAcaoInput() {
        return acaoInput;
    }
    
    public void setAcaoInput(Consumer<String> acaoInput) {
        this.acaoInput = acaoInput;
    }
}