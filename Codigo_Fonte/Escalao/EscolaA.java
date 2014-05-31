package Escalao;

public class EscolaA extends Escalao {
    
    public EscolaA(){
        super(10,2,7);
    }
    
    @Override
    public boolean validaEscalao(int anoNascimento, int epoca) {
        return (10 == (epoca-anoNascimento));
    }
    
    public String toString(){
        return this.getClass().getName();
    }
}

