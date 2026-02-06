public class Main {

    public static void main(String[] args) {
        GerenciarSistemaAereo sistema = new GerenciarSistemaAereo();
        System.out.println("===============================");
        System.out.println("SISTEMA DE GERENCIAMENTO DE VOOS");
        System.out.println("===============================");
        System.out.println("Seja bem-vindo ao sistema de gerenciamento de voos!" );
        System.out.println("Voce pode cadastrar avioes, gerenciar passageiros e acompanhar o historico de operacoes.");
        System.out.println("Explore as opcoes do menu para comecar a usar o sistema.");

        sistema.iniciar();
    }
}

