import java.util.List;

public class GerenciamentoDeAvioes {
    
    int quantidadeAvioes;
    List<Aviao> listaAvioes;

    public GerenciamentoDeAvioes(int quantidadeAvioes, List<Aviao> listaAvioes) {
        this.quantidadeAvioes = quantidadeAvioes;
        this.listaAvioes = listaAvioes;
    }
    public int getQuantidadeAvioes() {
        return quantidadeAvioes;
    }
    public void setQuantidadeAvioes(int quantidadeAvioes) {
        this.quantidadeAvioes = quantidadeAvioes;
    }
    public List<Aviao> getListaAvioes() {
        return listaAvioes;
    }
    public void setListaAvioes(List<Aviao> listaAvioes) {
        this.listaAvioes = listaAvioes;
    }
    public void InserirAviao(Aviao aviao){
        this.listaAvioes.add(aviao);
        this.quantidadeAvioes++;
    }
    public void RemoverAviao(Aviao aviao){
        this.listaAvioes.remove(aviao);
        this.quantidadeAvioes--;
    }
    public Aviao BuscarAviaoPorCodigo(int codigo){
        for(Aviao aviao : this.listaAvioes){
            if(aviao.getCodigo() == codigo){
                return aviao;
            }
        }
        return null;
    }
    
}
