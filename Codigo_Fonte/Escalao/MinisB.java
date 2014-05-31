package Escalao;

public class MinisB extends Escalao {
    
    public MinisB(){
        super(5,1,3);
    }
    
    @Override
    public boolean validaEscalao(int anoNascimento, int epoca) {
        return (5 == (epoca-anoNascimento));
    }
    public String toString(){
        return this.getClass().getName();
    }
}
