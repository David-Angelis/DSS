/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Gestao;

/**
 *
 * @author David
 */
public class Gestor {
        private String _nome;
	private String _username;
	private String _password;
        
        public Gestor(String nome,String username,String pasword){
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

}
