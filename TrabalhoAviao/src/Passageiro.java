
public class Passageiro{
    private int id;
    private String nome;
    private int idade;
    private Prioridade prioridade;

    public Passageiro(int id, String nome, int idade, Prioridade prioridade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.prioridade = prioridade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }
    public String ExibirInformacoes() {
        return "ID: " + id + ", Nome: " + nome + ", Idade: " + idade + ", Prioridade: " + prioridade;
    }

}
