import java.util.Stack;

public class PilhaHistoricoOperacoes {

    private Stack<Comando> historicoDeOperacoes;

    public PilhaHistoricoOperacoes() {
        historicoDeOperacoes = new Stack<>();
    }

    public void registrar(Comando comando) {
        historicoDeOperacoes.push(comando);
    }

    public void exibir() {
        if (historicoDeOperacoes.isEmpty()) {
            System.out.println("Histórico vazio.");
            return;
        }
        System.out.println("--- Log de Operações (Topo = Mais recente) ---");
        for (int i = historicoDeOperacoes.size() - 1; i >= 0; i--) {
            System.out.println(historicoDeOperacoes.get(i).getDescricao());
        }
    }

    public void desfazer() {
        if (historicoDeOperacoes.isEmpty()) {
            System.out.println(">> Nada para desfazer.");
            return;
        }
        Comando cmd = historicoDeOperacoes.pop();
        System.out.println(">> Desfazendo: " + cmd.getDescricao());
        cmd.desfazer(); 
    }
}