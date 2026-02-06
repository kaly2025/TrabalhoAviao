import java.util.Stack;

public class PilhaHistoricoOperacoes {

    private Stack<String> historicoDeOperacoes ;

    public  PilhaHistoricoOperacoes() {
        historicoDeOperacoes = new Stack<>();
    }

    public void registrar(String operacao) {
        historicoDeOperacoes.push(operacao);
    }

    public void exibir() {
        if (historicoDeOperacoes.isEmpty()) {
            System.out.println("Histórico vazio.");
            return;
        }

        System.out.println("=== HISTÓRICO ===");
        for (String op : historicoDeOperacoes) {
            System.out.println(op);
        }
    }

    public void desfazer() {
        if (historicoDeOperacoes.isEmpty()) {
            System.out.println("Nada para desfazer.");
            return;
        }

        System.out.println("Operação desfeita: " + historicoDeOperacoes.pop());
    }
}
