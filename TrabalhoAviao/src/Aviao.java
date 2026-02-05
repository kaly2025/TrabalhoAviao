public class Aviao {
    private int codigo;
    private String modelo;
    private String fabricante;
    private int capacidadePassageiros;

    public Aviao(int codigo , String modelo, String fabricante, int capacidadePassageiros) {
        this.codigo = codigo;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.capacidadePassageiros = capacidadePassageiros;
    }
    public int getCodigo() {
        return this.codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getFabricante() {
        return fabricante;
    }
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
    public int getCapacidadePassageiros() {
        return capacidadePassageiros;
    }
    public void setCapacidadePassageiros(int capacidadePassageiros) {
        this.capacidadePassageiros = capacidadePassageiros;
    }
    


}
