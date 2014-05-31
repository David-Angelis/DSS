/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Escalao;

/**
 *
 * @author David
 */
public class IniciadosB extends Escalao {
    
    public IniciadosB(){
        super(13,2,7);
    }
    
    @Override
    public boolean validaEscalao(int anoNascimento, int epoca) {
        return (13 == (epoca-anoNascimento));
    }
    public String toString(){
        return this.getClass().getName();
    }
}
