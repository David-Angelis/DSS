/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Competicao.Campo;
import Gestao.Equipa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class DAOEquipaCampeonato {
    
    public Connection conn;

    public DAOEquipaCampeonato() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            this.conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/orcl", "BASEDADOS", "bd");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setEquipa(Collection<String> equipa, String cod_Competicao) {
        try {
            Statement stm = conn.createStatement();
            for (String p : equipa) {
                String sql = "Insert into EquipaCampeonato(cod_equipa,cod_competicao)Values('" + p + "','" + cod_Competicao + "')";
                stm.executeUpdate(sql);
            }
            stm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Collection<Equipa> values(String cod_competicao){
         try {
            Collection<Equipa> col = new HashSet<Equipa>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT Equipa.* FROM Equipa,EquipaCampeonato where EquipaCampeonato.cod_equipa=Equipa.cod_equipa and EquipaCampeonato.cod_competicao='"+cod_competicao+"'");
            
            for (; rs.next();) {

                col.add(new Equipa(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4)));
            }
            rs.close();
            stm.close();
            return col;
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public int nEquipa(String cod_competicao){
          try {
              int i=0;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT cod_equipa FROM EquipaCampeonato where cod_competicao='"+cod_competicao+"'");
            
            for (; rs.next(); i++);
            rs.close();
            stm.close();
            return i;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public void remove(String cod) {
         try {
            
            Statement stm = conn.createStatement();
            String sql = "DELETE  FROM EquipaCampeonato where cod_competicao='" + (String) cod + "'";
            int i = stm.executeUpdate(sql);
            stm.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
    }
    
}
