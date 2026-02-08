import java.util.Collections;
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
            System.out.println(">> Nenhum avião cadastrado.");
            return;
        }
        System.out.println("--- Lista de Aviões ---");
        for (Aviao a : avioes) {
            System.out.println(a);
        }
    }

    public Aviao buscarPorCodigo(String codigo) {
        for (Aviao a : avioes) {
            if (a.getCodigo().equalsIgnoreCase(codigo)) { 
                return a;
            }
        }
        return null;
    }
    public List<Aviao> getAvioes() {
        return Collections.unmodifiableList(avioes);
    }

    public boolean isEmpty(){
        return avioes.isEmpty();
    }

    public boolean removerAviao(String codigo) {
        Aviao a = buscarPorCodigo(codigo);
        if (a != null) {
            avioes.remove(a);
            System.out.println(">> Avião removido com sucesso.");
            return true; 
        } 
        else {
            System.out.println(">> Avião não encontrado.");
            return false;
        }
    }
}