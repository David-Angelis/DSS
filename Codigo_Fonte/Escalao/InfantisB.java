/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Escalao;

/**
 *
 * @author David
 */
public class InfantisB extends Escalao {
    
    public InfantisB(){
        super(11,2,7);
    }
    
    @Override
    public boolean validaEscalao(int anoNascimento, int epoca) {
        return (11 == (epoca-anoNascimento));
    }
    public String toString(){
        return this.getClass().getName();
    }
}
