/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Competicao;

import DAO.DAOJogo;
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
public class EliminatoriaDupla extends Torneio {

    public EliminatoriaDupla(String cod, String nome, String escalao, String ano) {
        super(cod, nome, escalao, ano);
    }

    @Override
    public int geraCalendario(String data) {

        Calendar c = Calendar.getInstance();
        DateFormat dataFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = dataFormat.parse(data);
            c.setTime(date);
        } catch (ParseException ex) {
            Logger.getLogger(Torneio.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Init array de equipas
        Collection<Equipa> equipas = DAOTorneio.values(super.getCod_competicao());
        Iterator it = equipas.iterator();
        Collection<Campo> campos = this.getCampo().values();
        ArrayList<Equipa> seeds = new ArrayList<>(equipas.size() + 1);
        while (it.hasNext()) {
            seeds.add((Equipa) it.next());
        }
        //Shuffle array
        Collections.shuffle(seeds);
        //Criar rondas
        String campo;
        int nJogos = seeds.size();
        int nEquipas = seeds.size();
        int rondas = 1;
        int total = 0;
        int livre = 0;
        //Criar Jornadas
        ArrayList<Jornada> jornadas = new ArrayList<Jornada>();
        int i = 1;
        for (int k = 0; k < nJornadas(nEquipas); k++) {
            Jornada j = new Jornada(super.getCod_competicao() + "_" + i, super.getCod_competicao(), dataFormat.format(c.getTime()));
            this.getDAOJornada().put(super.getCod_competicao() + "_" + i, j);
            jornadas.add(j);
            i++;
            rondas++;
            for (int g = 0; g < equipas.size(); g++) {
                try {
                    if (!this.getJogos().ExisteJogo(seeds.get(g).getCodEquipa(), dataFormat.format(c.getTime()))) {
                        livre++;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Eliminatorias.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            c.add(Calendar.DATE, 7);
        }
        if (livre != nJornadas(nEquipas) * equipas.size()) {
            try {
                this.getOcupacao().LimpaOcupacao();
                this.removerJogos(this.getCod_competicao());
                this.removerJornadas(this.getCod_competicao());
                removerEquipa(this.getCod_competicao());
                return 0;
            } catch (SQLException ex) {
                Logger.getLogger(EliminatoriaDupla.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.getOcupacao().CriarCopia();
        rondas = 0;
        boolean flag = true;
        int volta = 0;
        for (int jor = 0; jor < nJornadas(nEquipas) && flag; jor++) {
            if (jor == nJornadas(nEquipas) - 1) {
                //final 
                rondas++;
                campo = procuraCampo(seeds.get(0).getCod_Escola());
                try {
                    if ((campo != null) && //verifica se o codigo escola do campo e igual ao cod_escola da equipa
                            (this.getOcupacao().verificaOcupacao(this.getEscalao().getQuadrantes(), jornadas.get(jor).getDate(), campo) > 0)) { //verifica se o jogo pode ser realizado no ca)) { //Verifica se as equipas ja nao tem um jogo marcado nesse dia
                        total++;
                        this.getOcupacao().validaOcupacao(this.getEscalao().getQuadrantes(), jornadas.get(jor).getDate(), campo);//Marca o campo como ocupado no dia
                        Jogo jogo = new Jogo(jornadas.get(jor).getCod_Jornada() + "_" + "1" + "_" + "0", jornadas.get(jor).getCod_Jornada(), super.getCod_competicao(), -1, -1, "0", "0", campo);
                        this.getJogos().put(jornadas.get(jor).getCod_Jornada() + "_" + "1" + "_" + "0", jogo);
                    } else {
                        flag = false;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(EliminatoriaDupla.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            int njogo = 1;

            for (int game = 0; game < nJogos && flag; game++) {
                rondas++;

                campo = procuraCampo(seeds.get(game).getCod_Escola());
                try {
                    if (volta == 0) {
                        if ((campo != null) && //verifica se o codigo escola do campo e igual ao cod_escola da equipa
                                (this.getOcupacao().verificaOcupacao(this.getEscalao().getQuadrantes(), jornadas.get(jor).getDate(), campo) > 0)) { //verifica se o jogo pode ser realizado no ca)) { //Verifica se as equipas ja nao tem um jogo marcado nesse dia
                            total++;
                            this.getOcupacao().validaOcupacao(this.getEscalao().getQuadrantes(), jornadas.get(jor).getDate(), campo);//Marca o campo como ocupado no dia
                            int id = game, equipa1 = game, equipa2 = game, jorn = jor;
                            //1a volta - "1" é para dizer que é a 1a mao
                            Jogo jogo = new Jogo(jornadas.get(jor).getCod_Jornada() + "_" + njogo + "_" + "1", jornadas.get(jor).getCod_Jornada(), super.getCod_competicao(), -1, -1, seeds.get(equipa1).getCodEquipa(), seeds.get(equipa2 + 1).getCodEquipa(), campo);
                            this.getJogos().put(jornadas.get(jor).getCod_Jornada() + "_" + njogo + "_" + "1", jogo);

                            //2a volta-"2"é para dizer que é a 2a mao
                            campo = procuraCampo(seeds.get(game + 1).getCod_Escola());
                            if ((campo != null) && //verifica se o codigo escola do campo e igual ao cod_escola da equipa
                                    (this.getOcupacao().verificaOcupacao(this.getEscalao().getQuadrantes(), jornadas.get(jor + 1).getDate(), campo) > 0)) { //verifica se o jogo pode ser realizado no ca)) { //Verifica se as equipas ja nao tem um jogo marcado nesse dia
                                total++;
                                this.getOcupacao().validaOcupacao(this.getEscalao().getQuadrantes(), jornadas.get(jor + 1).getDate(), campo);//Marca o campo como ocupado no dia
                                jogo = new Jogo(jornadas.get(jor + 1).getCod_Jornada() + "_" + njogo + "_" + "2", jornadas.get(jor + 1).getCod_Jornada(), super.getCod_competicao(), -1, -1, seeds.get(equipa2 + 1).getCodEquipa(), seeds.get(equipa1).getCodEquipa(), campo);
                                this.getJogos().put(jornadas.get(jor + 1).getCod_Jornada() + "_" + njogo + "_" + "2", jogo);
                                njogo++;
                            } else {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    } else {
                        if ((campo != null) && //verifica se o codigo escola do campo e igual ao cod_escola da equipa
                                (this.getOcupacao().verificaOcupacao(this.getEscalao().getQuadrantes(), jornadas.get(jor).getDate(), campo) > 0)) { //verifica se o jogo pode ser realizado no ca)) { //Verifica se as equipas ja nao tem um jogo marcado nesse dia
                            total++;
                            this.getOcupacao().validaOcupacao(this.getEscalao().getQuadrantes(), jornadas.get(jor).getDate(), campo);//Marca o campo como ocupado no dia
                            int id = game, equipa1 = game, equipa2 = game, jorn = jor;
                            //1a volta - "1" é para dizer que é a 1a mao
                            Jogo jogo = new Jogo(jornadas.get(jor).getCod_Jornada() + "_" + njogo + "_" + "1", jornadas.get(jor).getCod_Jornada(), super.getCod_competicao(), -1, -1, "0", "0", campo);
                            this.getJogos().put(jornadas.get(jor).getCod_Jornada() + "_" + njogo + "_" + "1", jogo);

                            //2a volta-"2"é para dizer que é a 2a mao
                            campo = procuraCampo(seeds.get(game + 1).getCod_Escola());
                            if ((campo != null) && //verifica se o codigo escola do campo e igual ao cod_escola da equipa
                                    (this.getOcupacao().verificaOcupacao(this.getEscalao().getQuadrantes(), jornadas.get(jor + 1).getDate(), campo) > 0)) { //verifica se o jogo pode ser realizado no ca)) { //Verifica se as equipas ja nao tem um jogo marcado nesse dia
                                total++;
                                this.getOcupacao().validaOcupacao(this.getEscalao().getQuadrantes(), jornadas.get(jor + 1).getDate(), campo);//Marca o campo como ocupado no dia
                                jogo = new Jogo(jornadas.get(jor + 1).getCod_Jornada() + "_" + njogo + "_" + "2", jornadas.get(jor + 1).getCod_Jornada(), super.getCod_competicao(), -1, -1, "0", "0", campo);
                                this.getJogos().put(jornadas.get(jor + 1).getCod_Jornada() + "_" + njogo + "_" + "2", jogo);
                                njogo++;
                            } else {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(EliminatoriaDupla.class.getName()).log(Level.SEVERE, null, ex);
                }
                game++;
            }
            jor++;
            nJogos = nJogos / 2;
            volta++;

        }
        if (flag = true) {
            this.getOcupacao().removeCopia();
            return 1;
        } else {
            try {
                this.getOcupacao().LimpaOcupacao();
                this.removerJogos(this.getCod_competicao());
                this.removerJornadas(this.getCod_competicao());
                removerEquipa(this.getCod_competicao());
                return 0;
            } catch (SQLException ex) {
                Logger.getLogger(EliminatoriaDupla.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 0;
        }

    }

    public String procuraCampo(String cod_escola) {

        Collection<Campo> campos = this.getCampo().values();
        Iterator<Campo> camps = campos.iterator();
        Campo camp = null;
        while (camps.hasNext()) {
            camp = camps.next();
            if (camp.getCod_Escola().equals(cod_escola)) {
                break;
            }
        }
        return camp.getCodigo();
    }

    public int nJornadas(int nEquipas) {
        /* int resultado = 0;
         while (nEquipas != 0) {
         resultado++;
         nEquipas = nEquipas / 2;
         }
         return resultado;
         */
        if (nEquipas == 4) {
            return 3;
        }
        if (nEquipas == 8) {
            return 5;
        }
        if (nEquipas == 16) {
            return 7;
        }
        if (nEquipas == 32) {
            return 9;
        }
        return -1;
    }

    @Override
    public void atribuirJogo(String cod_jogo, String cod_equipa) {
        String[] aux = cod_jogo.split("_");
        int jogo = Integer.parseInt(aux[2]);
        System.out.println(this.getDAOJornada().size(this.getCod_competicao()));
        if ((this.getDAOJornada().size(this.getCod_competicao())-1)==(Integer.parseInt(aux[1]))) {
            if (jogo % 2 == 0) {
                int jornada = Integer.parseInt(aux[1]) + 1;
                if (this.getJogos().containsKey(aux[0] + "_" + jornada + "_" + 1 + "_" + "0")) {
                    Jogo j = this.getJogos().get(aux[0] + "_" + jornada + "_" + 1 + "_" + "0");
                    j.setEquipa2(cod_equipa);
                    this.getJogos().put(j.getCod_Jogo(), j);
                }
            } else {
                int jornada = Integer.parseInt(aux[1]) + 1;
                if (this.getJogos().containsKey(aux[0] + "_" + jornada + "_" + 1 + "_" + "0")) {
                    Jogo j = this.getJogos().get(aux[0] + "_" + jornada + "_" + 1 + "_" + "0");
                    j.setEquipa1(cod_equipa);
                    this.getJogos().put(j.getCod_Jogo(), j);
                }
            }
            return;
        }
        if (jogo / 2 == 0) {
            int jornada = Integer.parseInt(aux[1]) + 1;
            if (this.getJogos().containsKey(aux[0] + "_" + jornada + "_" + 1 + "_" + "1")) {
                Jogo j = this.getJogos().get(aux[0] + "_" + jornada + "_" + 1 + "_" + "1");
                j.setEquipa1(cod_equipa);
                this.getJogos().put(j.getCod_Jogo(), j);

                Jogo j2 = this.getJogos().get(aux[0] + "_" + (jornada + 1) + "_" + 1 + "_" + "2");
                j2.setEquipa2(cod_equipa);
                this.getJogos().put(j2.getCod_Jogo(), j2);
                return;
            }
        }
        if (jogo % 2 == 0) {
            int jornada = Integer.parseInt(aux[1]) + 1;
            if (this.getJogos().containsKey(aux[0] + "_" + jornada + "_" + jogo / 2 + "_" + "1")) {
                Jogo j = this.getJogos().get(aux[0] + "_" + jornada + "_" + jogo / 2 + "_" + "1");
                j.setEquipa2(cod_equipa);
                this.getJogos().put(j.getCod_Jogo(), j);

                Jogo j2 = this.getJogos().get(aux[0] + "_" + (jornada + 1) + "_" + jogo / 2 + "_" + "2");
                j2.setEquipa1(cod_equipa);
                this.getJogos().put(j2.getCod_Jogo(), j2);
                return;
            }
        } else {
            int jornada = Integer.parseInt(aux[1]) + 1;
            if (this.getJogos().containsKey(aux[0] + "_" + jornada + "_" + ((jogo / 2) + 1) + "_" + "1")) {
                Jogo j = this.getJogos().get(aux[0] + "_" + jornada + "_" + ((jogo / 2) + 1) + "_" + "1");
                j.setEquipa1(cod_equipa);
                this.getJogos().put(j.getCod_Jogo(), j);

                Jogo j2 = this.getJogos().get(aux[0] + "_" + (jornada + 1) + "_" + ((jogo / 2) + 1) + "_" + "2");
                j2.setEquipa2(cod_equipa);
                this.getJogos().put(j2.getCod_Jogo(), j2);
                return;
            }
        }

    }

}
/*   public int geraCalendario(String data) {
 this.getOcupacao().CriarCopia();
 Calendar c = Calendar.getInstance();
 DateFormat dataFormat = new SimpleDateFormat("dd-MM-yyyy");
 try {
 Date date = dataFormat.parse(data);
 c.setTime(date);
 } catch (ParseException ex) {
 Logger.getLogger(Torneio.class.getName()).log(Level.SEVERE, null, ex);
 }
 DAOJornada DAOJornada = super.getDAOJornada();
 DAOJogo DAOJogo = this.getJogos();

 //Init array de equipas
 Collection<Equipa> equipas = DAOTorneio.values(super.getCod_competicao());
 Iterator it = equipas.iterator();
 Collection<Campo> campos = this.getCampo().values();
 ArrayList<Equipa> seeds = new ArrayList<>(equipas.size() + 1);
 while (it.hasNext()) {
 seeds.add((Equipa) it.next());
 }
 //Shuffle array
 Collections.shuffle(seeds);
 //Criar rondas
 int nJogos = seeds.size();
 int nEquipas = seeds.size();
 int rondas = 1;
 int total = 0;
 System.out.println("Jornadas"+nJornadas(nEquipas));
 //Primeira Jornada
 Jornada j = new Jornada(Integer.toString(rondas)+"_"+"1", super.getCod_competicao(), dataFormat.format(c.getTime()));
 DAOJornada.put(Integer.toString(rondas)+"_"+"1", j);
 c.add(Calendar.DATE, 7);
 Jornada jor = new Jornada(Integer.toString(rondas)+"_"+"2", super.getCod_competicao(), dataFormat.format(c.getTime()));
 DAOJornada.put(Integer.toString(rondas)+"_"+"2", jor);
 for (int i = 0; i < nEquipas; i++) {
 Iterator<Campo> camps = campos.iterator();
 while (camps.hasNext()) {
 Campo camp = camps.next();
 try {
 if ((camp.getCod_Escola().equals(seeds.get(i).getCod_Escola())) && //verifica se o codigo escola do campo e igual ao cod_escola da equipa
 (this.getOcupacao().verificaOcupacao(this.getEscalao().getQuadrantes(), j.getDate(), camp.getCodigo()) > 0) && //verifica se o jogo pode ser realizado no campo
 (!this.getJogos().ExisteJogo(seeds.get(i).getCodEquipa(), j.getDate())) && (!this.getJogos().ExisteJogo(seeds.get(i + 1).getCodEquipa(), j.getDate()))) { //Verifica se as equipas ja nao tem um jogo marcado nesse dia
 total++;
 this.getOcupacao().validaOcupacao(this.getEscalao().getQuadrantes(), j.getDate(), camp.getCodigo());//Marca o campo como ocupado no dia
 int id = i, equipa1 = i, equipa2 = i;
 //1a volta - "1" é para dizer que é a 1a mao
 Jogo jogo = new Jogo(super.getCod_competicao() + "_" + rondas + "_" + (id+1) + "_" + "1", j.getCod_Jornada(), super.getCod_competicao(), -1, -1, seeds.get(equipa1).getCodEquipa(), seeds.get(equipa2 + 1).getCodEquipa(), camp.getCodigo());
 DAOJogo.put(super.getCod_competicao() + "_" + rondas + "_" + (id+1) + "_" + "1", jogo);
 //2a volta, com as equipas trocadas
 //Procura campo da 2a equipa
 Iterator<Campo> campo = campos.iterator();
 while (campo.hasNext()) {
 if(camp.getCod_Escola().equals(seeds.get(i+1).getCod_Escola()))
 break;  
 camp = campo.next();
                           
                                 
 }
 if(!campo.hasNext())
 throw new Exception("Nao existe Campo");
 //Verifica se campo pode receber jogo
 if (this.getOcupacao().verificaOcupacao(this.getEscalao().getQuadrantes(), jor.getDate(), camp.getCodigo()) > 0) {
 total++;
 //Marca o campo como ocupado
 this.getOcupacao().validaOcupacao(this.getEscalao().getQuadrantes(), jor.getDate(), camp.getCodigo());//Marca o campo como ocupado no dia
 Jogo jogo2 = new Jogo(super.getCod_competicao() + "_" + rondas + "_" + id + "_" + "2", jor.getCod_Jornada(), super.getCod_competicao(), -1, -1, seeds.get(equipa2 + 1).getCodEquipa(), seeds.get(equipa1).getCodEquipa(), camp.getCodigo());
 DAOJogo.put(super.getCod_competicao() + "_" + rondas + "_" + id + "_" + "2", jogo2);
 break;
 }
 }

 } catch (SQLException ex) {
 Logger.getLogger(Torneio.class.getName()).log(Level.SEVERE, null, ex);
 } catch (Exception ex) {
 Logger.getLogger(EliminatoriaDupla.class.getName()).log(Level.SEVERE, null, ex);
 }
 }
 i++;
 }
 //Verifica se conseguiu criar o numero certo de jogos
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
 nJogos = nJogos / 4;
 nEquipas = nEquipas / 2;
 int livre = 0;
 while (nJogos > 0) {
 c.add(Calendar.DATE, 7);
 //Verifica se todas as equipas podem jogar
 for (int g = 0; g < equipas.size(); g++) {
 try {
 if (!this.getJogos().ExisteJogo(seeds.get(g).getCodEquipa(), dataFormat.format(c.getTime()))) {
 livre++;
 }
 } catch (SQLException ex) {
 Logger.getLogger(EliminatoriaDupla.class.getName()).log(Level.SEVERE, null, ex);
 }
 }
 //Verifica se o numero livre e igual ao numero de equipas que participam no torneio
 if (livre != equipas.size()) {
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
 rondas++;
            
 j = new Jornada(Integer.toString(rondas), super.getCod_competicao(), dataFormat.format(c.getTime()));
 DAOJornada.put(Integer.toString(rondas), j);
 for (int i = 0; i < nEquipas; i++) {
 Iterator<Campo> camps = campos.iterator();
 while (camps.hasNext()) {
 Campo camp = camps.next();
 if (nJogos == 1) {
 try {
 //final
 if ((camp.getCod_Escola().equals(seeds.get(i).getCod_Escola()))
 && (this.getOcupacao().verificaOcupacao(this.getEscalao().getQuadrantes(), dataFormat.format(c.getTime()), camp.getCodigo()) > 0)) {
 total++;
 int id = i;
 this.getOcupacao().validaOcupacao(this.getEscalao().getQuadrantes(), dataFormat.format(c.getTime()), camp.getCodigo());
 Jogo jogo = new Jogo(super.getCod_competicao() + "_" + rondas + "_" + id + "_" + "0", Integer.toString(rondas), super.getCod_competicao(), -1, -1, "0", "0", camp.getCodigo());
 DAOJogo.put(super.getCod_competicao() + "_" + rondas + "_" + id + "_" + "0", jogo);
 break;
 }
 break;
 } catch (SQLException ex) {
 Logger.getLogger(EliminatoriaDupla.class.getName()).log(Level.SEVERE, null, ex);
 }
 } else {
 try {
 // Resto dos jogos
 if ((camp.getCod_Escola().equals(seeds.get(i).getCod_Escola()))
 && (this.getOcupacao().verificaOcupacao(this.getEscalao().getQuadrantes(), dataFormat.format(c.getTime()), camp.getCodigo()) > 0)) {
 total++;
 int id = i;
 this.getOcupacao().validaOcupacao(this.getEscalao().getQuadrantes(), dataFormat.format(c.getTime()), camp.getCodigo());
 Jogo jogo = new Jogo(super.getCod_competicao() + "_" + rondas + "_" + id + "_" + "1", Integer.toString(rondas), super.getCod_competicao(), -1, -1, "0", "0", camp.getCodigo());
 DAOJogo.put(super.getCod_competicao() + "_" + rondas + "_" + id + "_" + "1", jogo);
 c.add(Calendar.DATE, 7);
 //Validar equipa
 //Procura proximo campo livre
 Iterator<Campo> campo = campos.iterator();
 while (campo.hasNext()) {
 if(camp.getCod_Escola().equals(seeds.get(i+1).getCod_Escola()))
 break;  
 camp = campo.next();
                           
                                 
 }
 if(!campo.hasNext())
 throw new Exception("Nao existe Campo");
 //Verifica se campo pode receber jogo
 if (this.getOcupacao().verificaOcupacao(this.getEscalao().getQuadrantes(), dataFormat.format(c.getTime()), camp.getCodigo()) > 0) {
 total++;
 //Marca o campo como ocupado
 this.getOcupacao().validaOcupacao(this.getEscalao().getQuadrantes(), dataFormat.format(c.getTime()), camp.getCodigo());
 Jogo jogo2 = new Jogo(super.getCod_competicao() + "_" + rondas + "_" + id + "_" + "2", Integer.toString(rondas), super.getCod_competicao(), -1, -1, "0", "0", camp.getCodigo());
 DAOJogo.put(super.getCod_competicao() + "_" + rondas + "_" + id + "_" + "2", jogo2);
 break;
 } 
 }
                             
 } catch (SQLException ex) {
 Logger.getLogger(EliminatoriaDupla.class.getName()).log(Level.SEVERE, null, ex);
 } catch (Exception ex) {
 Logger.getLogger(EliminatoriaDupla.class.getName()).log(Level.SEVERE, null, ex);
 }
                        
 }
 }
 i++;
 }
 //Verifica o numero de jogos criado
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
 nJogos = nJogos / 4;
 }
 //Remoçao da tabela Ocupacao na BD
 this.getOcupacao().removeCopia();
 return 1;
 }
 */
