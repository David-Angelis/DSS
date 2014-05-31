/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Competicao;

/**
 *
 * @author David
 */
public class Juiz {
        private String _nome;
	private String _username;
	private String _password;
        
        public Juiz(String nome,String username,String pasword){
            _nome=nome;
            _username=username;
            _password=pasword;
        }
        
	public String getNome() {
		return this._nome;
	}

	public void setNome(String aNome) {
		this._nome = aNome;
	}

	public String getUsername() {
		return this._username;
	}

	public void setUsername(String aUsername) {
		this._username = aUsername;
	}

	public String getPassword() {
		return this._password;
	}

	public void setPassword(String aPassword) {
		this._password = aPassword;
	}

	public int hashCode() {
		int lHashCode = 0;
		if ( this._nome != null ) {
			lHashCode += this._nome.hashCode();
		}
		if ( this._username != null ) {
			lHashCode += this._username.hashCode();
		}
		if ( this._password != null ) {
			lHashCode += this._password.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Juiz) {
			Juiz lJuizObject = (Juiz) aObject;
			boolean lEquals = true;
			lEquals &= ((this._nome == lJuizObject._nome)
				|| (this._nome != null && this._nome.equals(lJuizObject._nome)));
			lEquals &= ((this._username == lJuizObject._username)
				|| (this._username != null && this._username.equals(lJuizObject._username)));
			lEquals &= ((this._password == lJuizObject._password)
				|| (this._password != null && this._password.equals(lJuizObject._password)));
			return lEquals;
		}
		return false;
	}
}
