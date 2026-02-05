public class Passageiro {
    
   private String nome;
   private String documentoVoo;
   private int numeroVoo;
   private String idChegada;

   private Prioridade atualPrioridade;

   public Passageiro(String nome, String documentoVoo, int numeroVoo, String idChegada){
    this.nome = nome;
    this.documentoVoo = documentoVoo;
    this.numeroVoo = numeroVoo;
    this.idChegada = idChegada;
   }


   public String getNome(){
    return nome;
   }

   public String getDocumento(){
    return documentoVoo;
   }

   public int getNumeroVoo(){
    return numeroVoo;
   }

   public String getId(){
    return idChegada;
   }

   public Prioridade getPrioridade(){
    return atualPrioridade;
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
}
