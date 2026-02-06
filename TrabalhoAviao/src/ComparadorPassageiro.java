import java.util.Comparator;

public class ComparadorPassageiro implements Comparator<Passageiro> {

    @Override
    public int compare(Passageiro p1, Passageiro p2) {
        int comparacaoPrioridade = p1.getPrioridade().compareTo(p2.getPrioridade());
        if (comparacaoPrioridade != 0) {
            return comparacaoPrioridade;
        }
        return Integer.compare(p1.getOrdemChegada(), p2.getOrdemChegada());
    }
}