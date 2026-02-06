import java.util.*;

public class GerenciarSistemaAereo {

    private Queue<Passageiro> filaComum;
    private PriorityQueue<Passageiro> filaPrioritaria;
    private GerenciamentoDeAvioes gerAvioes;
    private PilhaHistoricoOperacoes historico;

    private int contadorChegada;
    private Scanner sc;

    public GerenciarSistemaAereo() {
        filaComum = new LinkedList<>();
        filaPrioritaria = new PriorityQueue<>(new ComparadorPassageiro());
        gerAvioes = new GerenciamentoDeAvioes();
        historico = new PilhaHistoricoOperacoes();
        contadorChegada = 1; 
        sc = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;
        do {
            exibirMenuPrincipal();
            try {
                opcao = Integer.parseInt(sc.nextLine()); 
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1 -> menuAvioes();
                case 2 -> menuPassageiros();
                case 3 -> menuHistorico();
                case 0 -> System.out.println("Encerrando sistema...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
        sc.close();
    }

    private void exibirMenuPrincipal() {
        System.out.println("\n========================================");
        System.out.println("   SISTEMA DE GESTÃO DE EMPRESA AÉREA");
        System.out.println("========================================");
        System.out.println("1 - Gerenciamento de Aviões");
        System.out.println("2 - Gestão de Passageiros e Embarque");
        System.out.println("3 - Histórico de Operações");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void menuAvioes() {
        System.out.println("\n--- GERENCIAMENTO DE AVIÕES ---");
        System.out.println("1 - Cadastrar novo avião");
        System.out.println("2 - Listar aviões cadastrados");
        System.out.println("3 - Remover avião");
        System.out.println("0 - Voltar");
        System.out.print("Opção: ");

        int op = Integer.parseInt(sc.nextLine());

        switch (op) {
            case 1 -> cadastrarAviao();
            case 2 -> gerAvioes.listarAvioes();
            case 3 -> removerAviao();
            case 0 -> { return; }
            default -> System.out.println("Opção inválida.");
        }
    }

    private void cadastrarAviao() {
        System.out.print("Código: ");
        String codigo = sc.nextLine();
        System.out.print("Modelo: ");
        String modelo = sc.nextLine();
        System.out.print("Fabricante: ");
        String fabricante = sc.nextLine();
        System.out.print("Capacidade: ");
        int capacidade = Integer.parseInt(sc.nextLine());

        gerAvioes.inserirAviao(new Aviao(codigo, modelo, fabricante, capacidade));
        historico.registrar("Cadastrou avião: " + codigo + " (" + modelo + ")");
        System.out.println(">> Avião cadastrado com sucesso!");
    }

    private void removerAviao() {
        System.out.print("Código do avião a remover: ");
        String codigo = sc.nextLine();
        boolean removeu = gerAvioes.removerAviao(codigo);
        if (removeu) {
            historico.registrar("Removeu avião: " + codigo);
        }
    }

    private void menuPassageiros() {
        System.out.println("\n--- GESTÃO DE PASSAGEIROS E EMBARQUE ---");
        System.out.println("1 - Vender passagem (Comum)");
        System.out.println("2 - Vender passagem (Prioritária)");
        System.out.println("3 - Embarcar próximo passageiro");
        System.out.println("4 - Exibir filas de embarque"); 
        System.out.println("0 - Voltar");
        System.out.print("Opção: ");

        int op = Integer.parseInt(sc.nextLine());

        switch (op) {
            case 1 -> cadastrarPassageiro(false);
            case 2 -> cadastrarPassageiro(true);
            case 3 -> embarcarPassageiro();
            case 4 -> exibirFilas();
            case 0 -> { return; }
            default -> System.out.println("Opção inválida.");
        }
    }

    private void cadastrarPassageiro(boolean prioritario) {
        String idChegada = "CH" + contadorChegada;
        System.out.print("Nome do Passageiro: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Número do Voo: ");
        int voo = Integer.parseInt(sc.nextLine());

        Prioridade prioridade = Prioridade.Baixa; 
        
        if (prioritario) {
            System.out.println("Selecione a Prioridade: 1-Alta | 2-Media");
            int p = Integer.parseInt(sc.nextLine());
            prioridade = (p == 1) ? Prioridade.Alta : Prioridade.Media;
        }

        Passageiro p = new Passageiro(nome, cpf, voo, prioridade, idChegada, contadorChegada);
        contadorChegada++;

        if (prioridade == Prioridade.Baixa) {
            filaComum.add(p);
        } else {
            filaPrioritaria.add(p);
        }

        historico.registrar("Venda de passagem: " + nome + " (" + prioridade + ")");
        System.out.println(">> Passagem vendida! ID: " + idChegada);
    }

    private void embarcarPassageiro() {
        Passageiro p = null;

        if (!filaPrioritaria.isEmpty()) {
            p = filaPrioritaria.poll();
        } else if (!filaComum.isEmpty()) {
            p = filaComum.poll();
        }

        if (p != null) {
            System.out.println(">> EMBARCANDO: " + p);
            historico.registrar("Embarcou passageiro: " + p.getNome());
        } else {
            System.out.println(">> Não há passageiros aguardando embarque.");
        }
    }
    
    // Funcionalidade que faltava
    private void exibirFilas() {
        System.out.println("\n--- FILA PRIORITÁRIA (Ordem de saída) ---");
        if (filaPrioritaria.isEmpty()) System.out.println("(Vazia)");
        else {
            Passageiro[] temp = filaPrioritaria.toArray(new Passageiro[0]);
            Arrays.sort(temp, new ComparadorPassageiro());
            for (Passageiro p : temp) System.out.println(p);
        }

        System.out.println("\n--- FILA COMUM ---");
        if (filaComum.isEmpty()) System.out.println("(Vazia)");
        else {
            for (Passageiro p : filaComum) System.out.println(p);
        }
    }

    private void menuHistorico() {
        System.out.println("\n--- HISTÓRICO DE OPERAÇÕES ---");
        System.out.println("1 - Exibir histórico");
        System.out.println("2 - Desfazer última operação");
        System.out.println("0 - Voltar");
        System.out.print("Opção: ");

        int op = Integer.parseInt(sc.nextLine());

        if (op == 1) {
            historico.exibir();
        } 
        else if (op == 2) {
            historico.desfazer();
        }
    }
}