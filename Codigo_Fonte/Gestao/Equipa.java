/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestao;

import DAO.DAOJogador;
import Escalao.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

/**
 *
 * @author David
 */
public class Equipa {
    
    //private static int codigo = 1;
    private String nome;
    private String codEquipa;
    private String codEscola;
    private Escalao escalao;
    private DAOJogador DAOJogador;
    
    public Equipa(String codigo,String nome,String Escalao, String cod_Escola) {
        this.codEquipa = codigo;
        this.nome = nome;
        this.codEscola = cod_Escola;
        this.DAOJogador = new DAOJogador();
        switch (Escalao) {
            case "Escalao.EscolaA":
                escalao = new EscolaA();
                break;
            case "Escalao.EscolaB":
                escalao = new EscolaB();
                break;
            case "Escalao.InfantisA":
                escalao = new InfantisA();
                break;
            case "Escalao.InfantisB":
                escalao = new InfantisB();
                break;
            case "Escalao.IniciadosA":
                escalao = new IniciadosA();
                break;
            case "Escalao.IniciadosB":
                escalao = new IniciadosB();
                break;
            case "Escalao.MinisA":
                escalao = new MinisA();
                break;
            case "Escalao.MinisB":
                escalao = new MinisB();
                break;
            case "Escalao.PreEscolaA":
                escalao = new PreEscolaA();
                break;
            default:
                //(Escalao.equals("Escalao.PreEscolaB")) {
                escalao = new PreEscolaB();
                break;
        }
    }
    
  /*  public Equipa(String nome,String Escalao, String cod_Escola) {
        this.codEquipa = Integer.toString(codigo);
        Equipa.codigo++;
        this.nome = nome;
        this.codEscola = cod_Escola;
        this.DAOJogador = new DAOJogador();
        switch (Escalao) {
            case "Escalao.EscolaA":
                escalao = new EscolaA();
                break;
            case "Escalao.EscolaB":
                escalao = new EscolaB();
                break;
            case "Escalao.InfantisA":
                escalao = new InfantisA();
                break;
            case "Escalao.InfantisB":
                escalao = new InfantisB();
                break;
            case "Escalao.IniciadosA":
                escalao = new IniciadosA();
                break;
            case "Escalao.IniciadosB":
                escalao = new IniciadosB();
                break;
            case "Escalao.MinisA":
                escalao = new MinisA();
                break;
            case "Escalao.MinisB":
                escalao = new MinisB();
                break;
            case "Escalao.PreEscolaA":
                escalao = new PreEscolaA();
                break;
            default:
                //(Escalao.equals("Escalao.PreEscolaB")) {
                escalao = new PreEscolaB();
                break;
        }
    }
    */
    
    /* GET & SET*/
    public String getNome() {
        return this.nome;
    }

    public void setNome(String aNome) {
        this.nome = aNome;
    }

    public String getCodEquipa() {
        return codEquipa;
    }
    
    public void setCod_Equipa(String codEquipa) {
        this.codEquipa = codEquipa;
    }

    public String getCod_Escola() {
        return codEscola;
    }
    
    public void setCod_Escola(String cod_Escola) {
        this.codEscola = cod_Escola;
    }
    
    
    public Escalao getEscalao() {
        return escalao;
    }
    
    public void setEscalao(Escalao escalao) {
        this.escalao = escalao;
    }
    
    public DAOJogador getDAOJogador() {
        return DAOJogador;
    }
    
    public void setDAOJogador(DAOJogador DAOJogador) {
        this.DAOJogador = DAOJogador;
    }
    
    /*METODOS*/
    
    public boolean validaJogador (Jogador j){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return (this.escalao.validaEscalao(Integer.parseInt(j.getDataNascimento()),year));
        }
    
    public void addJogador (Jogador j){
        if(validaJogador(j)){
            DAOJogador.put(j.getCodJogador(), j);
        }
    }
    
    public void removeJogadores(){
        DAOJogador.removeAll(this.codEquipa);
    }
    public void removeJogador(String cod){
        DAOJogador.remove(cod);
    }
    
    public Collection<Jogador> getJogadores(){
        Collection<Jogador> jogadores=new ArrayList<Jogador>();
        jogadores = DAOJogador.values(this.codEquipa);
        return jogadores;
    }
    
    public Jogador getJogador(String nome){
        Jogador j = DAOJogador.get(nome);
        return j;
    }
   
    
}
