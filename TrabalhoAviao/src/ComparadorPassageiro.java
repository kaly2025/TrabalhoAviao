import java.util.Comparator;

public class ComparadorPassageiro implements Comparator<Passageiro> {

    @Override
    public int compare(Passageiro p1, Passageiro p2) {

        int prioridade = p1.getPrioridade().compareTo(p2.getPrioridade());
        if (prioridade != 0) {
            return -prioridade;
        }

        return Long.compare(p1.getOrdemChegada(), p2.getOrdemChegada());
    }
}
