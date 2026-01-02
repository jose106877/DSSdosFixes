package ModelacaoEstrutural.model;

import java.util.ArrayList;
import java.util.List;

public class Ecra {

    private List<Integer> opcoesValidas;
    public boolean opcoesNumeradas;
    protected List<Opcao> opcoes;
    protected Ecra proximoEcra;

    public Ecra(int numOpcoes, Opcao[] opcoes, boolean opNumeradas, Ecra proximoEcra) {
        this.opcoesValidas = new ArrayList<>();
        this.opcoesNumeradas = opNumeradas;
        this.opcoes = new ArrayList<>();
        this.proximoEcra = proximoEcra;
        
        for (int i = 0; i < numOpcoes && i < opcoes.length; i++) {
            this.opcoes.add(opcoes[i]);
            if (opcoes[i] != null && opcoes[i].isValid()) {
                opcoesValidas.add(i);
            }
        }
    }

    public Ecra(Ecra original) {
        this.opcoesValidas = new ArrayList<>(original.opcoesValidas);
        this.opcoesNumeradas = original.opcoesNumeradas;
        this.opcoes = new ArrayList<>();
        for (Opcao op : original.opcoes) {
            this.opcoes.add(op);
        }
        this.proximoEcra = original.proximoEcra;
    }

    public Ecra clone() {
        return new Ecra(this);
    }

    public void draw() {
        System.out.println("=== Ecrã ===");
        for (int i = 0; i < opcoes.size(); i++) {
            if (opcoesNumeradas) {
                System.out.println((i + 1) + ". " + opcoes.get(i).getTexto());
            } else {
                System.out.println(opcoes.get(i).getTexto());
            }
        }
    }

    public Ecra getProximoEcra() {
        return this.proximoEcra;
    }
    
    public void setProximoEcra(Ecra proximoEcra) {
        this.proximoEcra = proximoEcra;
    }

    public void updateOpcaoAt(int idOpcao) {
        if (idOpcao >= 0 && idOpcao < opcoes.size()) {
            Opcao opcao = opcoes.get(idOpcao);
            if (opcao.isValid() && !opcoesValidas.contains(idOpcao)) {
                opcoesValidas.add(idOpcao);
            }
        }
    }
    
    public List<Integer> getOpcoesValidas() {
        return new ArrayList<>(opcoesValidas);
    }
    
    public List<Opcao> getOpcoes() {
        return new ArrayList<>(opcoes);
    }
}