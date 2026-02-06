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
        contadorChegada = 0;
        sc = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = sc.nextInt();

            switch (opcao) {
                case 1 -> menuAvioes();
                case 2 -> menuPassageiros();
                case 3 -> menuHistorico();
                case 0 -> System.out.println("Encerrando sistema....");
                default -> System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);

        sc.close();
    }

    private void exibirMenuPrincipal() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1 - Gerenciar Avioes");
        System.out.println("2 - Gerenciar Passageiros");
        System.out.println("3 - Historico");
        System.out.println("0 - Sair");
        System.out.print("Opcao: ");
    }


    private void menuAvioes() {
        System.out.println("\n=== MENU AVIOES ===");
        System.out.println("1 - Cadastrar aviao");
        System.out.println("2 - Listar avioes");
        System.out.println("3 - Remover aviao");
        System.out.print("Opcao: ");

        int op = sc.nextInt();
        sc.nextLine();

        switch (op) {
            case 1 -> cadastrarAviao();
            case 2 -> gerAvioes.listarAvioes();
            case 3 -> removerAviao();
            default -> System.out.println("Opcao invalida.");
        }
    }

    private void cadastrarAviao() {
        System.out.print("Codigo: ");
        String codigo = sc.nextLine();
        sc.nextLine();

        System.out.print("Modelo: ");
        String modelo = sc.nextLine();

        System.out.print("Fabricante: ");
        String fabricante = sc.nextLine();

        System.out.print("Capacidade: ");
        int capacidade = sc.nextInt();
        sc.nextLine();

         gerAvioes.inserirAviao(new Aviao(codigo, modelo, fabricante, capacidade));
         historico.registrar("Cadastrou aviao codigo " + codigo);
    }

    private void removerAviao() {
        System.out.print("Codigo do aviao: ");
        String codigo = sc.nextLine();
        gerAvioes.removerAviao(codigo);
        historico.registrar("Removeu aviao codigo " + codigo);
    }

   

    private void menuPassageiros() {
        System.out.println("\n=== MENU PASSAGEIROS ===");
        System.out.println("1 - Passageiro comum");
        System.out.println("2 - Prioridade ALTA");
        System.out.println("3 - Prioridade MEDIA");
        System.out.println("4 - Prioridade BAIXA");
        System.out.println("5 - Embarcar proximo");
        System.out.print("Opcao: ");

        int op = sc.nextInt();
        sc.nextLine();

        if (op >= 1 && op <= 4) {
            cadastrarPassageiro(op);
        } 
        else if (op == 5) {
            embarcarPassageiro();
        } else {
            System.out.println("Opcao invalida.");
        }
    }

    private void cadastrarPassageiro(int tipo) {
        String idChegada = "CH" + contadorChegada;
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Numero do voo: ");
        int voo = sc.nextInt();

        Prioridade prioridade =
                (tipo == 2) ? Prioridade.Alta :
                (tipo == 3) ? Prioridade.Media :
                (tipo == 4) ? Prioridade.Baixa :
                Prioridade.Baixa;

        Passageiro p = new Passageiro(nome, cpf, voo, prioridade, idChegada, contadorChegada);
        contadorChegada ++;
        if (tipo == 1) {
            filaComum.add(p);
        } 
        else {
            filaPrioritaria.add(p);
        }

        historico.registrar("Inseriu passageiro " + nome);
        historico.registrar("ID: " +idChegada);
    }

    private void embarcarPassageiro() {
        Passageiro p;

        if (!filaPrioritaria.isEmpty()) {
            p = filaPrioritaria.poll();
        } else {
            p = filaComum.poll();
        }

        if (p != null) {
            System.out.println("Embarcou: " + p);
            historico.registrar("Embarcou passageiro " + p.getNome());
        } 
        else {
            System.out.println("Nenhum passageiro aguardando.");
        }
    }

   

    private void menuHistorico() {
        System.out.println("\n=== HISTORICO ===");
        System.out.println("1 - Exibir historico");
        System.out.println("2 - Desfazer ultima operacao");
        System.out.print("Opcao: ");

        int op = sc.nextInt();

        if (op == 1) {
            historico.exibir();
        } else if (op == 2) {
            historico.desfazer();
        } else {
            System.out.println("Opcao invalida.");
        }
    }
}
