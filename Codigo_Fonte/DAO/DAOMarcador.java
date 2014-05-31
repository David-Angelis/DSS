/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class DAOMarcador {

    public Connection conn;

    public DAOMarcador() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            this.conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/orcl", "BASEDADOS", "bd");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Collection<String> getClassificacao(String cod_competicao) {
        try {
            Collection<String> col = new ArrayList<String>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select Jogador.nome,equipa.cod_equipa as Equipa,golo from Jogador,Marcadores,Equipa where cod_competicao='" + cod_competicao + "'and Jogador.cod_jogador=marcadores.cod_jogador and equipa.cod_equipa=jogador.cod_equipa and golo>0 Order by golo desc ");
            
            for (; rs.next();) {
                col.add((rs.getString(1) + "_" + rs.getString(2) + "_" + rs.getString(3)));
            }
            rs.close();
            stm.close();
            return col;
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void put(String cod_competicao, String cod_jogador) {

        try (Statement stm = conn.createStatement()) {

            int i = stm.executeUpdate("Update Marcadores set Cod_Competicao='" + cod_competicao + "',cod_jogador='" + cod_jogador + "',Golo=to_number('0') WHERE Cod_competicao='" + cod_competicao + "'and cod_jogador='"+cod_jogador+"'");
            if (i == 0) {
                String sql = "INSERT INTO Marcadores(cod_competicao,cod_jogador,golo) VALUES('" + cod_competicao + "','" + cod_jogador + "',to_number('0'))";
                i = stm.executeUpdate(sql);
                stm.close();
            } 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void actualizaMarcador(String cod_Competicao, HashMap<String, Integer> Jogadores1, HashMap<String, Integer> Jogadores2) 
    {
        try {
            Statement stm = conn.createStatement();
            
            Set set = Jogadores1.keySet();
            Iterator iter = set.iterator();
            while (iter.hasNext()) {
                String cod = (String) iter.next();
                stm.executeQuery("Update Marcadores set Golo=Golo+" + Jogadores1.get(cod) + "where cod_competicao='" + cod_Competicao + "'and cod_jogador='" + cod + "'");
            }
            set = Jogadores2.keySet();
            iter = set.iterator();
            while (iter.hasNext()) {
                String cod = (String) iter.next();
                stm.executeQuery("Update Marcadores set Golo=Golo+" + Jogadores2.get(cod) + "where cod_competicao='" + cod_Competicao + "'and cod_jogador='" + cod + "'");
            }
            stm.close();
        } catch (SQLException ex) {
            System.out.println("Erro");
            Logger.getLogger(DAOMarcador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
