/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Escalao;

/**
 *
 * @author David
 */
public class PreEscolaB extends Escalao {
    
    public PreEscolaB(){
        super(7,1,5);
    }
    
    @Override
    public boolean validaEscalao(int anoNascimento, int epoca) {
        return (7 == (epoca-anoNascimento ));
    }
    public String toString(){
        return this.getClass().getName();
    }
}
