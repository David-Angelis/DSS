/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Competicao;

/**
 *
 * @author David
 */
public class Jogo {

    private String _cod_Jogo;
    private String _cod_Jornada;
    private String _cod_Competicao;
    private String _equipa1;
    private String _equipa2;
    private int _golo1;
    private int _golo2;
    private String _cod_Campo;
    
    public Jogo(String cod_Jogo,String cod_Jornada,String cod_Competicao,int golo1,int golo2,String equipa1,String equipa2,String cod_Campo){
        this._cod_Jogo=cod_Jogo;
        this._cod_Jornada=cod_Jornada;
        this._cod_Competicao=cod_Competicao;
        this._equipa1=equipa1;
        this._equipa2=equipa2;
        this._golo1=golo1;
        this._golo2=golo2;
        this._cod_Campo=cod_Campo;
    }
    public int hashCode() {
        int lHashCode = 0;
        if (this.getCod_Jogo() != null) {
            lHashCode += this.getCod_Jogo().hashCode();
        }
        if (this.getCod_Jornada() != null) {
            lHashCode += this.getCod_Jornada().hashCode();
        }
        if (this.getCod_Competicao() != null) {
            lHashCode += this.getCod_Competicao().hashCode();
        }
        if (this.getCod_Campo() != null) {
            lHashCode += this.getCod_Campo().hashCode();
        }
        if (lHashCode == 0) {
            lHashCode = super.hashCode();
        }
        return lHashCode;
    }

    public boolean equals(Object aObject) {
        if (this == aObject) {
            return true;
        } else if (aObject instanceof Jogo) {
            Jogo lJogoObject = (Jogo) aObject;
            boolean lEquals = true;
            lEquals &= ((this.getCod_Jogo() == lJogoObject.getCod_Jogo())
                    || (this.getCod_Jogo() != null && this.getCod_Jogo().equals(lJogoObject.getCod_Jogo())));
            lEquals &= ((this.getCod_Jornada() == lJogoObject.getCod_Jornada())
                    || (this.getCod_Jornada() != null && this.getCod_Jornada().equals(lJogoObject.getCod_Jornada())));
            lEquals &= ((this.getCod_Competicao() == lJogoObject.getCod_Competicao())
                    || (this.getCod_Competicao() != null && this.getCod_Competicao().equals(lJogoObject.getCod_Competicao())));
            lEquals &= ((this.getCod_Campo() == lJogoObject.getCod_Campo())
                    || (this.getCod_Campo() != null && this.getCod_Campo().equals(lJogoObject.getCod_Campo())));
            return lEquals;
        }
        return false;
    }

    /**
     * @return the _cod_Jogo
     */
    public String getCod_Jogo() {
        return _cod_Jogo;
    }

    /**
     * @param cod_Jogo the _cod_Jogo to set
     */
    public void setCod_Jogo(String cod_Jogo) {
        this._cod_Jogo = cod_Jogo;
    }

    /**
     * @return the _cod_Jornada
     */
    public String getCod_Jornada() {
        return _cod_Jornada;
    }

    /**
     * @param cod_Jornada the _cod_Jornada to set
     */
    public void setCod_Jornada(String cod_Jornada) {
        this._cod_Jornada = cod_Jornada;
    }

    /**
     * @return the _cod_Competicao
     */
    public String getCod_Competicao() {
        return _cod_Competicao;
    }

    /**
     * @param cod_Competicao the _cod_Competicao to set
     */
    public void setCod_Competicao(String cod_Competicao) {
        this._cod_Competicao = cod_Competicao;
    }

    /**
     * @return the _golo1
     */
    public int getGolo1() {
        return _golo1;
    }

    /**
     * @param golo1 the _golo1 to set
     */
    public void setGolo1(int golo1) {
        this._golo1 = golo1;
    }

    /**
     * @return the _golo2
     */
    public int getGolo2() {
        return _golo2;
    }

    /**
     * @param golo2 the _golo2 to set
     */
    public void setGolo2(int golo2) {
        this._golo2 = golo2;
    }

    /**
     * @return the _cod_Campo
     */
    public String getCod_Campo() {
        return _cod_Campo;
    }

    /**
     * @param cod_Campo the _cod_Campo to set
     */
    public void setCod_Campo(String cod_Campo) {
        this._cod_Campo = cod_Campo;
    }

    /**
     * @return the _equipa1
     */
    public String getEquipa1() {
        return _equipa1;
    }

    /**
     * @param equipa1 the _equipa1 to set
     */
    public void setEquipa1(String equipa1) {
        this._equipa1 = equipa1;
    }

    /**
     * @return the _equipa2
     */
    public String getEquipa2() {
        return _equipa2;
    }

    /**
     * @param equipa2 the _equipa2 to set
     */
    public void setEquipa2(String equipa2) {
        this._equipa2 = equipa2;
    }
    
    public void setResultado(int nGolo1,int nGolo2){
        _golo1=nGolo1;
        _golo2=nGolo2;
    }
    
    public Jogo RegistaAlteracao(int golo1,int golo2,String cod_equipa1,String cod_equipa2){
        this.setGolo1(golo1);
        this.setGolo2(golo2);
        this.setEquipa1(cod_equipa1);
        this.setEquipa2(cod_equipa2);
        return this;
        
    }
    
}