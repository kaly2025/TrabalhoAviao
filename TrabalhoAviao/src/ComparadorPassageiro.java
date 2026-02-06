import java.util.Comparator;

public class ComparadorPassageiro implements Comparator<Passageiro> {

    @Override
    public int compare(Passageiro p1, Passageiro p2) {
        return p1.getPrioridade().compareTo(p2.getPrioridade());
    }
}