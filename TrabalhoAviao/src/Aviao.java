public class Aviao {

    private int codigo;
    private String modelo;
    private String fabricante;
    private int capacidade;

    public Aviao(int codigo, String modelo,
                 String fabricante, int capacidade) {
        this.codigo = codigo;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.capacidade = capacidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    @Override
    public String toString() {
        return "Código: " + codigo +
               " | Modelo: " + modelo +
               " | Fabricante: " + fabricante +
               " | Capacidade: " + capacidade;
    }
}
