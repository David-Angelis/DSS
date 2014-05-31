/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Competicao;

import DAO.DAOJogo;

/**
 *
 * @author David
 */
public class Jornada {
        private String _cod_Jornada;
	private String _cod_Competicao;
        private String date;
	private DAOJogo jogo;
        
        public Jornada(String cod_Jornada,String cod_Competicao,String data){
            this._cod_Jornada=cod_Jornada;
            this._cod_Competicao=cod_Competicao;
            this.date=data;
            jogo=new DAOJogo();
        }
        
	public int hashCode() {
		int lHashCode = 0;
		if ( this.getCod_Jornada() != null ) {
			lHashCode += this.getCod_Jornada().hashCode();
		}
		if ( this.getCod_Competicao() != null ) {
			lHashCode += this.getCod_Competicao().hashCode();
		}
		
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Jornada) {
			Jornada lJornadaObject = (Jornada) aObject;
			boolean lEquals = true;
			lEquals &= ((this.getCod_Jornada() == lJornadaObject.getCod_Jornada())
				|| (this.getCod_Jornada() != null && this.getCod_Jornada().equals(lJornadaObject.getCod_Jornada())));
			lEquals &= ((this.getCod_Competicao() == lJornadaObject.getCod_Competicao())
				|| (this.getCod_Competicao() != null && this.getCod_Competicao().equals(lJornadaObject.getCod_Competicao())));
			return lEquals;
		}
		return false;
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
     * @return the jogo
     */
    public DAOJogo getJogo() {
        return jogo;
    }

    /**
     * @param jogo the jogo to set
     */
    public void setJogo(DAOJogo jogo) {
        this.jogo = jogo;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }
    
}
