public class Passageiro {

    private String nome;
    private String cpf;
    private int numeroVoo;
    private String idChegada;
    private int ordemChegada;
    private Prioridade atualPrioridade;
    private String codigoAviao;

    public Passageiro(String nome, String cpf, int numeroVoo, Prioridade prioridade, String idChegada, int ordemChegada, String codigoAviao) {
        this.nome = nome;
        this.cpf = cpf;
        this.numeroVoo = numeroVoo;
        this.atualPrioridade = prioridade;
        this.idChegada = idChegada;
        this.ordemChegada = ordemChegada;
        this.codigoAviao = codigoAviao;
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
        return this.atualPrioridade;
    }

    public int getOrdemChegada() {
        return this.ordemChegada;
    }

    public void setNumeroVoo(int numeroVoo) {
        this.numeroVoo = numeroVoo;
    }

    public String getInformacoes() {
        return "Nome: " + nome +
               ", CPF: " + cpf +
               ", Voo: " + numeroVoo +
               ", Prioridade: " + atualPrioridade;
    }

    public String getCodigoAviao(){
        return codigoAviao;
    }

    public String getAtualClasse(){
        switch(atualPrioridade){
            case Alta:
                return "Primeira classe(VIP)";
            case Media:
                return "Classe Media";
            case Baixa:
                return "Classe Economica";
            default:
                return "N tem nada";
        }
    }
      @Override
    public String toString() {
        return "[" + idChegada + "] " + nome + " (" + atualPrioridade + ") - Voo: " + numeroVoo;
    }
}
