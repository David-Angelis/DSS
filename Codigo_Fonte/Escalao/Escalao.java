package Escalao;

public abstract class Escalao {
    private int Idade;
    private int numQuadrantes;
    private int numJogadores;

   public Escalao(int ano,int num,int numj){
       this.Idade=ano;
       this.numJogadores=numj;
       this.numQuadrantes=num;
   }
    public int getIdade(){
        return this.Idade;
    }
    public void setIdade(int ano){
        this.Idade = ano;
    }
    public int getQuadrantes(){
        return this.numQuadrantes;
    }
    public void setQuadrantes(int quad){
        this.numQuadrantes = quad;
    }
    public int getNumJogadores(){
        return this.numJogadores;
    }
    public void setNumJogadores(int jog){
        this.numJogadores = jog;
    }
    
    
    public abstract boolean validaEscalao(int anoNascimento, int epoca);
}
