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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class DAOClassificacao{
    public Connection conn;

    public DAOClassificacao() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            this.conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/orcl", "BASEDADOS", "bd");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void put(String cod_competicao, String cod_equipa) {

        try (Statement stm = conn.createStatement()) {
                String sql = "INSERT INTO Classificacao(cod_competicao,cod_equipa,pontos,golos) VALUES('" + cod_competicao + "','" + cod_equipa + "',to_number('0'),to_number('0'))";
               int i = stm.executeUpdate(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
    }
    
    
     public Collection<String> getClassificacao(String cod_competicao){
         try {
            Collection<String> col = new ArrayList<String>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select Equipa.Cod_equipa,Pontos,Golos from Equipa,Classificacao where cod_competicao='"+cod_competicao+"'and Classificacao.cod_equipa=equipa.cod_equipa order by Pontos desc, Golos desc ");
            
            for (; rs.next();) {
                
                col.add((rs.getString(1)+"_"+rs.getString(2)+"_"+rs.getString(3)));
            }
            return col;
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
     
   public  void actualizaClassificacao(String cod_competicao,String cod_equipa1,int golos1,String cod_equipa2,int golos2 ){
         try {
            Statement stm = conn.createStatement();
            if(golos1>golos2){
            String sql = "Update Classificacao set Pontos=Pontos+to_number('3'),Golos=Golos+to_number('"+golos1+"') WHERE Cod_competicao='" +cod_competicao+ "' and cod_equipa='"+cod_equipa1+"'";
            stm.executeUpdate(sql);
            
            }
            if(golos2>golos1){
             String sql = "Update Classificacao set Pontos=Pontos+to_number('3'),Golos=Golos+to_number('"+golos2+"') WHERE Cod_competicao='" +cod_competicao+ "' and cod_equipa='"+cod_equipa2+"'";
             stm.executeUpdate(sql);
            }
            if(golos2==golos1){
             String sql = "Update Classificacao set Pontos=Pontos+to_number('1'),Golos=Golos+to_number('"+golos2+"') WHERE Cod_competicao='" +cod_competicao+ "' and cod_equipa='"+cod_equipa2+"'";
            stm.executeUpdate(sql);
              sql = "Update Classificacao set Pontos=Pontos+to_number('1'),Golos=Golos+to_number('"+golos1+"') WHERE Cod_competicao='" +cod_competicao+ "' and cod_equipa='"+cod_equipa1+"'";
             stm.executeUpdate(sql);
            }
            
           }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }   
   }
    
}
