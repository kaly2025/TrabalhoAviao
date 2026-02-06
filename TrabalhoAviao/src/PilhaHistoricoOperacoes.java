import java.util.Stack;

public class PilhaHistoricoOperacoes {

    private Stack<String> historicoDeOperacoes;

    public PilhaHistoricoOperacoes() {
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
        System.out.println("--- Log de Operações (Topo = Mais recente) ---");
        for (int i = historicoDeOperacoes.size() - 1; i >= 0; i--) {
            System.out.println(historicoDeOperacoes.get(i));
        }
    }

    public void desfazer() {
        if (historicoDeOperacoes.isEmpty()) {
            System.out.println("Nada para desfazer.");
            return;
        }
        String op = historicoDeOperacoes.pop();
        System.out.println("Registro de operação removido do histórico: " + op);
        System.out.println("(Nota: Esta ação remove apenas o registro do log).");
    }
}