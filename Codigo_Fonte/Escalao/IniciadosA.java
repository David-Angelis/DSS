/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Escalao;

/**
 *
 * @author David
 */
public class IniciadosA extends Escalao {
    
    public IniciadosA(){
        super(14,2,7);
    }
    
    @Override
    public boolean validaEscalao(int anoNascimento, int epoca) {
        if ((epoca-anoNascimento) ==14 || (epoca-anoNascimento) == 15)
            return true;
        else
            return false;
    }
    public String toString(){
        return this.getClass().getName();
    }
}
