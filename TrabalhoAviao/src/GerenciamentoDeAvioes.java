import java.util.LinkedList;
import java.util.List;

public class GerenciamentoDeAvioes {

    private List<Aviao> avioes;

    public GerenciamentoDeAvioes() {
        avioes = new LinkedList<>();
    }

    public void inserirAviao(Aviao aviao) {
        avioes.add(aviao);
    }

    public void listarAvioes() {
        if (avioes.isEmpty()) {
            System.out.println("Nenhum aviao cadastrado.");
            return;
        }

        for (Aviao a : avioes) {
            System.out.println(a);
        }
    }

    public Aviao buscarPorCodigo(int codigo) {
        for (Aviao a : avioes) {
            if (a.getCodigo() == codigo) {
                return a;
            }
        }
        return null;
    }

    public void removerAviao(int codigo) {
        Aviao a = buscarPorCodigo(codigo);
        if (a != null) {
            avioes.remove(a);
            System.out.println("Aviao removido.");
        } else {
            System.out.println("Aviao não encontrado.");
        }
    }
}
