public class Main {
    public static void main(String[] args) throws Exception {
        Passageiro p1 = new Passageiro(1, "Alice", 30, Prioridade.ALTA); 
        Passageiro p2 = new Passageiro(2, "Bob", 25, Prioridade.MEDIA);
        Aviao a1 = new Aviao(101, "A320", "Airbus", 180);
        System.out.println("Hello, World!");
        System.out.println(p1.getId() + " " + p1.getNome() + " " + p1.getIdade() + " " + p1.getPrioridade());
        System.out.println(p2.getId() + " " + p2.getNome() + " " + p2.getIdade() + " " + p2.getPrioridade());
        System.out.println(a1.getCodigo() + " " + a1.getModelo() + " " + a1.getFabricante() + " " + a1.getCapacidadePassageiros());
    }
}
