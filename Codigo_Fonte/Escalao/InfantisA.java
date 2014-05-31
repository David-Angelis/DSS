/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Escalao;

/**
 *
 * @author David
 */
public class InfantisA extends Escalao {
    
    public InfantisA(){
        super(12,2,7);
    }
    
    @Override
    public boolean validaEscalao(int anoNascimento, int epoca) {
        return (12 == (epoca-anoNascimento ));
    }
    public String toString(){
        return this.getClass().getName();
    }
}

