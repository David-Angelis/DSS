/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Competicao;

import DAO.DAOCampo;
import DAO.DAOClassificacao;
import DAO.DAOEquipa;
import DAO.DAOJogo;
import DAO.DAOJornada;
import DAO.DAOMarcador;
import DAO.DAOOcupacao;
import Escalao.Escalao;
import Escalao.*;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author David
 */
public abstract class Competicao {

    /* private static int codigo=0;*/
    private String cod_competicao;
    private String nome;
    private Escalao escalao;
    private String ano;
    private DAOJornada jornada;
    private DAOEquipa equipa;
    private DAOMarcador marcador;
    private DAOClassificacao classificacao;
    private DAOJogo jogos;
    private DAOCampo campo;
    private DAOOcupacao ocupacao;

    public Competicao(String cod, String nome, String Escalao, String ano) {
        this.cod_competicao = cod;
        this.nome = nome;
        this.ano = ano;
        jornada = new DAOJornada();
        equipa = new DAOEquipa();
        marcador = new DAOMarcador();
        ocupacao = new DAOOcupacao();
        classificacao = new DAOClassificacao();
        campo = new DAOCampo();
        jogos = new DAOJogo();
        if (Escalao.equals("Escalao.EscolaA")) {
            escalao = new EscolaA();
        }
        if (Escalao.equals("Escalao.EscolaB")) {
            escalao = new EscolaB();
        }
        if (Escalao.equals("Escalao.InfantisA")) {
            escalao = new InfantisA();
        }
        if (Escalao.equals("Escalao.InfantisB")) {
            escalao = new InfantisB();
        }
        if (Escalao.equals("Escalao.IniciadosA")) {
            escalao = new IniciadosA();
        }
        if (Escalao.equals("Escalao.IniciadosB")) {
            escalao = new IniciadosB();
        }
        if (Escalao.equals("Escalao.MinisA")) {
            escalao = new MinisA();
        }
        if (Escalao.equals("Escalao.MinisB")) {
            escalao = new MinisB();
        }
        if (Escalao.equals("Escalao.PreEscolaA")) {
            escalao = new PreEscolaA();
        }
        if (Escalao.equals("Escalao.PreEscolaB")) {
            escalao = new PreEscolaB();
        }
    }
    /*   
     public Competicao(String nome, String Escalao,String ano) {
        
     System.out.println("Codigo" + codigo);
     this.nome = nome;
     this.ano=ano;
     jornada = new DAOJornada();
     equipa = new DAOEquipa();
     marcador = new DAOMarcador();
     ocupacao=new DAOOcupacao();
     classificacao = new DAOClassificacao();
     campo = new DAOCampo();
     if (Escalao.equals("Escalao.EscolaA")) {
     escalao = new EscolaA();
     }
     if (Escalao.equals("Escalao.EscolaB")) {
     escalao = new EscolaB();
     }
     if (Escalao.equals("Escalao.InfantisA")) {
     escalao = new InfantisA();
     }
     if (Escalao.equals("Escalao.InfantisB")) {
     escalao = new InfantisB();
     }
     if (Escalao.equals("Escalao.IniciadosA")) {
     escalao = new IniciadosA();
     }
     if (Escalao.equals("Escalao.IniciadosB")) {
     escalao = new IniciadosB();
     }
     if (Escalao.equals("Escalao.MinisA")) {
     escalao = new MinisA();
     }
     if (Escalao.equals("Escalao.MinisB")) {
     escalao = new MinisB();
     }
     if (Escalao.equals("Escalao.PreEscolaA")) {
     escalao = new PreEscolaA();
     }
     if (Escalao.equals("Escalao.PreEscolaB")) {
     escalao = new PreEscolaB();
     }
     }
     */

    public abstract int geraCalendario(String data);

    /**
     * @return the cod_competicao
     */
    public String getCod_competicao() {
        return cod_competicao;
    }

    /**
     * @param cod_competicao the cod_competicao to set
     */
    public void setCod_competicao(String cod_competicao) {
        this.cod_competicao = cod_competicao;
    }

    public DAOOcupacao getOcupacao() {
        return this.ocupacao;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the escalao
     */
    public Escalao getEscalao() {
        return escalao;
    }

    /**
     * @param escalao the escalao to set
     */
    public void setEscalao(Escalao escalao) {
        this.escalao = escalao;
    }

    /**
     * @return the jornada
     */
    public DAOJornada getDAOJornada() {
        return jornada;
    }

    public DAOJogo getJogos() {
        return this.jogos;
    }

    /**
     * @param jornada the jornada to set
     */
    public void setJornada(DAOJornada jornada) {
        this.jornada = jornada;
    }

    /**
     * @return the equipa
     */
    public DAOEquipa getEquipa() {
        return equipa;
    }

    /**
     * @param equipa the equipa to set
     */
    public void setEquipa(DAOEquipa equipa) {
        this.equipa = equipa;
    }

    /**
     * @return the marcador
     */
    public DAOMarcador getMarcador() {
        return marcador;
    }

    /**
     * @param marcador the marcador to set
     */
    public void setMarcador(DAOMarcador marcador) {
        this.marcador = marcador;
    }

    /**
     * @return the classificacao
     */
    public DAOClassificacao getDAOClassificacao() {
        return classificacao;
    }

    /**
     * @param classificacao the classificacao to set
     */
    public void setClassificacao(DAOClassificacao classificacao) {
        this.classificacao = classificacao;
    }

    /**
     * @return the campo
     */
    public DAOCampo getCampo() {
        return campo;
    }

    /**
     * @param campo the campo to set
     */
    public void setCampo(DAOCampo campo) {
        this.campo = campo;
    }

    /**
     * @return the ano
     */
    public String getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(String ano) {
        this.ano = ano;
    }

    public Map<String, Collection<Jogo>> getJornadas(String cod_) {
        Map<String, Collection<Jogo>> jornadas = new HashMap<String, Collection<Jogo>>();
        Collection<Jornada> jor = jornada.values(cod_competicao);
        for (Jornada a : jor) {
            jornadas.put(a.getCod_Jornada(), jogos.values(a.getCod_Competicao(), a.getCod_Jornada()));
        }
        return jornadas;
    }

    public Jornada getJornada(String cod_competicao, String date) {
        return jornada.get(cod_competicao, date);
    }

    public Jogo getJogo(String cod_jogo) {
        return jogos.get(cod_jogo);
    }

    public void setResultado(String cod_jogo,String cod_jornada ,String cod_equipa1, String cod_equipa2, int nGolo1, int nGolo2)throws Exception {
        if (this.getClass().getName().equals("Competicao.Campeonato")) {
            Jogo jogo = jogos.get(cod_competicao,cod_equipa1,cod_equipa2,cod_jornada);
            jogo.setResultado(nGolo1, nGolo2);
            jogos.put(jogo.getCod_Jogo(), jogo);
            classificacao.actualizaClassificacao(cod_competicao, cod_equipa1, nGolo1, cod_equipa2, nGolo2);
        } else if (this.getClass().getName().equals("Competicao.Eliminatorias")) {
            if (nGolo1 != nGolo2) {
                Jogo jogo = jogos.get(cod_competicao,cod_equipa1,cod_equipa2,cod_jornada);
                jogo.setResultado(nGolo1, nGolo2);
                jogos.put(jogo.getCod_Jogo(), jogo);
                Eliminatorias elim = (Eliminatorias) this;
                if (nGolo1 > nGolo2) {
                    elim.atribuirJogo(jogo.getCod_Jogo(), cod_equipa1);
                } else {
                    elim.atribuirJogo(jogo.getCod_Jogo(), cod_equipa2);
                }
            }else throw new Exception("Nao pode ser empate");
        }else {
             Jogo jogo_ = jogos.get(cod_competicao,cod_equipa1,cod_equipa2,cod_jornada);
            String[]aux=jogo_.getCod_Jogo().split("_");
            if(aux[3].equals("1")){ //1a mao
               // Jogo jogo = jogos.get(cod_jogo);
                jogo_.setResultado(nGolo1, nGolo2);
                jogos.put(jogo_.getCod_Jogo(), jogo_);
            }
            else if (aux[3].equals("0")){ //final
               if (nGolo1 != nGolo2) {
               // Jogo jogo = jogos.get(cod_jogo);
                jogo_.setResultado(nGolo1, nGolo2);
                jogos.put(jogo_.getCod_Jogo(), jogo_);
                Eliminatorias elim = (Eliminatorias) this;
                if (nGolo1 > nGolo2) {
                    elim.atribuirJogo(jogo_.getCod_Jogo(), cod_equipa1);
                } else {
                    elim.atribuirJogo(jogo_.getCod_Jogo(), cod_equipa2);
                }
            }else throw new Exception("Nao pode ser empate"); 
            }
            else{ //jogo 2a mao
                EliminatoriaDupla elim = (EliminatoriaDupla) this;
               // Jogo j = this.getJogo(aux[0]+aux[1]+aux[2]+"2");
                int goloE1M1 = jogo_.getGolo1();
                int goloE2M1 = jogo_.getGolo2();
                if(goloE1M1 + nGolo1 == goloE2M1 + nGolo2) //se for empate
                   throw new Exception("Nao pode ser empate");
                 jogo_.setResultado(nGolo1, nGolo2);
                jogos.put(jogo_.getCod_Jogo(), jogo_);
                if(goloE1M1 + nGolo1 > goloE2M1 +nGolo2)
                    elim.atribuirJogo(jogo_.getCod_Jogo(), cod_equipa1);
                else elim.atribuirJogo(jogo_.getCod_Jogo(), cod_equipa2);
            }
        }
    }

    public void setGolos(String cod_Competicao, HashMap<String, Integer> Jogadores1, HashMap<String, Integer> Jogadores2) throws SQLException {
        marcador.actualizaMarcador(cod_Competicao, Jogadores1, Jogadores2);
    }

    public void removerJogos(String cod_competicao) {
        jogos.remove(cod_competicao);
    }

    public void removerJornadas(String cod_competicao) {
        jornada.remove(cod_competicao);
    }

    public Competicao updateCompeticao(String nome, String Ano) {
        this.setAno(Ano);
        this.setNome(nome);
        return this;
    }

    public Collection<String> getMelhorMarcadorCompeticao(String cod_competicao) {
        return marcador.getClassificacao(cod_competicao);
    }

    public void AlterarJogo(Jogo jogo) {
        jogos.put(jogo.getCod_Jogo(), jogo);
    }

    public Collection getClasificacao(String cod_campeonato) {
        return classificacao.getClassificacao(cod_competicao);

    }

}
