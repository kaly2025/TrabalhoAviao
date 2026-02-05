
public class Aviao {

    //private int codigo;
    private String codigo;
    private String modelo;
    private String fabricante;
    private int capacidadePassageiros;

    public Aviao(String modelo, String codigo, String fabricante, int capacidadePassageiros) {
        this.codigo = codigo;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.capacidadePassageiros = capacidadePassageiros;
    }
    public String getCodigo() {
        return this.codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getModelo() {
        return this.modelo;
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
}
