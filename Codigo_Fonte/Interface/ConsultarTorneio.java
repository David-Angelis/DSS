/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;

import Competicao.Jogo;
import Competicao.Jornada;
import Competicao.Torneio;
import Competicao.EliminatoriaDupla;
import Competicao.Eliminatorias;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author Rui
 */
public class ConsultarTorneio extends javax.swing.JFrame {
    private AssociacaoEscolasFutebol a;

    /**
     * Creates new form ConsultarTorneio
     */
    public ConsultarTorneio(String nomeTorneio, AssociacaoEscolasFutebol a) {
        this.a = a;
        initComponents();
  
        NomeTorneio_Label.setText(nomeTorneio);
        
        Collection<Jornada> jornadas = a.getCompeticao(nomeTorneio).getDAOJornada().values(nomeTorneio);
        Collection<Jogo> jogos = null;
        String cod[];
        String fase = null;
                      
        //calcula que fase começa
        if (((Torneio) a.getCompeticao(nomeTorneio)).getEquipas().size(nomeTorneio) == 16) {
            fase = "oitavos";
        }
        if (((Torneio) a.getCompeticao(nomeTorneio)).getEquipas().size(nomeTorneio) == 8) {
            fase = "quartos";
        }
        if (((Torneio) a.getCompeticao(nomeTorneio)).getEquipas().size(nomeTorneio) == 4) {
            fase = "meias";
        }
        if (((Torneio) a.getCompeticao(nomeTorneio)).getEquipas().size(nomeTorneio) == 2) {
            fase = "final";
        }
        
        String tipoEliminatoria = a.getCompeticao(nomeTorneio).getClass().getName();
        escondeTudo(fase, tipoEliminatoria);
           
        if (tipoEliminatoria.equals("Competicao.Eliminatorias")) {
            if (jornadas != null) {
                for (Jornada jornada : jornadas) {
                    jogos = jornada.getJogo().values(nomeTorneio, jornada.getCod_Jornada());
                    cod = jornada.getCod_Jornada().split("_");
                    Iterator itr = jogos.iterator();
                    int i, max;
                    max = jornada.getJogo().size(jornada.getCod_Jornada());

                    switch (fase) { 
                        case "oitavos": //caso seja nos oitavos
                            for (i = 1; itr.hasNext() && i <= max; i++) {
                                Jogo jogo = (Jogo) itr.next();
                                switch (i) { //indice do jogo
                                    case 1:
                                        e1.setText(jogo.getEquipa1());
                                        e2.setText(jogo.getEquipa2());
                                        e1_golos.setText(Integer.toString(jogo.getGolo1()));
                                        e2_golos.setText(Integer.toString(jogo.getGolo2()));
                                        break;
                                    case 2:
                                        e3.setText(jogo.getEquipa1());
                                        e4.setText(jogo.getEquipa2());
                                        e3_golos.setText(Integer.toString(jogo.getGolo1()));
                                        e4_golos.setText(Integer.toString(jogo.getGolo2()));
                                        break;
                                    case 3:
                                        e5.setText(jogo.getEquipa1());
                                        e6.setText(jogo.getEquipa2());
                                        e5_golos.setText(Integer.toString(jogo.getGolo1()));
                                        e6_golos.setText(Integer.toString(jogo.getGolo2()));
                                        break;
                                    case 4:
                                        e7.setText(jogo.getEquipa1());
                                        e8.setText(jogo.getEquipa2());
                                        e7_golos.setText(Integer.toString(jogo.getGolo1()));
                                        e8_golos.setText(Integer.toString(jogo.getGolo2()));
                                        break;
                                    case 5:
                                        e9.setText(jogo.getEquipa1());
                                        e10.setText(jogo.getEquipa2());
                                        e9_golos.setText(Integer.toString(jogo.getGolo1()));
                                        e10_golos.setText(Integer.toString(jogo.getGolo2()));
                                        break;
                                    case 6:
                                        e11.setText(jogo.getEquipa1());
                                        e12.setText(jogo.getEquipa2());
                                        e11_golos.setText(Integer.toString(jogo.getGolo1()));
                                        e12_golos.setText(Integer.toString(jogo.getGolo2()));
                                        break;
                                    case 7:
                                        e13.setText(jogo.getEquipa1());
                                        e14.setText(jogo.getEquipa2());
                                        e13_golos.setText(Integer.toString(jogo.getGolo1()));
                                        e14_golos.setText(Integer.toString(jogo.getGolo2()));
                                        break;
                                    case 8:
                                        e15.setText(jogo.getEquipa1());
                                        e16.setText(jogo.getEquipa2());
                                        e15_golos.setText(Integer.toString(jogo.getGolo1()));
                                        e16_golos.setText(Integer.toString(jogo.getGolo2()));
                                        break;
                                }
                            }
                            fase = "quartos";
                            break;
                        case "quartos": //caso seja nos quartos
                            for (i = 1; itr.hasNext() && i <= max; i++) {
                                Jogo jogo = (Jogo) itr.next();
                                switch (i) { //indice do jogo
                                    case 1:
                                        e17.setText(jogo.getEquipa1());
                                        e18.setText(jogo.getEquipa2());
                                        e17_golos.setText(Integer.toString(jogo.getGolo1()));
                                        e18_golos.setText(Integer.toString(jogo.getGolo2()));
                                        break;
                                    case 2:
                                        e19.setText(jogo.getEquipa1());
                                        e20.setText(jogo.getEquipa2());
                                        e19_golos.setText(Integer.toString(jogo.getGolo1()));
                                        e20_golos.setText(Integer.toString(jogo.getGolo2()));
                                        break;
                                    case 3:
                                        e21.setText(jogo.getEquipa1());
                                        e22.setText(jogo.getEquipa2());
                                        e21_golos.setText(Integer.toString(jogo.getGolo1()));
                                        e22_golos.setText(Integer.toString(jogo.getGolo2()));
                                        break;
                                    case 4:
                                        e23.setText(jogo.getEquipa1());
                                        e24.setText(jogo.getEquipa2());
                                        e23_golos.setText(Integer.toString(jogo.getGolo1()));
                                        e24_golos.setText(Integer.toString(jogo.getGolo2()));
                                        break;
                                }
                            }
                            fase = "meias";
                            break;
                        case "meias": //caso seja nas meias
                            for (i = 1; itr.hasNext() && i <= max; i++) {
                                Jogo jogo = (Jogo) itr.next();
                                switch (i) { //indice do jogo
                                    case 1:
                                        e25.setText(jogo.getEquipa1());
                                        e26.setText(jogo.getEquipa2());
                                        e25_golos.setText(Integer.toString(jogo.getGolo1()));
                                        e26_golos.setText(Integer.toString(jogo.getGolo2()));
                                        break;
                                    case 2:
                                        e27.setText(jogo.getEquipa1());
                                        e28.setText(jogo.getEquipa2());
                                        e27_golos.setText(Integer.toString(jogo.getGolo1()));
                                        e28_golos.setText(Integer.toString(jogo.getGolo2()));
                                        break;
                                }
                            }
                            fase = "final";
                            break;
                        case "final": //caso seja na final
                            for (i = 1; itr.hasNext() && i <= max; i++) {
                                Jogo jogo = (Jogo) itr.next();
                                switch (i) { //indice do jogo
                                    case 1:
                                        e29.setText(jogo.getEquipa1());
                                        e30.setText(jogo.getEquipa2());
                                        e29_golos.setText(Integer.toString(jogo.getGolo1()));
                                        e30_golos.setText(Integer.toString(jogo.getGolo2()));
                                        break;
                                }
                            }

                    }
                }
            }

        }
        else {
            if (jornadas != null) {
                int check = 1;
                for (Jornada jornada : jornadas) {
                    jogos = jornada.getJogo().values(nomeTorneio, jornada.getCod_Jornada());
                    cod = jornada.getCod_Jornada().split("_");
                    Iterator itr = jogos.iterator();
                    int i, max;
                    max = jornada.getJogo().size(jornada.getCod_Jornada());

                    switch (fase) { 
                        case "oitavos": //caso seja nos oitavos
                            for (i = 1; itr.hasNext() && i <= max; i++) {
                                Jogo jogo = (Jogo) itr.next();
                                switch (i) { //indice do jogo
                                    case 1:
                                        if (check == 1) {
                                            e1.setText(jogo.getEquipa1());
                                            e2.setText(jogo.getEquipa2());
                                            e1_golos2.setText(Integer.toString(jogo.getGolo1()));
                                            e2_golos2.setText(Integer.toString(jogo.getGolo2()));
                                        }
                                        else {
                                            e1_golos.setText(Integer.toString(jogo.getGolo2()));
                                            e2_golos.setText(Integer.toString(jogo.getGolo1())); 
                                        }
                                        break;
                                    case 2:
                                        if (check == 1) {
                                            e3.setText(jogo.getEquipa1());
                                            e4.setText(jogo.getEquipa2());
                                            e3_golos2.setText(Integer.toString(jogo.getGolo1()));
                                            e4_golos2.setText(Integer.toString(jogo.getGolo2()));
                                        }
                                        else {
                                            e3_golos.setText(Integer.toString(jogo.getGolo2()));
                                            e4_golos.setText(Integer.toString(jogo.getGolo1())); 
                                        }
                                        break;
                                    case 3:
                                        if (check == 1) {
                                            e5.setText(jogo.getEquipa1());
                                            e6.setText(jogo.getEquipa2());
                                            e5_golos2.setText(Integer.toString(jogo.getGolo1()));
                                            e6_golos2.setText(Integer.toString(jogo.getGolo2()));
                                        }
                                        else {
                                            e5_golos.setText(Integer.toString(jogo.getGolo2()));
                                            e6_golos.setText(Integer.toString(jogo.getGolo1())); 
                                        }
                                        break;
                                    case 4:
                                        if (check == 1) {
                                            e7.setText(jogo.getEquipa1());
                                            e8.setText(jogo.getEquipa2());
                                            e7_golos2.setText(Integer.toString(jogo.getGolo1()));
                                            e8_golos2.setText(Integer.toString(jogo.getGolo2()));
                                        }
                                        else {
                                            e7_golos.setText(Integer.toString(jogo.getGolo2()));
                                            e8_golos.setText(Integer.toString(jogo.getGolo1())); 
                                        }
                                        break;
                                    case 5:
                                        if (check == 1) {
                                            e9.setText(jogo.getEquipa1());
                                            e10.setText(jogo.getEquipa2());
                                            e9_golos2.setText(Integer.toString(jogo.getGolo1()));
                                            e10_golos2.setText(Integer.toString(jogo.getGolo2()));
                                        }
                                        else {
                                            e9_golos.setText(Integer.toString(jogo.getGolo2()));
                                            e10_golos.setText(Integer.toString(jogo.getGolo1())); 
                                        }
                                        break;
                                    case 6:
                                        if (check == 1) {
                                            e11.setText(jogo.getEquipa1());
                                            e12.setText(jogo.getEquipa2());
                                            e11_golos2.setText(Integer.toString(jogo.getGolo1()));
                                            e12_golos2.setText(Integer.toString(jogo.getGolo2()));
                                        }
                                        else {
                                            e11_golos.setText(Integer.toString(jogo.getGolo2()));
                                            e12_golos.setText(Integer.toString(jogo.getGolo1())); 
                                        }
                                        break;
                                    case 7:
                                        if (check == 1) {
                                            e13.setText(jogo.getEquipa1());
                                            e14.setText(jogo.getEquipa2());
                                            e13_golos2.setText(Integer.toString(jogo.getGolo1()));
                                            e14_golos2.setText(Integer.toString(jogo.getGolo2()));
                                        }
                                        else {
                                            e13_golos.setText(Integer.toString(jogo.getGolo2()));
                                            e14_golos.setText(Integer.toString(jogo.getGolo1())); 
                                        }
                                        break;
                                    case 8:
                                        if (check == 1) {
                                            e15.setText(jogo.getEquipa1());
                                            e16.setText(jogo.getEquipa2());
                                            e15_golos2.setText(Integer.toString(jogo.getGolo1()));
                                            e16_golos2.setText(Integer.toString(jogo.getGolo2()));
                                        }
                                        else {
                                            e15_golos.setText(Integer.toString(jogo.getGolo2()));
                                            e16_golos.setText(Integer.toString(jogo.getGolo1())); 
                                        }
                                        break;
                                }
                            }
                            
                            if ( check == 2) {
                                    fase = "quartos";
                                    check = 1;
                            }
                            else
                                    check++;
                            break;
                        case "quartos": //caso seja nos quartos
                            for (i = 1; itr.hasNext() && i <= max; i++) {
                                Jogo jogo = (Jogo) itr.next();
                                switch (i) { //indice do jogo
                                    case 1:
                                        if (check == 1) {
                                            e17.setText(jogo.getEquipa1());
                                            e18.setText(jogo.getEquipa2());
                                            e17_golos2.setText(Integer.toString(jogo.getGolo1()));
                                            e18_golos2.setText(Integer.toString(jogo.getGolo2()));
                                        }
                                        else {
                                            e17_golos.setText(Integer.toString(jogo.getGolo2()));
                                            e18_golos.setText(Integer.toString(jogo.getGolo1())); 
                                        }
                                        break;
                                    case 2:
                                        if (check == 1) {
                                            e19.setText(jogo.getEquipa1());
                                            e20.setText(jogo.getEquipa2());
                                            e19_golos2.setText(Integer.toString(jogo.getGolo1()));
                                            e20_golos2.setText(Integer.toString(jogo.getGolo2()));
                                        }
                                        else {
                                            e19_golos.setText(Integer.toString(jogo.getGolo2()));
                                            e20_golos.setText(Integer.toString(jogo.getGolo1())); 
                                        }
                                        break;
                                    case 3:
                                        if (check == 1) {
                                            e21.setText(jogo.getEquipa1());
                                            e22.setText(jogo.getEquipa2());
                                            e21_golos2.setText(Integer.toString(jogo.getGolo1()));
                                            e22_golos2.setText(Integer.toString(jogo.getGolo2()));
                                        }
                                        else {
                                            e21_golos.setText(Integer.toString(jogo.getGolo2()));
                                            e22_golos.setText(Integer.toString(jogo.getGolo1())); 
                                        }
                                        break;
                                    case 4:
                                        if (check == 1) {
                                            e23.setText(jogo.getEquipa1());
                                            e24.setText(jogo.getEquipa2());
                                            e23_golos2.setText(Integer.toString(jogo.getGolo1()));
                                            e24_golos2.setText(Integer.toString(jogo.getGolo2()));
                                        }
                                        else {
                                            e23_golos.setText(Integer.toString(jogo.getGolo2()));
                                            e24_golos.setText(Integer.toString(jogo.getGolo1())); 
                                        }
                                        break;
                                }
                            }
                            if ( check == 2) {
                                    fase = "meias";
                                    check = 1;
                            }
                            else
                                    check++;
                            break;
                        case "meias": //caso seja nas meias
                            for (i = 1; itr.hasNext() && i <= max; i++) {
                                Jogo jogo = (Jogo) itr.next();
                                switch (i) { //indice do jogo
                                    case 1:
                                        if (check == 1) {
                                            e25.setText(jogo.getEquipa1());
                                            e26.setText(jogo.getEquipa2());
                                            e25_golos2.setText(Integer.toString(jogo.getGolo1()));
                                            e26_golos2.setText(Integer.toString(jogo.getGolo2()));
                                        }
                                        else {
                                            e25_golos.setText(Integer.toString(jogo.getGolo2()));
                                            e26_golos.setText(Integer.toString(jogo.getGolo1())); 
                                        }
                                        break;
                                    case 2:
                                        if (check == 1) {
                                            e27.setText(jogo.getEquipa1());
                                            e28.setText(jogo.getEquipa2());
                                            e27_golos2.setText(Integer.toString(jogo.getGolo1()));
                                            e28_golos2.setText(Integer.toString(jogo.getGolo2()));
                                        }
                                        else {
                                            e27_golos.setText(Integer.toString(jogo.getGolo2()));
                                            e28_golos.setText(Integer.toString(jogo.getGolo1())); 
                                        }
                                        break;
                                }
                            }
                            if ( check == 2) {
                                    fase = "final";
                                    check = 1;
                            }
                            else
                                    check++;
                            break;
                        case "final": //caso seja na final
                            for (i = 1; itr.hasNext() && i <= max; i++) {
                                Jogo jogo = (Jogo) itr.next();
                                switch (i) { //indice do jogo
                                    case 1:
                                        e29.setText(jogo.getEquipa1());
                                        e30.setText(jogo.getEquipa2());
                                        e29_golos.setText(Integer.toString(jogo.getGolo1()));
                                        e30_golos.setText(Integer.toString(jogo.getGolo2()));
                                        break;
                                }
                            }

                    }
                }
            }
            
        }
        
    }
    
    private void escondeTudo(String s, String t) {
        switch(s) {
            case "quartos":
                jogo_8_1.setVisible(false);
                for(Component c: jogo_8_1.getComponents())
                    c.setVisible(false);
                jogo_8_2.setVisible(false);
                for(Component c: jogo_8_2.getComponents())
                    c.setVisible(false);
                jogo_8_3.setVisible(false);
                for(Component c: jogo_8_3.getComponents())
                    c.setVisible(false);
                jogo_8_4.setVisible(false);
                for(Component c: jogo_8_4.getComponents())
                    c.setVisible(false);
                jogo_8_5.setVisible(false);
                for(Component c: jogo_8_5.getComponents())
                    c.setVisible(false);
                jogo_8_6.setVisible(false);
                for(Component c: jogo_8_6.getComponents())
                    c.setVisible(false);
                jogo_8_7.setVisible(false);
                for(Component c: jogo_8_7.getComponents())
                    c.setVisible(false);
                jogo_8_8.setVisible(false);
                for(Component c: jogo_8_8.getComponents())
                    c.setVisible(false);
                break;
            case "meias":
                jogo_4_1.setVisible(false);
                for(Component c: jogo_4_1.getComponents())
                    c.setVisible(false);
                jogo_4_2.setVisible(false);
                for(Component c: jogo_4_2.getComponents())
                    c.setVisible(false);
                jogo_4_3.setVisible(false);
                for(Component c: jogo_4_3.getComponents())
                    c.setVisible(false);
                jogo_4_4.setVisible(false);
                for(Component c: jogo_4_4.getComponents())
                    c.setVisible(false);
                break;
            case "final":
                jogo_2_1.setVisible(false);
                for(Component c: jogo_2_1.getComponents())
                    c.setVisible(false);
                jogo_2_2.setVisible(false);
                for(Component c: jogo_2_2.getComponents())
                    c.setVisible(false);
                break;
        }
        
        if(t.equals("Competicao.Eliminatorias")) {
            e1_golos2.setVisible(false);
            e2_golos2.setVisible(false);
            e3_golos2.setVisible(false);
            e4_golos2.setVisible(false);
            e5_golos2.setVisible(false);
            e6_golos2.setVisible(false);
            e7_golos2.setVisible(false);
            e8_golos2.setVisible(false);
            e9_golos2.setVisible(false);
            e10_golos2.setVisible(false);
            e11_golos2.setVisible(false);
            e12_golos2.setVisible(false);
            e13_golos2.setVisible(false);
            e14_golos2.setVisible(false);
            e15_golos2.setVisible(false);
            e16_golos2.setVisible(false);
            e17_golos2.setVisible(false);
            e18_golos2.setVisible(false);
            e19_golos2.setVisible(false);
            e20_golos2.setVisible(false);
            e21_golos2.setVisible(false);
            e22_golos2.setVisible(false);
            e23_golos2.setVisible(false);
            e24_golos2.setVisible(false);
            e25_golos2.setVisible(false);
            e26_golos2.setVisible(false);
            e27_golos2.setVisible(false);
            e28_golos2.setVisible(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jogo_8_1 = new javax.swing.JPanel();
        e1 = new javax.swing.JTextField();
        e2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        e1_golos = new javax.swing.JLabel();
        e2_golos = new javax.swing.JLabel();
        e1_golos2 = new javax.swing.JLabel();
        e2_golos2 = new javax.swing.JLabel();
        jogo_8_2 = new javax.swing.JPanel();
        e3 = new javax.swing.JTextField();
        e4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        e3_golos = new javax.swing.JLabel();
        e4_golos = new javax.swing.JLabel();
        e3_golos2 = new javax.swing.JLabel();
        e4_golos2 = new javax.swing.JLabel();
        jogo_8_3 = new javax.swing.JPanel();
        e5 = new javax.swing.JTextField();
        e6 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        e5_golos = new javax.swing.JLabel();
        e6_golos = new javax.swing.JLabel();
        e5_golos2 = new javax.swing.JLabel();
        e6_golos2 = new javax.swing.JLabel();
        jogo_8_4 = new javax.swing.JPanel();
        e7 = new javax.swing.JTextField();
        e8 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        e7_golos = new javax.swing.JLabel();
        e8_golos = new javax.swing.JLabel();
        e7_golos2 = new javax.swing.JLabel();
        e8_golos2 = new javax.swing.JLabel();
        jogo_8_5 = new javax.swing.JPanel();
        e9 = new javax.swing.JTextField();
        e10 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        e9_golos = new javax.swing.JLabel();
        e10_golos = new javax.swing.JLabel();
        e9_golos2 = new javax.swing.JLabel();
        e10_golos2 = new javax.swing.JLabel();
        jogo_8_6 = new javax.swing.JPanel();
        e11 = new javax.swing.JTextField();
        e12 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        e11_golos = new javax.swing.JLabel();
        e12_golos = new javax.swing.JLabel();
        e11_golos2 = new javax.swing.JLabel();
        e12_golos2 = new javax.swing.JLabel();
        jogo_8_7 = new javax.swing.JPanel();
        e13 = new javax.swing.JTextField();
        e14 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        e13_golos = new javax.swing.JLabel();
        e14_golos = new javax.swing.JLabel();
        e13_golos2 = new javax.swing.JLabel();
        e14_golos2 = new javax.swing.JLabel();
        jogo_8_8 = new javax.swing.JPanel();
        e15 = new javax.swing.JTextField();
        e16 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        e15_golos = new javax.swing.JLabel();
        e16_golos = new javax.swing.JLabel();
        e15_golos2 = new javax.swing.JLabel();
        e16_golos2 = new javax.swing.JLabel();
        Consultar_Label = new javax.swing.JLabel();
        jogo_4_1 = new javax.swing.JPanel();
        e17 = new javax.swing.JTextField();
        e18 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        e17_golos = new javax.swing.JLabel();
        e18_golos = new javax.swing.JLabel();
        e17_golos2 = new javax.swing.JLabel();
        e18_golos2 = new javax.swing.JLabel();
        jogo_4_3 = new javax.swing.JPanel();
        e21 = new javax.swing.JTextField();
        e22 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        e21_golos = new javax.swing.JLabel();
        e22_golos = new javax.swing.JLabel();
        e21_golos2 = new javax.swing.JLabel();
        e22_golos2 = new javax.swing.JLabel();
        jogo_4_2 = new javax.swing.JPanel();
        e19 = new javax.swing.JTextField();
        e20 = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        e19_golos = new javax.swing.JLabel();
        e20_golos = new javax.swing.JLabel();
        e19_golos2 = new javax.swing.JLabel();
        e20_golos2 = new javax.swing.JLabel();
        jogo_4_4 = new javax.swing.JPanel();
        e23 = new javax.swing.JTextField();
        e24 = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        e23_golos = new javax.swing.JLabel();
        e24_golos = new javax.swing.JLabel();
        e23_golos2 = new javax.swing.JLabel();
        e24_golos2 = new javax.swing.JLabel();
        jogo_2_2 = new javax.swing.JPanel();
        e27 = new javax.swing.JTextField();
        e28 = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        e27_golos = new javax.swing.JLabel();
        e28_golos = new javax.swing.JLabel();
        e26_golos2 = new javax.swing.JLabel();
        e28_golos2 = new javax.swing.JLabel();
        jogo_2_1 = new javax.swing.JPanel();
        e25 = new javax.swing.JTextField();
        e26 = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        e25_golos = new javax.swing.JLabel();
        e26_golos = new javax.swing.JLabel();
        e25_golos2 = new javax.swing.JLabel();
        e27_golos2 = new javax.swing.JLabel();
        jogo_1_1 = new javax.swing.JPanel();
        e29 = new javax.swing.JTextField();
        e30 = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        e29_golos = new javax.swing.JLabel();
        e30_golos = new javax.swing.JLabel();
        NomeTorneio_Label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jogo_8_1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("vs");

        e1_golos.setText("0");

        e2_golos.setText("0");

        e1_golos2.setText("0");

        e2_golos2.setText("0");

        javax.swing.GroupLayout jogo_8_1Layout = new javax.swing.GroupLayout(jogo_8_1);
        jogo_8_1.setLayout(jogo_8_1Layout);
        jogo_8_1Layout.setHorizontalGroup(
            jogo_8_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_8_1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_8_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(e2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_8_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(e1_golos2)
                    .addComponent(e2_golos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jogo_8_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e1_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(e2_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jogo_8_1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel1)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jogo_8_1Layout.setVerticalGroup(
            jogo_8_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_8_1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_8_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e1_golos)
                    .addComponent(e1_golos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_8_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e2_golos)
                    .addComponent(e2_golos2))
                .addContainerGap())
        );

        jogo_8_2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setText("vs");

        e3_golos.setText("0");

        e4_golos.setText("0");

        e3_golos2.setText("0");

        e4_golos2.setText("0");

        javax.swing.GroupLayout jogo_8_2Layout = new javax.swing.GroupLayout(jogo_8_2);
        jogo_8_2.setLayout(jogo_8_2Layout);
        jogo_8_2Layout.setHorizontalGroup(
            jogo_8_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_8_2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_8_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e3, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(e4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jogo_8_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(e3_golos2)
                    .addComponent(e4_golos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_8_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e3_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(e4_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jogo_8_2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel7)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jogo_8_2Layout.setVerticalGroup(
            jogo_8_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_8_2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_8_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e3_golos)
                    .addComponent(e3_golos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_8_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e4_golos)
                    .addComponent(e4_golos2))
                .addContainerGap())
        );

        jogo_8_3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setText("vs");

        e5_golos.setText("0");

        e6_golos.setText("0");

        e5_golos2.setText("0");

        e6_golos2.setText("0");

        javax.swing.GroupLayout jogo_8_3Layout = new javax.swing.GroupLayout(jogo_8_3);
        jogo_8_3.setLayout(jogo_8_3Layout);
        jogo_8_3Layout.setHorizontalGroup(
            jogo_8_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_8_3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_8_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e5, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(e6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_8_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(e5_golos2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(e6_golos2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jogo_8_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e5_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(e6_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jogo_8_3Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel10)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jogo_8_3Layout.setVerticalGroup(
            jogo_8_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_8_3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_8_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e5_golos)
                    .addComponent(e5_golos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_8_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e6_golos)
                    .addComponent(e6_golos2))
                .addContainerGap())
        );

        jogo_8_4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setText("vs");

        e7_golos.setText("0");

        e8_golos.setText("0");

        e7_golos2.setText("0");

        e8_golos2.setText("0");

        javax.swing.GroupLayout jogo_8_4Layout = new javax.swing.GroupLayout(jogo_8_4);
        jogo_8_4.setLayout(jogo_8_4Layout);
        jogo_8_4Layout.setHorizontalGroup(
            jogo_8_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_8_4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_8_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e7, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(e8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jogo_8_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(e7_golos2)
                    .addComponent(e8_golos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_8_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e7_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(e8_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jogo_8_4Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel13)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jogo_8_4Layout.setVerticalGroup(
            jogo_8_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_8_4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_8_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e7_golos)
                    .addComponent(e7_golos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_8_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e8_golos)
                    .addComponent(e8_golos2))
                .addContainerGap())
        );

        jogo_8_5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setText("vs");

        e9_golos.setText("0");

        e10_golos.setText("0");

        e9_golos2.setText("0");

        e10_golos2.setText("0");

        javax.swing.GroupLayout jogo_8_5Layout = new javax.swing.GroupLayout(jogo_8_5);
        jogo_8_5.setLayout(jogo_8_5Layout);
        jogo_8_5Layout.setHorizontalGroup(
            jogo_8_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_8_5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_8_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e9, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(e10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jogo_8_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(e9_golos2)
                    .addComponent(e10_golos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_8_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e9_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(e10_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jogo_8_5Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel16)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jogo_8_5Layout.setVerticalGroup(
            jogo_8_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_8_5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_8_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e9_golos)
                    .addComponent(e9_golos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_8_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e10_golos)
                    .addComponent(e10_golos2))
                .addContainerGap())
        );

        jogo_8_6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel19.setText("vs");

        e11_golos.setText("0");

        e12_golos.setText("0");

        e11_golos2.setText("0");

        e12_golos2.setText("0");

        javax.swing.GroupLayout jogo_8_6Layout = new javax.swing.GroupLayout(jogo_8_6);
        jogo_8_6.setLayout(jogo_8_6Layout);
        jogo_8_6Layout.setHorizontalGroup(
            jogo_8_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_8_6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_8_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e11, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(e12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_8_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(e11_golos2)
                    .addComponent(e12_golos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jogo_8_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e11_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(e12_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jogo_8_6Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel19)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jogo_8_6Layout.setVerticalGroup(
            jogo_8_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_8_6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_8_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e11_golos)
                    .addComponent(e11_golos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_8_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e12_golos)
                    .addComponent(e12_golos2))
                .addContainerGap())
        );

        jogo_8_7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel22.setText("vs");

        e13_golos.setText("0");

        e14_golos.setText("0");

        e13_golos2.setText("0");

        e14_golos2.setText("0");

        javax.swing.GroupLayout jogo_8_7Layout = new javax.swing.GroupLayout(jogo_8_7);
        jogo_8_7.setLayout(jogo_8_7Layout);
        jogo_8_7Layout.setHorizontalGroup(
            jogo_8_7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_8_7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_8_7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e13, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(e14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jogo_8_7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(e13_golos2)
                    .addComponent(e14_golos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_8_7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e13_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(e14_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jogo_8_7Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel22)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jogo_8_7Layout.setVerticalGroup(
            jogo_8_7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_8_7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_8_7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e13_golos)
                    .addComponent(e13_golos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_8_7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e14_golos)
                    .addComponent(e14_golos2))
                .addContainerGap())
        );

        jogo_8_8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel25.setText("vs");

        e15_golos.setText("0");

        e16_golos.setText("0");

        e15_golos2.setText("0");

        e16_golos2.setText("0");

        javax.swing.GroupLayout jogo_8_8Layout = new javax.swing.GroupLayout(jogo_8_8);
        jogo_8_8.setLayout(jogo_8_8Layout);
        jogo_8_8Layout.setHorizontalGroup(
            jogo_8_8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_8_8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_8_8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e15, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(e16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jogo_8_8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(e15_golos2)
                    .addComponent(e16_golos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_8_8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e15_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(e16_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jogo_8_8Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel25)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jogo_8_8Layout.setVerticalGroup(
            jogo_8_8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_8_8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_8_8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e15_golos)
                    .addComponent(e15_golos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_8_8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e16_golos)
                    .addComponent(e16_golos2))
                .addContainerGap())
        );

        Consultar_Label.setFont(new java.awt.Font("Trebuchet MS", 0, 20)); // NOI18N
        Consultar_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Consultar_Label.setText("Consultar Torneio:");

        jogo_4_1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel43.setText("vs");

        e17_golos.setText("0");

        e18_golos.setText("0");

        e17_golos2.setText("0");

        e18_golos2.setText("0");

        javax.swing.GroupLayout jogo_4_1Layout = new javax.swing.GroupLayout(jogo_4_1);
        jogo_4_1.setLayout(jogo_4_1Layout);
        jogo_4_1Layout.setHorizontalGroup(
            jogo_4_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_4_1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_4_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e17, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(e18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_4_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(e17_golos2)
                    .addComponent(e18_golos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jogo_4_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e17_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(e18_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jogo_4_1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel43)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jogo_4_1Layout.setVerticalGroup(
            jogo_4_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_4_1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_4_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e17_golos)
                    .addComponent(e17_golos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_4_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e18_golos)
                    .addComponent(e18_golos2))
                .addContainerGap())
        );

        jogo_4_3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel46.setText("vs");

        e21_golos.setText("0");

        e22_golos.setText("0");

        e21_golos2.setText("0");

        e22_golos2.setText("0");

        javax.swing.GroupLayout jogo_4_3Layout = new javax.swing.GroupLayout(jogo_4_3);
        jogo_4_3.setLayout(jogo_4_3Layout);
        jogo_4_3Layout.setHorizontalGroup(
            jogo_4_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_4_3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_4_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e21, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(e22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_4_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(e21_golos2)
                    .addComponent(e22_golos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jogo_4_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e21_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(e22_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jogo_4_3Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel46)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jogo_4_3Layout.setVerticalGroup(
            jogo_4_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_4_3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_4_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e21_golos)
                    .addComponent(e21_golos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_4_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e22_golos)
                    .addComponent(e22_golos2))
                .addContainerGap())
        );

        jogo_4_2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel49.setText("vs");

        e19_golos.setText("0");

        e20_golos.setText("0");

        e19_golos2.setText("0");

        e20_golos2.setText("0");

        javax.swing.GroupLayout jogo_4_2Layout = new javax.swing.GroupLayout(jogo_4_2);
        jogo_4_2.setLayout(jogo_4_2Layout);
        jogo_4_2Layout.setHorizontalGroup(
            jogo_4_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_4_2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_4_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e19, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(e20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_4_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(e19_golos2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(e20_golos2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jogo_4_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e19_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(e20_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jogo_4_2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel49)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jogo_4_2Layout.setVerticalGroup(
            jogo_4_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_4_2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_4_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e19_golos)
                    .addComponent(e19_golos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_4_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e20_golos)
                    .addComponent(e20_golos2))
                .addContainerGap())
        );

        jogo_4_4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel52.setText("vs");

        e23_golos.setText("0");

        e24_golos.setText("0");

        e23_golos2.setText("0");

        e24_golos2.setText("0");

        javax.swing.GroupLayout jogo_4_4Layout = new javax.swing.GroupLayout(jogo_4_4);
        jogo_4_4.setLayout(jogo_4_4Layout);
        jogo_4_4Layout.setHorizontalGroup(
            jogo_4_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_4_4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_4_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e23, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(e24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jogo_4_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(e23_golos2)
                    .addComponent(e24_golos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_4_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e23_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(e24_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jogo_4_4Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel52)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jogo_4_4Layout.setVerticalGroup(
            jogo_4_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_4_4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_4_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e23_golos)
                    .addComponent(e23_golos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_4_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e24_golos)
                    .addComponent(e24_golos2))
                .addContainerGap())
        );

        jogo_2_2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel64.setText("vs");

        e27_golos.setText("0");

        e28_golos.setText("0");

        e26_golos2.setText("0");

        e28_golos2.setText("0");

        javax.swing.GroupLayout jogo_2_2Layout = new javax.swing.GroupLayout(jogo_2_2);
        jogo_2_2.setLayout(jogo_2_2Layout);
        jogo_2_2Layout.setHorizontalGroup(
            jogo_2_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_2_2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_2_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e28, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(e27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jogo_2_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(e26_golos2)
                    .addComponent(e28_golos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_2_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e27_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(e28_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jogo_2_2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel64)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jogo_2_2Layout.setVerticalGroup(
            jogo_2_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_2_2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_2_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e27_golos)
                    .addComponent(e26_golos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel64)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_2_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e28_golos)
                    .addComponent(e28_golos2))
                .addContainerGap())
        );

        jogo_2_1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel67.setText("vs");

        e25_golos.setText("0");

        e26_golos.setText("0");

        e25_golos2.setText("0");

        e27_golos2.setText("0");

        javax.swing.GroupLayout jogo_2_1Layout = new javax.swing.GroupLayout(jogo_2_1);
        jogo_2_1.setLayout(jogo_2_1Layout);
        jogo_2_1Layout.setHorizontalGroup(
            jogo_2_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_2_1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_2_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e25, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(e26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jogo_2_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(e25_golos2)
                    .addComponent(e27_golos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_2_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e25_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(e26_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jogo_2_1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel67)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jogo_2_1Layout.setVerticalGroup(
            jogo_2_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_2_1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_2_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e25_golos)
                    .addComponent(e25_golos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel67)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_2_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e26_golos)
                    .addComponent(e27_golos2))
                .addContainerGap())
        );

        jogo_1_1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel70.setText("vs");

        e29_golos.setText("0");

        e30_golos.setText("0");

        javax.swing.GroupLayout jogo_1_1Layout = new javax.swing.GroupLayout(jogo_1_1);
        jogo_1_1.setLayout(jogo_1_1Layout);
        jogo_1_1Layout.setHorizontalGroup(
            jogo_1_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_1_1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_1_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(e30)
                    .addComponent(e29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jogo_1_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e29_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(e30_golos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jogo_1_1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel70)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jogo_1_1Layout.setVerticalGroup(
            jogo_1_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jogo_1_1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jogo_1_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e29_golos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel70)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogo_1_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(e30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e30_golos))
                .addContainerGap())
        );

        NomeTorneio_Label.setFont(new java.awt.Font("Trebuchet MS", 0, 20)); // NOI18N
        NomeTorneio_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NomeTorneio_Label.setText("Nome do torneio");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Consultar_Label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NomeTorneio_Label))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jogo_8_6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jogo_8_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jogo_8_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jogo_8_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jogo_4_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jogo_8_7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jogo_8_8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jogo_4_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jogo_2_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jogo_2_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jogo_8_5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jogo_8_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jogo_4_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jogo_4_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(414, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jogo_1_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Consultar_Label)
                    .addComponent(NomeTorneio_Label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jogo_8_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jogo_8_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jogo_8_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jogo_8_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jogo_8_5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jogo_8_6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jogo_8_7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jogo_8_8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jogo_4_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jogo_2_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jogo_4_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jogo_1_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jogo_4_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jogo_2_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jogo_4_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84))))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Consultar_Label;
    private javax.swing.JLabel NomeTorneio_Label;
    private javax.swing.JTextField e1;
    private javax.swing.JTextField e10;
    private javax.swing.JLabel e10_golos;
    private javax.swing.JLabel e10_golos2;
    private javax.swing.JTextField e11;
    private javax.swing.JLabel e11_golos;
    private javax.swing.JLabel e11_golos2;
    private javax.swing.JTextField e12;
    private javax.swing.JLabel e12_golos;
    private javax.swing.JLabel e12_golos2;
    private javax.swing.JTextField e13;
    private javax.swing.JLabel e13_golos;
    private javax.swing.JLabel e13_golos2;
    private javax.swing.JTextField e14;
    private javax.swing.JLabel e14_golos;
    private javax.swing.JLabel e14_golos2;
    private javax.swing.JTextField e15;
    private javax.swing.JLabel e15_golos;
    private javax.swing.JLabel e15_golos2;
    private javax.swing.JTextField e16;
    private javax.swing.JLabel e16_golos;
    private javax.swing.JLabel e16_golos2;
    private javax.swing.JTextField e17;
    private javax.swing.JLabel e17_golos;
    private javax.swing.JLabel e17_golos2;
    private javax.swing.JTextField e18;
    private javax.swing.JLabel e18_golos;
    private javax.swing.JLabel e18_golos2;
    private javax.swing.JTextField e19;
    private javax.swing.JLabel e19_golos;
    private javax.swing.JLabel e19_golos2;
    private javax.swing.JLabel e1_golos;
    private javax.swing.JLabel e1_golos2;
    private javax.swing.JTextField e2;
    private javax.swing.JTextField e20;
    private javax.swing.JLabel e20_golos;
    private javax.swing.JLabel e20_golos2;
    private javax.swing.JTextField e21;
    private javax.swing.JLabel e21_golos;
    private javax.swing.JLabel e21_golos2;
    private javax.swing.JTextField e22;
    private javax.swing.JLabel e22_golos;
    private javax.swing.JLabel e22_golos2;
    private javax.swing.JTextField e23;
    private javax.swing.JLabel e23_golos;
    private javax.swing.JLabel e23_golos2;
    private javax.swing.JTextField e24;
    private javax.swing.JLabel e24_golos;
    private javax.swing.JLabel e24_golos2;
    private javax.swing.JTextField e25;
    private javax.swing.JLabel e25_golos;
    private javax.swing.JLabel e25_golos2;
    private javax.swing.JTextField e26;
    private javax.swing.JLabel e26_golos;
    private javax.swing.JLabel e26_golos2;
    private javax.swing.JTextField e27;
    private javax.swing.JLabel e27_golos;
    private javax.swing.JLabel e27_golos2;
    private javax.swing.JTextField e28;
    private javax.swing.JLabel e28_golos;
    private javax.swing.JLabel e28_golos2;
    private javax.swing.JTextField e29;
    private javax.swing.JLabel e29_golos;
    private javax.swing.JLabel e2_golos;
    private javax.swing.JLabel e2_golos2;
    private javax.swing.JTextField e3;
    private javax.swing.JTextField e30;
    private javax.swing.JLabel e30_golos;
    private javax.swing.JLabel e3_golos;
    private javax.swing.JLabel e3_golos2;
    private javax.swing.JTextField e4;
    private javax.swing.JLabel e4_golos;
    private javax.swing.JLabel e4_golos2;
    private javax.swing.JTextField e5;
    private javax.swing.JLabel e5_golos;
    private javax.swing.JLabel e5_golos2;
    private javax.swing.JTextField e6;
    private javax.swing.JLabel e6_golos;
    private javax.swing.JLabel e6_golos2;
    private javax.swing.JTextField e7;
    private javax.swing.JLabel e7_golos;
    private javax.swing.JLabel e7_golos2;
    private javax.swing.JTextField e8;
    private javax.swing.JLabel e8_golos;
    private javax.swing.JLabel e8_golos2;
    private javax.swing.JTextField e9;
    private javax.swing.JLabel e9_golos;
    private javax.swing.JLabel e9_golos2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jogo_1_1;
    private javax.swing.JPanel jogo_2_1;
    private javax.swing.JPanel jogo_2_2;
    private javax.swing.JPanel jogo_4_1;
    private javax.swing.JPanel jogo_4_2;
    private javax.swing.JPanel jogo_4_3;
    private javax.swing.JPanel jogo_4_4;
    private javax.swing.JPanel jogo_8_1;
    private javax.swing.JPanel jogo_8_2;
    private javax.swing.JPanel jogo_8_3;
    private javax.swing.JPanel jogo_8_4;
    private javax.swing.JPanel jogo_8_5;
    private javax.swing.JPanel jogo_8_6;
    private javax.swing.JPanel jogo_8_7;
    private javax.swing.JPanel jogo_8_8;
    // End of variables declaration//GEN-END:variables
}
