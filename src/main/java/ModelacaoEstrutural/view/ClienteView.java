package ModelacaoEstrutural.view;
import java.util.*;

import ModelacaoEstrutural.facade.PedidoFacade;

/**
 * Interface do Cliente para criar pedidos (Terminal)
 * Responsável apenas por I/O e comunicação com o Facade
 */
public class ClienteView {

    private PedidoFacade facade;
    private Scanner scanner;
    private int restauranteID;
    private int pedidoID;
    private Set<String> filtroAlergeniosAtivos;
    
    public ClienteView(int restauranteID, PedidoFacade facade) {
        this.facade = facade;
        this.restauranteID = restauranteID;
        this.scanner = new Scanner(System.in);
        this.filtroAlergeniosAtivos = new HashSet<>();
    }
    
    public void iniciar() {
        // Criar novo pedido no facade e obter o ID
        this.pedidoID = facade.iniciarNovoPedido(restauranteID);
        
        boolean continuar = true;
        
        while (continuar) {
            limparEcra();
            mostrarSelecaoArtigos();
            
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 0:
                    // Cancelar pedido
                    continuar = !cancelarPedido();
                    break;
                    
                case 98:
                    // Filtrar alergenios
                    filtrarAlergenios();
                    break;
                    
                case 99:
                    // Finalizar pedido
                    if (facade.pedidoVazio(pedidoID)) {
                        System.out.println("\nPedido vazio! Adicione pelo menos um item.");
                        aguardarEnter();
                    } else {
                        continuar = !finalizarPedido();
                    }
                    break;
                    
                default:
                    List<String> artigos = facade.getArtigosDisponiveis(restauranteID, filtroAlergeniosAtivos);
                    List<String> menus = facade.getMenusDisponiveis(restauranteID, filtroAlergeniosAtivos);
                    
                    if (opcao >= 1 && opcao <= artigos.size()) {
                        // Selecionar artigo
                        selecionarArtigo(opcao - 1);
                    } else if (opcao >= 100 && opcao < 100 + menus.size()) {
                        // Selecionar menu
                        selecionarMenu(opcao - 100);
                    } else {
                        System.out.println("\nOpcao invalida!");
                        aguardarEnter();
                    }
                    break;
            }
        }
    }
    
    private boolean cancelarPedido() {
        if (confirmarCancelamento()) {
            facade.cancelarPedido(pedidoID);
            return true;
        }
        return false;
    }
    
    private void mostrarSelecaoArtigos() {
        System.out.println("================================================================================");
        System.out.println("                           FAZER PEDIDO                                         ");
        System.out.println("================================================================================");
        
        if (!filtroAlergeniosAtivos.isEmpty()) {
            System.out.print("\nFiltros ativos: ");
            System.out.println(String.join(", ", filtroAlergeniosAtivos));
        }
        
        // Obter artigos do facade com filtro
        List<String> artigos = facade.getArtigosDisponiveis(restauranteID, filtroAlergeniosAtivos);
        
        System.out.println("\n--- ARTIGOS INDIVIDUAIS ---");
        for (int i = 0; i < artigos.size(); i++) {
            String a = artigos.get(i);
            System.out.printf("  %d. %-25s %.2f EUR", (i + 1), a, facade.getPrecoArtigo(a));
            if (!facade.getAlergenios(a).isEmpty()) {
                System.out.print(" [" + String.join(", ", facade.getAlergenios(a)) + "]");
            }
            System.out.println();
        }
        
        // Obter menus do facade com filtro
        List<String> menus = facade.getMenusDisponiveis(restauranteID, filtroAlergeniosAtivos);
        
        System.out.println("\n--- MENUS ---");
        for (int i = 0; i < menus.size(); i++) {
            String m = menus.get(i);
            System.out.printf("  %d. %-25s %.2f EUR", (100 + i), m, facade.getPrecoItemPedido(pedidoID, m));
            if (!facade.getAlergenios(m).isEmpty()) {
                System.out.print(" [" + String.join(", ", facade.getAlergenios(m)) + "]");
            }
            System.out.println();
        }
        
        mostrarCarrinho();
        
        System.out.println("\n================================================================================");
        System.out.println("  98. Filtrar por alergenios");
        System.out.println("  99. Finalizar pedido");
        System.out.println("   0. Cancelar pedido");
        System.out.println("================================================================================");
        System.out.print("Selecione uma opcao: ");
    }
    
    private void mostrarCarrinho() {
        System.out.println("\n--- CARRINHO ---");
        
        List<String> itens = facade.getItensPedido(pedidoID);
        
        if (itens.isEmpty()) {
            System.out.println("  (vazio)");
        } else {
            for (String item : itens) {
                System.out.printf("  - %s (%.2f EUR)\n", item, facade.getPrecoItemPedido(pedidoID, item));
            }
            System.out.printf("\n  TOTAL: %.2f EUR\n", facade.getTotalPedido(pedidoID));
        }
    }
    
    private void selecionarArtigo(String artigo) {
        limparEcra();
        System.out.println("================================================================================");
        System.out.println("                    PERSONALIZAR ARTIGO                                         ");
        System.out.println("================================================================================");
        System.out.println("\nArtigo: " + artigo);
        System.out.println("Preco base: " + String.format("%.2f EUR", facade.getPrecoArtigo(artigo)));
        
        // Coletar personalizações do utilizador
        Map<String, Object> personalizacoes = new HashMap<>();
        
        // Ingredientes extras
        List<String> extrasEscolhidos = escolherExtras(artigo);
        if (!extrasEscolhidos.isEmpty()) {
            personalizacoes.put("extras", extrasEscolhidos);
        }
        
        // Remover ingredientes
        List<String> ingredientesRemovidos = escolherIngredientesRemover(artigo);
        if (!ingredientesRemovidos.isEmpty()) {
            personalizacoes.put("remover", ingredientesRemovidos);
        }
        
        // Ponto da carne (requisito do cenario)
        if (facade.temCarne(artigo)) {
            String pontoCarne = escolherPontoCarne();
            if (pontoCarne != null) {
                personalizacoes.put("pontoCarne", pontoCarne);
            }
        }
        
        // Notas
        String notas = pedirNotas();
        if (notas != null) {
            personalizacoes.put("notas", notas);
        }
        
        // Adicionar ao pedido através do facade usando pedidoID
        facade.adicionarArtigoAoPedido(pedidoID, artigo, personalizacoes);
        
        System.out.println("\n[Artigo adicionado ao pedido!]");
        
        if (!perguntarAdicionarMais()) {
            // Volta ao menu principal
        }
    }
    
    private List<String> escolherExtras(String artigo) {
        List<String> extrasDisponiveis = facade.getExtrasDisponiveis(artigo);
        List<String> escolhidos = new ArrayList<>();
        
        if (extrasDisponiveis.isEmpty()) {
            return escolhidos;
        }
        
        System.out.println("\n--- INGREDIENTES EXTRA ---");
        for (int i = 0; i < extrasDisponiveis.size(); i++) {
            System.out.printf("  %d. %s\n", (i + 1), extrasDisponiveis.get(i));
        }
        
        System.out.print("\nEscolha extras (separados por virgula, Enter para nenhum): ");
        String input = scanner.nextLine().trim();
        
        if (!input.isEmpty()) {
            String[] indices = input.split(",");
            for (String idx : indices) {
                try {
                    int opcao = Integer.parseInt(idx.trim()) - 1;
                    if (opcao >= 0 && opcao < extrasDisponiveis.size()) {
                        escolhidos.add(extrasDisponiveis.get(opcao));
                    }
                } catch (NumberFormatException e) {
                    // Ignora entrada invalida
                }
            }
        }
        
        return escolhidos;
    }
    
    private List<String> escolherIngredientesRemover(String artigo) {
        List<String> ingredientesRemovíveis = facade.getIngredientesRemoviveis(artigo);
        List<String> removidos = new ArrayList<>();
        
        if (ingredientesRemovíveis.isEmpty()) {
            return removidos;
        }
        
        System.out.println("\n--- REMOVER INGREDIENTES ---");
        for (int i = 0; i < ingredientesRemovíveis.size(); i++) {
            System.out.printf("  %d. %s\n", (i + 1), ingredientesRemovíveis.get(i));
        }
        
        System.out.print("\nEscolha ingredientes a remover (separados por virgula, Enter para nenhum): ");
        String input = scanner.nextLine().trim();
        
        if (!input.isEmpty()) {
            String[] indices = input.split(",");
            for (String idx : indices) {
                try {
                    int opcao = Integer.parseInt(idx.trim()) - 1;
                    if (opcao >= 0 && opcao < ingredientesRemovíveis.size()) {
                        removidos.add(ingredientesRemovíveis.get(opcao));
                    }
                } catch (NumberFormatException e) {
                    // Ignora entrada invalida
                }
            }
        }
        
        return removidos;
    }
    
    // retorna int match para o enum
    private int escolherPontoCarne() {
        
        System.out.println("\n--- PONTO DA CARNE ---");
        System.out.println("1. Mal Passado.");
        System.out.println("2. Médio.");
        System.out.println("2. Bem Passado.");

        System.out.print("\nEscolha o ponto (Enter para medio): ");
        String input = scanner.nextLine().trim();

        if (!input.isEmpty()) {
            try {
                int opcao = Integer.parseInt(input);
                switch (opcao) {
                    case 1:
                        return 1;
                    case 2:
                        return 2;
                    case 3:
                        return 3;
                    default:
                        return 2;
                }
            } catch (NumberFormatException e) {
                // Usa padrão
            }
        }
    }
    
    private String pedirNotas() {
        System.out.print("\nNotas para a cozinha (Enter para nenhuma): ");
        String notas = scanner.nextLine().trim();
        return notas.isEmpty() ? null : notas;
    }
    
    private boolean perguntarAdicionarMais() {
        System.out.print("\nAdicionar mais artigos? (S/N): ");
        String resp = scanner.nextLine().trim().toUpperCase();
        return resp.equals("S");
    }
    
    private void selecionarMenu(String menu) {
        limparEcra();
        System.out.println("================================================================================");
        System.out.println("                      PERSONALIZAR MENU                                         ");
        System.out.println("================================================================================");
        System.out.println("\nMenu: " + menu);
        System.out.println("Preco: " + String.format("%.2f EUR", facade.getPrecoMenu(menu)));
        
        // Coletar personalizações do utilizador
        Map<String, Object> personalizacoes = new HashMap<>();
        
        // Tamanho
        String tamanho = escolherTamanho();
        personalizacoes.put("tamanho", tamanho);
        
        // Bebida
        String bebida = escolherBebida(menu);
        personalizacoes.put("bebida", bebida);
        
        // Acompanhamento
        String acompanhamento = escolherAcompanhamento(menu);
        personalizacoes.put("acompanhamento", acompanhamento);
        
        // Notas
        String notas = pedirNotas();
        if (notas != null) {
            personalizacoes.put("notas", notas);
        }
        
        // Adicionar ao pedido através do facade usando pedidoID
        facade.adicionarMenuAoPedido(pedidoID, menu, personalizacoes);
        
        System.out.println("\n[Menu adicionado ao pedido!]");
        perguntarAdicionarMais();
    }
    
    private String escolherTamanho() {
        
        System.out.println("\n--- TAMANHO ---");
        System.out.println("1. Médio.");
        System.out.println("2. Grande.");

        
        System.out.print("\nEscolha o tamanho (Enter para medio): ");
        String input = scanner.nextLine().trim();
        
        if (!input.isEmpty()) {
            try {
                int opcao = Integer.parseInt(input) - 1;
                if (opcao >= 1 && opcao < 3) {
                    return tamanhosDisponiveis.get(opcao);
                }
            } catch (NumberFormatException e) {
                // Usa padrão
            }
        }
        
        return "Medio";
    }
    
    // se estiver tudo esgotado estamos fucked
    private String escolherBebida(String menu) {
        List<String> bebidasDisponiveis = facade.getBebidasDisponiveis(menu);
        
        System.out.println("\n--- BEBIDA ---");
        for (int i = 0; i < bebidasDisponiveis.size(); i++) {
            System.out.printf("  %d. %s\n", (i + 1), bebidasDisponiveis.get(i));
        }
        
        System.out.print("\nEscolha a bebida (Enter para primeira opcao): ");
        String input = scanner.nextLine().trim();
        
        if (!input.isEmpty()) {
            try {
                int opcao = Integer.parseInt(input) - 1;
                if (opcao >= 0 && opcao < bebidasDisponiveis.size()) {
                    return bebidasDisponiveis.get(opcao);
                }
            } catch (NumberFormatException e) {
                // Usa padrão
            }
        }
        
        return bebidasDisponiveis.get(0);
    }
    
    private String escolherAcompanhamento(String menu) {
        List<String> acompanhamentosDisponiveis = facade.getAcompanhamentosDisponiveis(menu);
        
        System.out.println("\n--- ACOMPANHAMENTO ---");
        for (int i = 0; i < acompanhamentosDisponiveis.size(); i++) {
            System.out.printf("  %d. %s\n", (i + 1), acompanhamentosDisponiveis.get(i));
        }
        
        System.out.print("\nEscolha o acompanhamento (Enter para primeira opcao): ");
        String input = scanner.nextLine().trim();
        
        if (!input.isEmpty()) {
            try {
                int opcao = Integer.parseInt(input) - 1;
                if (opcao >= 0 && opcao < acompanhamentosDisponiveis.size()) {
                    return acompanhamentosDisponiveis.get(opcao);
                }
            } catch (NumberFormatException e) {
                // Usa padrão
            }
        }
        
        return acompanhamentosDisponiveis.get(0);
    }
    
    private boolean finalizarPedido() {
        limparEcra();
        mostrarResumoPedido();
        
        // Tipo de consumo
        boolean isTakeAway = escolherTipoConsumo();
        facade.setTipoConsumoPedido(pedidoID, isTakeAway);
        
        // Pagamento: repetir até sucesso
        while (true) {
            boolean pago = processarPagamento();
            
            if (pago) {
                // Só após pagamento bem sucedido é que o pedido é efetivamente finalizado
                facade.finalizarPedido(pedidoID);
                finalizarComSucesso();
                return true;
            }
            
            // Pagamento falhou -> volta a pedir método de pagamento
            System.out.println("\nPagamento falhou. Tente novamente com outro metodo.");
            aguardarEnter();
        }
    }

    private void finalizarComSucesso() {
        limparEcra();
        mostrarFatura();
        aguardarEnter();
    }
    
    private void mostrarResumoPedido() {
        System.out.println("================================================================================");
        System.out.println("                      RESUMO DO PEDIDO                                          ");
        System.out.println("================================================================================");
        
        System.out.println("\n--- ITENS ---");
        List<String> itens = facade.getItensPedido(pedidoID);
        
        for (String item : itens) {
            System.out.println("\n" + item + " " + facade.getPrecoItemPedido(pedidoID, item));
        }
        
        System.out.printf("\nTOTAL: %.2f EUR\n", facade.getTotalPedido(pedidoID));
    }
    
    private boolean escolherTipoConsumo() {
        System.out.println("\n--- TIPO DE CONSUMO ---");
        System.out.println("  1. Comer no restaurante");
        System.out.println("  2. Take-Away");
        System.out.print("\nEscolha uma opcao: (default 1)");
        String tipo = scanner.nextLine().trim();
        
        return tipo.equals("2");
    }
    
    private boolean processarPagamento() {
        limparEcra();
        System.out.println("================================================================================");
        System.out.println("                         PAGAMENTO                                              ");
        System.out.println("================================================================================");
        
        System.out.printf("\nTotal a pagar: %.2f EUR\n", facade.getTotalPedido(pedidoID));
        
        System.out.println("\n--- MÉTODO DE PAGAMENTO ---");
        System.out.println("  1. Pagar ao balcão");
        System.out.println("  2. Multibanco");
        System.out.print("\nEscolha uma opcao: ");
        String metodo = scanner.nextLine().trim();
        
        switch (metodo) {
            case "2":
                return processarPagamentoMultibanco();
            case "1":
            default:
                return processarPagamentoBalcao();
        }
    }

    private String gerarQRCodeAscii() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        sb.append("  +-------------------+\n");
        for (int i = 0; i < 5; i++) {
            sb.append("  | ");
            for (int j = 0; j < 15; j++) {
                sb.append(random.nextBoolean() ? "#" : " ");
            }
            sb.append(" |\n");
        }
        sb.append("  +-------------------+");

        return sb.toString();
    }
    
    private boolean processarPagamentoMultibanco() {
        
        System.out.println("\n[QR Code gerado]");
        System.out.println(gerarQRCodeAscii());
        System.out.println("\nAguarde processamento do pagamento...");
        
        // Simular pagamento
        System.out.print("\nPagamento bem sucedido? (S/N): ");
        String sucesso = scanner.nextLine().trim().toUpperCase();
        
        if (!sucesso.equals("S")) {
            System.out.println("\n[ERRO] Fundos insuficientes!");
            aguardarEnter();
            return false;
        }
        
        // Registar pagamento no facade usando pedidoID
        facade.registarPagamento(pedidoID, "Multibanco");
        return true;
    }
    
    private boolean processarPagamentoBalcao() {

        System.out.println("\n[Talão gerado]");
        System.out.println("================================================================================");
        System.out.println("                              TALÃO                                             ");
        System.out.println("================================================================================");
        System.out.println("  Numero: " + facade.getNumeroPedido(pedidoID));
        System.out.printf("  Total: %.2f EUR\n", facade.getTotalPedido(pedidoID));
        System.out.println("  ");
        System.out.println("  Entregue este talão no balcão para efetuar o pagamento.");
        System.out.println("================================================================================");
        
        aguardarEnter();
        
        // Simular pagamento no balcao
        System.out.print("\nPagamento efetuado? (S/N): ");
        String pago = scanner.nextLine().trim().toUpperCase();
        
        if (pago.equals("S")) {
            facade.registarPagamento(pedidoID, "Balcao");
            return true;
        }
        
        return false;
    }
    
    private void mostrarFatura() {
        System.out.println("================================================================================");
        System.out.println("                              FATURA                                            ");
        System.out.println("================================================================================");
        System.out.println("\n  Pedido #" + facade.getNumeroPedido(pedidoID));
        System.out.println("  Data: " + new Date());
        System.out.println();
        
        List<String> itens = facade.getItensPedido(pedidoID);
        for (String item : itens) {
            System.out.printf("  %-40s %.2f EUR\n", item, facade.getPrecoItemPedido(pedidoID, item));
        }
        
        System.out.println("\n  " + "-".repeat(60));
        System.out.printf("  %-40s %.2f EUR\n", "TOTAL", facade.getTotalPedido(pedidoID));
        
        int tempoEstimado = facade.calcularTempoEstimado(pedidoID);
        System.out.println("\n  Tempo estimado: " + tempoEstimado + " minutos");
        
        boolean isTakeAway = facade.isPedidoTakeAway(pedidoID);
        if (isTakeAway) {
            System.out.println("  Take-Away");
        } else {
            int balcao = facade.getBalcaoLevantamento(pedidoID);
            System.out.println("  Balcao de levantamento: " + balcao);
        }
        
        System.out.println("\n================================================================================");
        System.out.println("                   Obrigado pela sua preferencia!                               ");
        System.out.println("================================================================================");
    }
    
    private void filtrarAlergenios() {
        limparEcra();
        System.out.println("================================================================================");
        System.out.println("                     FILTRAR ALERGENIOS                                         ");
        System.out.println("================================================================================");
        
        // Obter todos os alergénios através do facade
        Set<String> todosAlergenios = facade.getTodosAlergenios(restauranteID);
        List<String> lista = new ArrayList<>(todosAlergenios);
        Collections.sort(lista);
        
        System.out.println("\nAlergenios disponiveis:");
        for (int i = 0; i < lista.size(); i++) {
            System.out.printf("  %d. %s\n", (i + 1), lista.get(i));
        }
        
        System.out.print("\nEscolha alergenios a filtrar (separados por virgula, 0 para limpar): ");
        String escolha = scanner.nextLine().trim();
        
        if (escolha.equals("0")) {
            filtroAlergeniosAtivos.clear();
            System.out.println("\n[Filtros removidos]");
        } else if (!escolha.isEmpty()) {
            String[] indices = escolha.split(",");
            List<String> alergeniosSelecionados = new ArrayList<>();
            
            for (String idx : indices) {
                try {
                    int i = Integer.parseInt(idx.trim()) - 1;
                    if (i >= 0 && i < lista.size()) {
                        String alergeno = lista.get(i);
                        filtroAlergeniosAtivos.add(alergeno);
                        alergeniosSelecionados.add(alergeno);
                    }
                } catch (NumberFormatException e) {
                    // Ignora
                }
            }
            
            facade.aplicarFiltrosAlergenios(pedidoID, alergeniosSelecionados);
            System.out.println("\n[Filtros aplicados]");
        }
        
        aguardarEnter();
    }
    
    private boolean confirmarCancelamento() {
        if (facade.pedidoVazio(pedidoID)) {
            return true;
        }
        
        System.out.print("\nTem certeza que deseja cancelar o pedido? (S/N): ");
        String resp = scanner.nextLine().trim().toUpperCase();
        return resp.equals("S");
    }
    
    private int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private void aguardarEnter() {
        System.out.print("\nPressione Enter para continuar...");
        scanner.nextLine();
    }
    
    private void limparEcra() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}