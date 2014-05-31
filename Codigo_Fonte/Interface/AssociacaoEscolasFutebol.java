/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Competicao.Campeonato;
import Competicao.Campo;
import Competicao.Competicao;
import Competicao.EliminatoriaDupla;
import Competicao.Eliminatorias;
import Competicao.Jogo;
import Competicao.Juiz;
import Competicao.Torneio;
import DAO.DAOCompeticao;
import DAO.DAOEscola;
import DAO.DAOGestor;
import DAO.DAOJuiz;
import Gestao.Equipa;
import Gestao.Escola;
import Gestao.Jogador;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import javax.swing.JOptionPane;

/**
 *
 * @author Rui
 */
public class AssociacaoEscolasFutebol extends Observable {

    private DAOJuiz DAOJuiz;
    private DAOCompeticao DAOCompeticao;
    private DAOEscola DAOEscola;
    private DAOGestor DAOGestor;
    /*public DAOCompeticao_Passada _unnamed_DAOCompeticao_Passada_;*/
    private String tipoUtilizador;

    public AssociacaoEscolasFutebol() {
        this.tipoUtilizador = "";
        this.DAOJuiz = new DAOJuiz();
        this.DAOGestor=new DAOGestor();
        this.DAOCompeticao = new DAOCompeticao();
        this.DAOEscola = new DAOEscola();
    }

    public DAOJuiz getDAOJuiz() {
        return DAOJuiz;
    }

    public DAOCompeticao getDAOCompeticao() {
        return DAOCompeticao;
    }

    public DAOEscola getDAOEscola() {
        return DAOEscola;
    }

    String getTipoUtilizador() {
        return this.tipoUtilizador;
    }

    void setTipoUtilizador(String tipoUtilizador) {
        this.tipoUtilizador = tipoUtilizador;
    }

    public void SetResultado(String cod_competicao,String cod_jornada,String Equipa1, String Equipa2, int nGolo1, int nGolo2) throws Exception {
        DAOCompeticao.get(cod_competicao).setResultado(Equipa2,cod_jornada,Equipa1, Equipa2, nGolo1, nGolo2);
       /* this.setChanged();
        this.notifyObservers();*/
    }

    public void CriarCompeticao(String Nome, String escalao, Collection<String> equipa, int tipo, String torneio, String date) throws Exception {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        if (tipo == 0) {
            if (!DAOCompeticao.containsKey(escalao)) {
                Competicao comp = new Campeonato(escalao, Nome, escalao, Integer.toString(year));
                DAOCompeticao.put(escalao, comp);
                Campeonato comp1 = (Campeonato) comp;
                comp1.setEquipa(equipa, escalao);
                int i = comp1.geraCalendario(date);
                if (i == 0) {
                    DAOCompeticao.remove(comp1.getCod_competicao());
                    throw new Exception("Não foi possivel criar o competicao");
                }
            } else {
                throw new Exception("Competicao ja existe");
            }
        } else {
            if (torneio.equals("Eliminatórias")) {
                Competicao comp = new Eliminatorias(Nome, Nome, escalao, Integer.toString(year));
                if (!ExisteCompeticao(Nome)) {
                    DAOCompeticao.put(Nome, comp);
                    Torneio comp1 = (Torneio) comp;
                    comp1.setEquipa(equipa, Nome);
                    int i = comp1.geraCalendario(date);
                    if (i == 0) {
                        DAOCompeticao.remove(comp1.getCod_competicao());
                        throw new Exception("Não foi possivel criar o competicao");
                    }
                } else {
                    throw new Exception("Competicao ja existe");
                }
            }else{
                Competicao comp = new EliminatoriaDupla(Nome, Nome, escalao, Integer.toString(year));
                if (!ExisteCompeticao(Nome)) {
                    DAOCompeticao.put(Nome, comp);
                    Torneio comp1 = (Torneio) comp;
                    comp1.setEquipa(equipa, Nome);
                    int i = comp1.geraCalendario(date);
                    if (i == 0) {
                        DAOCompeticao.remove(comp1.getCod_competicao());
                        throw new Exception("Não foi possivel criar o competicao");
                    }
                } else {
                    throw new Exception("Competicao ja existe");
                }
            }
        }
    }

    public boolean ExisteCompeticao(String nome) {
        return DAOCompeticao.containsKey(nome);
    }

    public void RemoveCompeticao(String cod_competicao) {
        DAOCompeticao.removerJogos(cod_competicao);
        DAOCompeticao.removerJornadas(cod_competicao);
        DAOCompeticao.remove(cod_competicao);
        this.setChanged();
        this.notifyObservers();
    }

    public void UpdateCompeticao(String cod_Competicao, String nome, String ano) {
        Competicao comp = DAOCompeticao.get(cod_Competicao);
        comp.updateCompeticao(nome, ano);
        DAOCompeticao.put(cod_Competicao, comp);
        this.setChanged();
        this.notifyObservers();
    }

    public Competicao getCompeticao(String nome) {
       Competicao a=DAOCompeticao.get(nome);
       if(a!=null)
        return a;
       else return null;
    }

    public Collection<String> getMelhorMarcadores(String cod_competicao) {
        Competicao comp = DAOCompeticao.get(cod_competicao);
        if(comp!=null){
        return comp.getMelhorMarcadorCompeticao(cod_competicao);
        }
        return null;
    }

    public Map<String, Collection<Jogo>> getCalendarioCompeticao(String cod_competicao) {
        Competicao comp = DAOCompeticao.get(cod_competicao);
        if (comp != null) {
            return comp.getJornadas(cod_competicao);
        } else {
            return null;
        }
    }

    public void RemoverEscola(String escola) {
        DAOEscola.remove(escola);
    }

    public void UpdateEscola() {
    }

    public Equipa getEquipa(String escola, String equipa) {
        return DAOEscola.get(escola).getEquipa(equipa);
    }

    public void RemoveEquipa(String escola, String equipa) {
        DAOEscola.get(escola).removeEquipa(equipa);
        this.setChanged();
        this.notifyObservers();
    }

    public void UpdateEquipa() {
    }

    public Escola getEscola(String escola) {
        return DAOEscola.get(escola);
    }

    public Collection<String> getClassificacao(String cod_competicao) {
        if (DAOCompeticao.get(cod_competicao) != null) {
            return DAOCompeticao.get(cod_competicao).getClasificacao(cod_competicao);
        } else {
            return null;
        }
    }

    public void SetGolo(String cod_competicao, HashMap<String, Integer> Jogadores_1, HashMap<String,Integer> Jogadores_2) throws SQLException {
        DAOCompeticao.get(cod_competicao).getMarcador().actualizaMarcador(cod_competicao, Jogadores_1, Jogadores_2);
        /*this.setChanged();
        this.notifyObservers();*/
    }

    public void CriarJuiz(String username, String nome, String password) {

        if (!ExisteJuiz(username)) {
            Juiz j = new Juiz(username, nome, password);
            DAOJuiz.put(username, j);
            this.setChanged();
            this.notifyObservers();
        }
    }

    public void RemoverJuiz(String username) {
        DAOJuiz.remove(username);
        this.setChanged();
        this.notifyObservers();
    }

    public boolean ExisteJuiz(String username) {
        return DAOJuiz.containsKey(username);
    }

    public boolean login(String username, String password) {
        return DAOJuiz.login(username, password);
    }
    
    public boolean loginGestor(String username, String password){
        return DAOGestor.login(username, password);
    }
    

    public void criaEscola(String nome, String endereco, String localidade)throws Exception{
        if (DAOEscola.containsKey(nome)) {
            throw new Exception("Escola ja existe");
        }
        Escola esc = new Escola(nome, nome, endereco, localidade);
        DAOEscola.put(esc.getCod_Escola(), esc);
        this.setChanged();
        this.notifyObservers();

    }

    public void removeCampo(String campo, String escola) {
        DAOEscola.get(escola).DAOCampo.remove(campo);
        this.setChanged();
        this.notifyObservers();
    }

    public void addCampo(Campo campo, String escola) throws Exception {
        Escola e = DAOEscola.get(escola);
        if (e.existeCampo(campo.getNome())) {
            throw new Exception("Campo já existe!");
        }
        e.getDAOCampo().put(campo.getNome(), campo);
        this.setChanged();
        this.notifyObservers();
    }

    public void criaEquipa(String nomeEscola, String cod_equipa, String nome, String escalao) throws Exception {
        Escola escola = DAOEscola.get(nomeEscola);
        if (!escola.validaEquipa(cod_equipa)) {
            Equipa equipa = new Equipa(cod_equipa, nome, escalao, nomeEscola);
            escola.DAOEquipa.put(cod_equipa, equipa);
            this.setChanged();
            this.notifyObservers();
        } else {
            throw new Exception("Equipa já existe!");
        }
    }

    public void addJogador(String nome, String dataNascimento, String codEquipa, String nomeEscola) throws Exception {
        Equipa equipa = DAOEscola.get(nomeEscola).getEquipa(codEquipa);
        Jogador j = new Jogador(nome + "-" + dataNascimento, nome, dataNascimento, codEquipa);
        if (!equipa.validaJogador(j)) {
            throw new Exception("Jogador não se enquadra no escalão da equipa!");
        }
        equipa.addJogador(j);
        this.setChanged();
        this.notifyObservers();
    }

    public void removeJogador(String NomeJogador, String AnoNascimento, String equipa, String Escola) {
        DAOEscola.get(Escola).removeJogador(NomeJogador + "-" + AnoNascimento, equipa);
        this.setChanged();
        this.notifyObservers();
    }

}
