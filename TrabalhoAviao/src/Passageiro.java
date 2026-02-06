public class Passageiro {

    private String nome;
    private String cpf;
    private int numeroVoo;
    private Prioridade prioridade;
    private long ordemChegada;

    public Passageiro(String nome, String cpf, int numeroVoo,
                      Prioridade prioridade, long ordemChegada) {
        this.nome = nome;
        this.cpf = cpf;
        this.numeroVoo = numeroVoo;
        this.prioridade = prioridade;
        this.ordemChegada = ordemChegada;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public int getNumeroVoo() {
        return this.numeroVoo;
    }

    public Prioridade getPrioridade() {
        return this.prioridade;
    }

    public long getOrdemChegada() {
        return this.ordemChegada;
    }

    // setters apenas do que pode mudar
    public void setNumeroVoo(int numeroVoo) {
        this.numeroVoo = numeroVoo;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public String getInformacoes() {
        return "Nome: " + nome +
               ", CPF: " + cpf +
               ", Voo: " + numeroVoo +
               ", Prioridade: " + prioridade;
    }

    public String toString(Prioridade prioridade) {
        if (prioridade == Prioridade.Alta) {
            return "[Classe:ALTA(*Vip)] " + getInformacoes();
        } else if (prioridade == Prioridade.Media) {
         return "[Classe:MÉDIA] " + getInformacoes();
        } else {
            return "[Classe:BAIXA] " + getInformacoes(); 
        }
    }
}
