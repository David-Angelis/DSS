/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Competicao;

import DAO.DAOEquipaTorneio;
import DAO.DAOJogo;
import DAO.DAOJornada;
import Gestao.Equipa;
import Gestao.Jogador;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Torneio extends Competicao {

    DAOEquipaTorneio DAOTorneio;
    /* public Torneio(String nome,String escalao,String ano){
     super(nome,escalao,ano);
     }*/

    public Torneio(String cod, String nome, String escalao, String ano) {
        super(cod, nome, escalao, ano);
        DAOTorneio = new DAOEquipaTorneio();
    }

    public void removerEquipa(String cod) {
        DAOTorneio.remove(cod);
    }
    
    public DAOEquipaTorneio getEquipas(){
        return DAOTorneio;
    }

    public abstract void atribuirJogo(String cod_jogo, String cod_equipa);
       

    public void setEquipa(Collection<String> equipas, String cod_competicao) {
        DAOTorneio.setEquipa(equipas, cod_competicao);
        for (String a : equipas) {
            Collection<Jogador> jogadores = this.getEquipa().get(a).getJogadores();
            if (jogadores != null) {
                for (Jogador jog : jogadores) {
                    this.getMarcador().put(cod_competicao, jog.getCodJogador());
                }
            }
        }
    }

    /**
     *
     * @param data
     * @return
     */
    @Override
    public abstract int geraCalendario(String data);

}
