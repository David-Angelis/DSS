package Escalao;

//import java.util.Calendar;

/**
 *
 * @author David
 */
public class MinisA extends Escalao {
    
    public MinisA(){
        super(6,1,3); //2007
    }
    
    @Override
    public boolean validaEscalao(int anoNascimento, int epoca) {
        //int year = Calendar.getInstance().get(Calendar.YEAR);
        return (6 == (epoca-anoNascimento) );
    }
    public String toString(){
        return this.getClass().getName();
    }
}
