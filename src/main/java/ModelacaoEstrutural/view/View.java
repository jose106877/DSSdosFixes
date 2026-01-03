package ModelacaoEstrutural.view;
import java.util.Scanner;

import ModelacaoEstrutural.facade.AutenticacaoFacade;
import ModelacaoEstrutural.facade.CadeiaFacade;
import ModelacaoEstrutural.facade.GestaoFacade;
import ModelacaoEstrutural.facade.PedidoFacade;

public class View {

    private final Scanner scanner = new Scanner(System.in);

    // colocar cadeia a fornecer autenticacao aqui? serve para os restaurantes ig a cadeia
    // colocar so cadeia e obter o resto dele, é melhor...?
    private final CadeiaFacade cadeia;
    private final AutenticacaoFacade autenticacaoFacade;
    private final GestaoFacade gestaoFacade;
    private final PedidoFacade pedidoFacade;
    

    private int restauranteSelecionado;

    public View(AutenticacaoFacade autenticacaoFacade, CadeiaFacade cadeia, GestaoFacade gestaoFacade, PedidoFacade pedidoFacade) {
        this.autenticacaoFacade = autenticacaoFacade;
        this.cadeia = cadeia;
        this.gestaoFacade = gestaoFacade;
        this.pedidoFacade = pedidoFacade;
    }

    public void run() {
        boolean running = true;

        while (running) {
            limparEcra();
            System.out.println("=== MENU INICIAL ===");
            System.out.println("1. Selecionar restaurante");
            System.out.println("2. Login COO");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int option = readInt();

            switch (option) {
                case 1: 
                    restauranteSelecionado = menuSelecionarRestaurante();
                    menuRestaurante();
                    break;
                case 2:
                    if(login(true)) {
                        System.out.println("Bem-Vindo COO!");
                        GestorView gestorView = new GestorView(gestaoFacade, true);
                        gestorView.iniciar();
                    } else {
                        System.out.println("Login falhado.");
                    }
                    break;
                case 0: 
                    System.out.println("A sair do programa...");
                    running = false;
                    break;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    private int menuSelecionarRestaurante() {
        System.out.println("=== SELECIONAR RESTAURANTE ===");

        for(int i = 0; i < cadeia.getRestaurantes().size(); i++) {
            System.out.println((i + 1) + ". " + cadeia.getRestaurante(i).getNome());
        }
        System.out.println();
        System.out.print("Escolha um restaurante: ");
        int escolha = readInt();
        return escolha;
    }

    private void menuRestaurante(){
        System.out.println("=== MENU RESTAURANTE ===");
        System.out.println("1. Efetuar Pedidos");
        System.out.println("2. Operar Posto");
        System.out.println("3. Autenticar Gestor");
        System.out.println("0. Voltar ao menu inicial");
        System.out.println();
        System.out.print("Escolha uma opção: ");

        int escolha = readInt();
        switch (escolha) {
            case 1:
                ClienteView clientView = new ClienteView(restauranteSelecionado, pedidoFacade);
                clientView.iniciar();
                break;
            case 2:
                // pôr aqui cozinhaFacade
                DisplayFuncionarioView displayFuncionario = new DisplayFuncionarioView(pedidoFacade);
                displayFuncionario.iniciar();
                break;
            case 3:
                if(login(false)) {
                    System.out.println("Bem-Vindo Gestor!");
                    GestorView gestorView = new GestorView(autenticacaoFacade, gestaoFacade, pedidoFacade, null, cadeia);
                    gestorView.iniciar(restauranteSelecionado);
                }
                break;
            case 0:
                System.out.println("Voltando ao menu inicial...");
                break;
            default: System.out.println("Opção inválida.");
        }
    }

    private boolean login(boolean isCOO) {
        System.out.println("=== LOGIN ===");
        System.out.print("Username: ");
        String user = scanner.nextLine();

        System.out.print("Password: ");
        String pass = scanner.nextLine();

        if (isCOO)
            return autenticacaoFacade.loginCOO(user, pass);
        else
            return autenticacaoFacade.loginGestor(user, pass);
    }

    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Introduza um número válido: ");
            }
        }
    }

    private void limparEcra() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
