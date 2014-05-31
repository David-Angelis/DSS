package Escalao;

public class EscolaB extends Escalao {
    
    public EscolaB(){
        super(9,2,7);
    }
    
    @Override
    public boolean validaEscalao(int anoNascimento, int epoca) {
        return (9 == (epoca-anoNascimento));
    }
    public String toString(){
        return this.getClass().getName();
    }
}