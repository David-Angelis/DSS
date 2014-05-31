/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Escalao;

/**
 *
 * @author David
 */
public class PreEscolaA extends Escalao {
    
    public PreEscolaA(){
        super(8,1,5);
    }
    
    @Override
    public boolean validaEscalao(int anoNascimento, int epoca) {
        return (8 == (epoca-anoNascimento));
    }
    public String toString(){
        return this.getClass().getName();
    }
}
