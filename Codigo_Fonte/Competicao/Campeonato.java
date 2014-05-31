/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Competicao;

import DAO.DAOEquipaCampeonato;
import Gestao.Equipa;
import Gestao.Jogador;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class Campeonato extends Competicao {

    private DAOEquipaCampeonato equipa;

    /* public Campeonato(String nome,String escalao,String ano){
     super(nome,escalao,ano);
     }
     */
    public Campeonato(String cod, String nome, String escalao, String ano) {
        super(cod, nome, escalao, ano);
        equipa = new DAOEquipaCampeonato();
    }

    public void setEquipa(Collection<String>equipas,String cod_competicao){
            equipa.setEquipa(equipas,cod_competicao);
            for(String a:equipas){
                this.getDAOClassificacao().put(cod_competicao,a);
            for(Jogador jog:this.getEquipa().get(a).getJogadores())
                this.getMarcador().put(cod_competicao,jog.getCodJogador());
            }       
    }
    
    public void removerEquipa(String cod){
        equipa.remove(cod);
    }
    @Override
        public int geraCalendario(String data) {
         this.getOcupacao().CriarCopia();
        int teams = this.equipa.nEquipa(this.getCod_competicao());
        // If odd number of teams add a "ghost".
        boolean ghost = false;
        if (teams % 2 == 1) {
            teams++;
            ghost = true;
        }
        ArrayList<Equipa> equipas = new ArrayList<Equipa>(this.equipa.values(this.getCod_competicao()));
        Collection<Campo> campos = this.getCampo().values();
        ArrayList<Campo> campoUtilizados=new ArrayList<Campo>();
        // Generate the fixtures using the cyclic algorithm.
        int totalRounds = 2*(teams - 1);
        int matchesPerRound = teams / 2;
        String[][] rounds = new String[totalRounds][matchesPerRound];

        for (int round = 0; round < totalRounds; round++) {
            for (int match = 0; match < matchesPerRound; match++) {
                int home = (round + match) % (teams - 1);
                int away = (teams - 1 - match + round) % (teams - 1);
                // Last team stays in the same place while the others
                // rotate around it.
                if (match == 0) {
                    away = teams - 1;
                }
                // Add one so teams are number 1 to teams not 0 to teams - 1
                // upon display.
                rounds[round][match] = (home + 1) + "v" + (away + 1);
            }
        }

        // Interleave so that home and away games are fairly evenly dispersed.
        String[][] interleaved = new String[totalRounds][matchesPerRound];

        int evn = 0;
        int odd = (teams / 2);
        for (int i = 0; i < rounds.length; i++) {
            if (i % 2 == 0) {
                interleaved[i] = rounds[evn++];
            } else {
                interleaved[i] = rounds[odd++];
            }
        }

        rounds = interleaved;

        // Last team can't be away for every game so flip them
        // to home on odd rounds.
        for (int round = 0; round < rounds.length; round++) {
            if (round % 2 == 1) {
                rounds[round][0] = flip(rounds[round][0]);
            }
        }

        // Display the fixtures 
       
        Calendar cal = Calendar.getInstance();
       int total=0;
        int l = 0;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String str_date=data;
        Date date = null ; 
         Date todate1 = null;
        try {
            date = dateFormat.parse(str_date);
        } catch (ParseException ex) {
            Logger.getLogger(Campeonato.class.getName()).log(Level.SEVERE, null, ex);
        }
            cal.setTime(date);
            todate1 = cal.getTime();
        for (int i = 0; i < (rounds.length)/2; i++) {
            String todate = dateFormat.format(todate1);
            String cod_jornada = this.getCod_competicao() + i;
            Jornada jornada = new Jornada(cod_jornada, this.getCod_competicao(), todate);
            this.getDAOJornada().put(cod_jornada, jornada);
            
            // valida occupacao
            int j;
            int p = 0;
            for (j = 0; j < matchesPerRound && (p == 0); j++) {
                String[] equipa = rounds[i][j].split("v");
                Iterator<Campo>camps=campos.iterator();
              while(camps.hasNext()) {
                  Campo c=camps.next();
                    try {
                        if ((c.getCod_Escola().equals(equipas.get(Integer.parseInt(equipa[0]) - 1).getCod_Escola()))&&(this.getOcupacao().verificaOcupacao(this.getEscalao().getQuadrantes(),todate , c.getCodigo()) > 0)) {
                            total++;
                            this.getOcupacao().validaOcupacao(this.getEscalao().getQuadrantes(), todate, c.getCodigo());
                            campoUtilizados.add(c);
                            data=todate;
                            String cod_jogo=this.getCod_competicao()+"-"+cod_jornada+j;
                            Jogo jogo = new Jogo(cod_jogo,cod_jornada, this.getCod_competicao(), -1, -1, equipas.get(Integer.parseInt(equipa[0]) - 1).getCodEquipa(), equipas.get(Integer.parseInt(equipa[1]) - 1).getCodEquipa(), c.getCodigo());
                            this.getJogos().put(cod_jogo, jogo);
                            /* campo.add(c);
                            this.getOcupacao().validaOcupacao(this.getEscalao().getQuadrantes(), fromdate, c.getCodigo());
                            l++;
                            System.out.println(c.getCodigo());
                            String cod_jogo = this.getCod_competicao() + l;
                            Jogo jogo = new Jogo(cod_jogo, cod_jornada, this.getCod_competicao(), -1, -1, equipas.get(Integer.parseInt(equipa[0]) - 1).getCodEquipa(), equipas.get(Integer.parseInt(equipa[1]) - 1).getCodEquipa(), c.getCodigo());
                            this.getJogos().put(cod_jogo, jogo);*/
                            break;
                            
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Campeonato.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
            
              cal.add(Calendar.DATE, 7);
             todate1 = cal.getTime();
        }
        
        for (int i =((rounds.length)/2); i < (rounds.length); i++) {
            String todate = dateFormat.format(todate1);
            String cod_jornada = this.getCod_competicao() + i;
            Jornada jornada = new Jornada(cod_jornada, this.getCod_competicao(), todate);
            this.getDAOJornada().put(cod_jornada, jornada);
            
            // valida occupacao
            int j;
            int p = 0;
            for (j = 0; j < matchesPerRound && (p == 0); j++) {
                System.out.println("Antes"+rounds[i][j]);
                String team=flip(rounds[i][j]);
                String[] equipa =  team.split("v");
                Iterator<Campo>camps=campos.iterator();
              while(camps.hasNext()) {
                    try {
                        Campo c=camps.next();
                         if ((c.getCod_Escola().equals(equipas.get(Integer.parseInt(equipa[0]) - 1).getCod_Escola()))&&(this.getOcupacao().verificaOcupacao(this.getEscalao().getQuadrantes(), todate, c.getCodigo()) > 0)) {
                             total++;
                             this.getOcupacao().validaOcupacao(this.getEscalao().getQuadrantes(), todate, c.getCodigo());
                             campoUtilizados.add(c);
                             data=todate;
                             String cod_jogo=this.getCod_competicao()+"-"+cod_jornada+j;
                              Jogo jogo = new Jogo(cod_jogo,cod_jornada, this.getCod_competicao(), -1, -1, equipas.get(Integer.parseInt(equipa[0]) - 1).getCodEquipa(), equipas.get(Integer.parseInt(equipa[1]) - 1).getCodEquipa(), c.getCodigo());
                                this.getJogos().put(cod_jogo, jogo);
                             /* campo.add(c);
                            this.getOcupacao().validaOcupacao(this.getEscalao().getQuadrantes(), fromdate, c.getCodigo());
                            l++;
                            System.out.println(c.getCodigo());
                            String cod_jogo = this.getCod_competicao() + l;
                            Jogo jogo = new Jogo(cod_jogo, cod_jornada, this.getCod_competicao(), -1, -1, equipas.get(Integer.parseInt(equipa[0]) - 1).getCodEquipa(), equipas.get(Integer.parseInt(equipa[1]) - 1).getCodEquipa(), c.getCodigo());
                            this.getJogos().put(cod_jogo, jogo);*/
                            break;

                        }
                         
                    } catch (SQLException ex) {
                        Logger.getLogger(Campeonato.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
            cal.add(Calendar.DATE, 7);
             todate1 = cal.getTime();
            
        }
        if(total!=(totalRounds*matchesPerRound)){
            try {
                this.getOcupacao().LimpaOcupacao();
                this.removerJogos(this.getCod_competicao());
                this.removerJornadas(this.getCod_competicao());
                removerEquipa(this.getCod_competicao());
                System.out.println(total);
                return 0;
            }
            
            /*
            cal = Calendar.getInstance();
            l = 0;
            for (int i = 0; i < rounds.length; i++) {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String todate = dateFormat.format(date);
            cal.add(Calendar.DATE, 7);
            Date todate1 = cal.getTime();
            String fromdate = dateFormat.format(todate1);
            String cod_jornada = this.getCod_competicao() + i;
            Jornada jornada = new Jornada(cod_jornada, this.getCod_competicao(), fromdate);
            this.getDAOJornada().put(cod_jornada, jornada);
            System.out.println("Round " + (i + 1));
            // valida occupacao
            int j;
            int p = 0;
            for (j = 0; j < matchesPerRound && (p == 0); j++) {
            System.out.println(rounds[i][j]);
            String[] equipa = rounds[i][j].split("v");
            for (Campo c : campos) {
            try {
            if (c.getCod_Escola().equals(equipas.get(Integer.parseInt(equipa[0]) - 1).getCod_Escola())&&(this.getOcupacao().verificaOcupacao(this.getEscalao().getQuadrantes(), fromdate, c.getCodigo()) > 0)) {
            campo.add(c);
            this.getOcupacao().validaOcupacao(this.getEscalao().getQuadrantes(), fromdate, c.getCodigo());
            l++;
            System.out.println(c.getCodigo());
            String cod_jogo = this.getCod_competicao() + l;
            Jogo jogo = new Jogo(cod_jogo, cod_jornada, this.getCod_competicao(), -1, -1, equipas.get(Integer.parseInt(equipa[0]) - 1).getCodEquipa(), equipas.get(Integer.parseInt(equipa[1]) - 1).getCodEquipa(), c.getCodigo());
            this.getJogos().put(cod_jogo, jogo);
            break;
            }
            } catch (SQLException ex) {
            Logger.getLogger(Campeonato.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            }
            }
             */ catch (SQLException ex) {
                Logger.getLogger(Campeonato.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
          this.getOcupacao().removeCopia();
          return 1;
        

    }

    private static String flip(String match) {
        String[] components = match.split("v");
        return components[1] + "v" + components[0];
    }

}
