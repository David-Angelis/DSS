/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Competicao;

import DAO.DAOJornada;
import Gestao.Equipa;
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

/**
 *
 * @author David
 */
public class Eliminatorias extends Torneio {

    public Eliminatorias(String cod, String nome, String escalao, String ano) {
        super(cod, nome, escalao, ano);
    }

    @Override
    public void atribuirJogo(String cod_jogo, String cod_equipa) {
        String[] aux = cod_jogo.split("_");
        int jogo = Integer.parseInt(aux[2]);

        if (jogo / 2 == 0) {
            int jornada = Integer.parseInt(aux[1]) + 1;
            if (this.getJogos().containsKey(aux[0] + "_" + jornada + "_" + 1)) {
                Jogo j = this.getJogos().get(aux[0] + "_" + jornada + "_" + 1);
                j.setEquipa1(cod_equipa);
                this.getJogos().put(j.getCod_Jogo(), j);
                return;
            }
        }
        if (jogo % 2 == 0) {
            int jornada = Integer.parseInt(aux[1]) + 1;
            if (this.getJogos().containsKey(aux[0] + "_" + jornada + "_" + (jogo / 2))) {
                Jogo j = this.getJogos().get(aux[0] + "_" + jornada + "_" + (jogo / 2));
                j.setEquipa2(cod_equipa);
                this.getJogos().put(j.getCod_Jogo(), j);
                return;
            }
        } else {
            int jornada = Integer.parseInt(aux[1]) + 1;
            if (this.getJogos().containsKey(aux[0] + "_" + jornada + "_" + ((jogo / 2) + 1))) {
                Jogo j = this.getJogos().get(aux[0] + "_" + jornada + "_" + ((jogo / 2) + 1));
                j.setEquipa1(cod_equipa);
                this.getJogos().put(j.getCod_Jogo(), j);
                return;
            }
        }

    }
    @Override
    public int geraCalendario(String data) {

        this.getOcupacao().CriarCopia();
        int total = 0;
        Calendar c = Calendar.getInstance();
        DateFormat dataFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = dataFormat.parse(data);
            c.setTime(date);
        } catch (ParseException ex) {
            Logger.getLogger(Torneio.class.getName()).log(Level.SEVERE, null, ex);
        }
        DAOJornada DAOJornada = super.getDAOJornada();

        //Init array de equipas
        Collection<Equipa> equipas = DAOTorneio.values(super.getCod_competicao());
        Iterator it = equipas.iterator();
        //Lista dos Campo do Sistema
        Collection<Campo> campos = this.getCampo().values();
        ArrayList<Equipa> seeds = new ArrayList<>(equipas.size() + 1);
        while (it.hasNext()) {
            seeds.add((Equipa) it.next());
        }
        //Shuffle array
        Collections.shuffle(seeds);
        //Criar rondas
        int nJogos = seeds.size() / 2;
        int nEquipas = seeds.size();
        int rondas = 1;
        //Primeira Jornada
        Jornada j = new Jornada(this.getCod_competicao() + "_" + Integer.toString(rondas), super.getCod_competicao(), dataFormat.format(c.getTime()));
        DAOJornada.put(Integer.toString(rondas), j);
         int id =1;
        for (int i = 0; i < nEquipas; i++) {
            //Crias um iterator dos campos
            Iterator<Campo> camps = campos.iterator();
            while (camps.hasNext()) {
                Campo camp = camps.next();
                try {
                    if ((camp.getCod_Escola().equals(seeds.get(i).getCod_Escola())) && //verifica se o codigo escola do campo e igual ao cod_escola da equipa
                            (this.getOcupacao().verificaOcupacao(this.getEscalao().getQuadrantes(), dataFormat.format(c.getTime()), camp.getCodigo()) > 0) && //verifica se o jogo pode ser realizado no campo 
                            (!this.getJogos().ExisteJogo(seeds.get(i).getCodEquipa(), dataFormat.format(c.getTime()))) && (!this.getJogos().ExisteJogo(seeds.get(i + 1).getCodEquipa(), dataFormat.format(c.getTime())))) { //Verifica se as equipas ja nao tem um jogo marcado nesse dia
                        total++;
                        this.getOcupacao().validaOcupacao(this.getEscalao().getQuadrantes(), dataFormat.format(c.getTime()), camp.getCodigo());//Marca o campo como ocupado no dia 
                         int equipa1 = i, equipa2 = i;
                        Jogo jogo = new Jogo(super.getCod_competicao() + "_" + rondas + "_" + id, this.getCod_competicao() + "_" + Integer.toString(rondas), super.getCod_competicao(), -1, -1, seeds.get(equipa1).getCodEquipa(), seeds.get(equipa2 + 1).getCodEquipa(), camp.getCodigo());
                        this.getJogos().put(super.getCod_competicao() + "_" + rondas + "_" + id, jogo);
                        break;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Torneio.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            i++;
            id++;
        }
        //Caso nao consegui criar todos o Jogos
        if (total != nJogos) {
            try {
                this.getOcupacao().LimpaOcupacao(); //A tabela Ocupacao passa para o estado anterior
                this.removerJogos(this.getCod_competicao()); //Remover todos os Jogos ja criados
                this.removerJornadas(this.getCod_competicao());//Remover todas as Jornadas
                removerEquipa(this.getCod_competicao());//Remover as Equipas do Torneio
                return 0;
            } catch (SQLException ex) {
                Logger.getLogger(Torneio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        total = 0;
        nJogos = nJogos / 2;
        nEquipas = nEquipas / 2;
        while (nJogos > 0) {
            c.add(Calendar.DATE, 7);
            int livre=0;
            for(int g=0;g<equipas.size();g++){
                try {
                    if(!this.getJogos().ExisteJogo(seeds.get(g).getCodEquipa(), dataFormat.format(c.getTime())))
                        livre++;
                } catch (SQLException ex) {
                    Logger.getLogger(Eliminatorias.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(livre!=equipas.size()){
                try {
                this.getOcupacao().LimpaOcupacao();
                this.removerJogos(this.getCod_competicao());
                this.removerJornadas(this.getCod_competicao());
                removerEquipa(this.getCod_competicao());
                return 0;
                } catch (SQLException ex) {
                    Logger.getLogger(Eliminatorias.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            rondas++;
            j = new Jornada(this.getCod_competicao() + "_" + Integer.toString(rondas), super.getCod_competicao(), dataFormat.format(c.getTime()));
            id =1;
            DAOJornada.put(Integer.toString(rondas), j);
            for (int i = 0; i < nEquipas; i++) {
                Iterator<Campo> camps = campos.iterator();
                while (camps.hasNext()) {
                    Campo camp = camps.next();
                    try {
                        if((camp.getCod_Escola().equals(seeds.get(i).getCod_Escola()))
                           && (this.getOcupacao().verificaOcupacao(this.getEscalao().getQuadrantes(), dataFormat.format(c.getTime()), camp.getCodigo()) > 0)) {
                            total++;
                            this.getOcupacao().validaOcupacao(this.getEscalao().getQuadrantes(), dataFormat.format(c.getTime()), camp.getCodigo());//Marca o campo como ocupado no dia 
                            
                            Jogo jogo = new Jogo(super.getCod_competicao() + "_" + rondas + "_" + id, this.getCod_competicao() + "_" + Integer.toString(rondas), super.getCod_competicao(), -1, -1, "0", "0", camp.getCodigo());
                            this.getJogos().put(super.getCod_competicao() + "_" + rondas + "_" + id, jogo);
                            break;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Torneio.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                i++;
                id++;
            }
            if (total != nJogos) {
            try {
                this.getOcupacao().LimpaOcupacao();
                this.removerJogos(this.getCod_competicao());
                this.removerJornadas(this.getCod_competicao());
                removerEquipa(this.getCod_competicao());
                return 0;
            } catch (SQLException ex) {
                Logger.getLogger(Torneio.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }

         nEquipas = nEquipas / 2;
            nJogos = nJogos / 2;
            total=0;

        }
        this.getOcupacao().removeCopia();
        return 1;
    }

}
 