import java.util.*;

public class GerenciarSistemaAereo {

    private Queue<Passageiro> filaComum;
    private PriorityQueue<Passageiro> filaPrioritaria;
    private GerenciamentoDeAvioes gerAvioes;
    private PilhaHistoricoOperacoes historico;

    private int contadorChegada;
    private Scanner sc;
    private Random rand;

    public GerenciarSistemaAereo() {
        filaComum = new LinkedList<>();
        filaPrioritaria = new PriorityQueue<>(new ComparadorPassageiro());
        gerAvioes = new GerenciamentoDeAvioes();
        historico = new PilhaHistoricoOperacoes();
        contadorChegada = 1; 
        rand = new Random();
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
            case 2 -> listarAvioesComPassageiros();
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

        Aviao novoAviao = new Aviao(codigo, modelo, fabricante, capacidade);
        gerAvioes.inserirAviao(novoAviao);
        System.out.println(">> Avião cadastrado com sucesso!");

        historico.registrar(new Comando() {
            @Override
            public void desfazer() {
                System.out.println("(Operação automática de desfeita)");
                gerAvioes.removerAviao(codigo);
            }

            @Override
            public String getDescricao() {
                return "Cadastrou avião: " + codigo + " (" + modelo + ")";
            }
        });
    }

    private void removerAviao() {
        System.out.print("Codigo do avião a remover: ");
        String codigo = sc.nextLine();
        Aviao aviaoRemovido = gerAvioes.buscarPorCodigo(codigo);
        
        if (aviaoRemovido != null) {
            boolean removeu = gerAvioes.removerAviao(codigo);
            if (removeu) {
                historico.registrar(new Comando() {
                    @Override
                    public void desfazer() {
                       
                        gerAvioes.inserirAviao(aviaoRemovido);
                        System.out.println(">> Avião " + codigo + " restaurado com sucesso.");
                    }

                    @Override
                    public String getDescricao() {
                        return "Removeu avião: " + codigo;
                    }
                });
            }
        } 
        else {
            System.out.println(">> Avião não encontrado.");
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
         if(gerAvioes.isEmpty()){
            System.out.println(">>Eh necessario cadastrar um aviao antes de registrar passageiro!");
            return;
        }

        String idChegada = "CH" + contadorChegada;
        System.out.print("Nome do Passageiro: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        int voo = 1000 + rand.nextInt(9000);
        System.out.println("Número do Voo: " + voo);

        Prioridade prioridade = Prioridade.Baixa; 
        
        if (prioritario) {
            System.out.println("Selecione a Prioridade: 1-Alta | 2-Media");
            int p = Integer.parseInt(sc.nextLine());
            prioridade = (p == 1) ? Prioridade.Alta : Prioridade.Media;
        }

        gerAvioes.listarAvioes();
            System.out.print("Digite o Codigo do aviao que o passageiro vai embarcar: ");
            String cod = sc.nextLine();

        Aviao aviao = gerAvioes.buscarPorCodigo(cod);
            if (aviao == null) {
                System.out.println(">> Avião não encontrado.");
                return;
            }
        
        int passageirosNoAviao = contarPassageirosDoAviao(aviao.getCodigo());

        if (passageirosNoAviao >= aviao.getCapacidade()) {
            System.out.println(">> Avião " + aviao.getCodigo() + " está LOTADO!");
            System.out.println(">> Capacidade: " + aviao.getCapacidade());
            System.out.println(">> Passageiros aguardando: " + passageirosNoAviao);
            return;
        }

        Passageiro p = new Passageiro(nome, cpf, voo, prioridade, idChegada, contadorChegada, aviao.getCodigo());
        contadorChegada++;

        Collection<Passageiro> filaDestino; 
        if (prioridade == Prioridade.Baixa) {
            filaComum.add(p);
            filaDestino = filaComum;
        } 
        else {
            filaPrioritaria.add(p);
            filaDestino = filaPrioritaria;
        }
        System.out.println(">> Passagem vendida! ID: " + idChegada + "| Para o aviao: " + aviao.getCodigo());
        
        historico.registrar(new Comando() {
            @Override
            public void desfazer() {
                filaDestino.remove(p);
                contadorChegada--; 
                System.out.println(">> Passagem de " + nome + " cancelada e removida da fila.");
            }

            @Override
            public String getDescricao() {
                return "Venda de passagem: " + nome + " (" + p.getPrioridade() + ")";
            }
        });
    }



    private void embarcarPassageiro() {
        Passageiro p = null;
        boolean eraPrioritario = false;

        if (!filaPrioritaria.isEmpty()) {
            p = filaPrioritaria.poll();
            eraPrioritario = true;
        } else if (!filaComum.isEmpty()) {
            p = filaComum.poll();
            eraPrioritario = false;
        }

        if (p != null) {
            System.out.println(">> EMBARCANDO: " + p.getNome() + " | Avião: " + p.getCodigoAviao());
            final Passageiro passageiroEmbarcado = p;
            final boolean veioDaPrioritaria = eraPrioritario;

            historico.registrar(new Comando() {
                @Override
                public void desfazer() {
                    if (veioDaPrioritaria) {
                        filaPrioritaria.add(passageiroEmbarcado); 
                    } 
                    else {
                        ((LinkedList<Passageiro>) filaComum).addFirst(passageiroEmbarcado);
                    }
                    System.out.println(">> Desembarque forçado: " + passageiroEmbarcado.getNome() + " retornou à fila.");
                }

                @Override
                public String getDescricao() {
                    return "Embarcou passageiro: " + passageiroEmbarcado.getNome();
                }
            });

        } 
        else {
            System.out.println(">> Não há passageiros aguardando embarque.");
        }
    }
    private void listarAvioesComPassageiros() {
        if (gerAvioes.isEmpty()) {
            System.out.println(">> Nenhum avião cadastrado.");
            return;
        }

        for (Aviao a : gerAvioes.getAvioes()) {
            System.out.println("\n" + a);

            boolean achou = false;

            System.out.println("  Passageiros na fila PRIORITÁRIA:");
            for (Passageiro p : filaPrioritaria) {
                if (p.getCodigoAviao().equalsIgnoreCase(a.getCodigo())) {
                    System.out.println("   - " + p);
                    achou = true;
                }
            }

            System.out.println("  Passageiros na fila COMUM:");
            for (Passageiro p : filaComum) {
                if (p.getCodigoAviao().equalsIgnoreCase(a.getCodigo())) {
                    System.out.println("   - " + p);
                    achou = true;
                }
            }

            if (!achou) {
                System.out.println("  (Nenhum passageiro aguardando embarque)");
            }
        }
    }

    private int contarPassageirosDoAviao(String codigoAviao) {
        int total = 0;

        for (Passageiro p : filaComum) {
            if (p.getCodigoAviao().equalsIgnoreCase(codigoAviao)) {
                total++;
            }
        }

        for (Passageiro p : filaPrioritaria) {
            if (p.getCodigoAviao().equalsIgnoreCase(codigoAviao)) {
                total++;
            }
        }

        return total;
    }



    
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