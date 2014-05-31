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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class DAOOcupacao {

    public Connection conn;

    public DAOOcupacao() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            this.conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/orcl", "BASEDADOS", "bd");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void CriarCopia(){
         Statement stm;
        try {
            stm = conn.createStatement();
            String sql = "CREATE TABLE Ocupacao1 AS SELECT * FROM Ocupacao";  
            stm.executeUpdate(sql);
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOcupacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int validaOcupacao(int nquadrante, String date, String cod_campo) throws SQLException {
        try {
            int i = -1;
            int nquadrantesCampo = 0;
            Statement stm = conn.createStatement();
            String sql = "Select quadrantes from campo where cod_campo='" + cod_campo + "'";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                nquadrantesCampo = rs.getInt(1);
            } else {
                return -1;
            }
            if (nquadrantesCampo >= nquadrante) {
                sql = "Select quadrante1,quadrante2,quadrante3,Quandrante4 from Ocupacao where cod_campo='" + cod_campo + "'and data_jogo=to_date('" + date + "','dd-mm-yyyy')";
                rs = stm.executeQuery(sql);
                if (!rs.next()) {
                    sql = "INSERT INTO Ocupacao(cod_campo,data_jogo,quadrante1,quadrante2,quadrante3,Quandrante4) VALUES('" + cod_campo + "',to_date('" + date + "','dd-mm-yyyy'),0,0,0,0)";
                    stm.executeUpdate(sql);
                    if (nquadrante == 1) {
                        sql = "Update Ocupacao set Quadrante1=1 where cod_campo='" + cod_campo + "'and data_jogo=to_date('" + date + "','dd-mm-yyyy')";
                        stm.executeUpdate(sql);
                        return 1;
                    }
                    if (nquadrante == 2) {
                        sql = "Update Ocupacao set Quadrante1=1, Quadrante2=1 where cod_campo='" + cod_campo + "'and data_jogo=to_date('" + date + "','dd-mm-yyyy')";
                        stm.executeUpdate(sql);
                    }

                } else {
                    if (nquadrante == 1) {
                        if (rs.getString(1).equals("0")) {
                            sql = "Update Ocupacao set Quadrante1=1 where cod_campo='" + cod_campo + "'and data_jogo=to_date('" + date + "','dd-mm-yyyy')";
                            stm.executeUpdate(sql);
                            return 1;
                        }
                        if (rs.getString(2).equals("0")) {
                            sql = "Update Ocupacao set Quadrante2=1 where cod_campo='" + cod_campo + "'and data_jogo=to_date('" + date + "','dd-mm-yyyy')";
                            stm.executeUpdate(sql);
                            return 1;
                        }
                        if (rs.getString(3).equals("0")) {
                            sql = "Update Ocupacao set Quadrante3=1 where cod_campo='" + cod_campo + "'and data_jogo=to_date('" + date + "','dd-mm-yyyy')";
                            stm.executeUpdate(sql);
                            return 1;
                        }
                        if (rs.getString(4).equals("0")) {
                            sql = "Update Ocupacao set Quandrante4=1 where cod_campo='" + cod_campo + "'and data_jogo=to_date('" + date + "','dd-mm-yyyy')";
                            stm.executeUpdate(sql);
                            return 1;
                        }
                    }
                    if (nquadrante == 2 && (nquadrantesCampo == 2)) {
                        if (rs.getString(1).equals("0") && rs.getString(2).equals("0")) {
                            sql = "Update Ocupacao set Quadrante1=1, Quadrante2=1 where cod_campo='" + cod_campo + "'and data_jogo=to_date('" + date + "','dd-mm-yyyy')";
                            stm.executeUpdate(sql);
                            return 2;
                        }
                    }
                    if (nquadrante == 2 && (nquadrantesCampo > 2)) {
                        if (rs.getString(1).equals("0") && rs.getString(3).equals("0")) {
                            sql = "Update Ocupacao set Quadrante1=1, Quadrante3=1 where cod_campo='" + cod_campo + "'and data_jogo=to_date('" + date + "','dd-mm-yyyy')";
                            stm.executeUpdate(sql);
                            return 2;
                        }
                        if (rs.getString(2).equals("0") && rs.getString(4).equals("0")) {
                            sql = "Update Ocupacao set Quadrante2=1, Quandrante4=1 where cod_campo='" + cod_campo + "'and data_jogo=to_date('" + date + "','dd-mm-yyyy')";
                            stm.executeUpdate(sql);
                            return 2;
                        }
                        if (rs.getString(3).equals("0") && rs.getString(4).equals("0")) {
                            sql = "Update Ocupacao set Quadrante3=1, Quandrante4=1 where cod_campo='" + cod_campo + "'and data_jogo=to_date('" + date + "','dd-mm-yyyy')";
                            stm.executeUpdate(sql);
                            return 2;
                        }
                        if (rs.getString(1).equals("0") && rs.getString(4).equals("0")) {
                            sql = "Update Ocupacao set Quadrante1=1, Quandrante4=1 where cod_campo='" + cod_campo + "'and data_jogo=to_date('" + date + "','dd-mm-yyyy')";
                            stm.executeUpdate(sql);
                            return 2;
                        }

                    }
                }
                return i;
            } else {
                return -1;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        
    }

    public int verificaOcupacao(int nquadrante, String date, String cod_campo) throws SQLException {
        try {
            int i = -1;
            int nquadrantesCampo = 0;
            Statement stm = conn.createStatement();
            String sql = "Select quadrantes from campo where cod_campo='" + cod_campo + "'";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                nquadrantesCampo = rs.getInt(1);
            } else {
                
                return -1;
            }
            if (nquadrantesCampo >= nquadrante) {
                sql = "Select quadrante1,quadrante2,quadrante3,Quandrante4 from Ocupacao where cod_campo='" + cod_campo + "'and data_jogo=to_date('" + date + "','dd-mm-yyyy')";
                rs = stm.executeQuery(sql);
                if (!rs.next()) {
                    
                    return 1;
                } else {
                    if (nquadrante == 1) {
                        if (rs.getString(1).equals("0")) {
                            
                            return 1;
                        }
                        if (rs.getString(2).equals("0")) {
                            
                            return 1;
                        }
                        if (rs.getString(3).equals("0")) {
                            
                            return 1;
                        }
                        if (rs.getString(4).equals("0")) {
                            
                            return 1;
                        }
                    }
                    if (nquadrante == 2 && nquadrantesCampo == 2) {
                        if (rs.getString(1).equals("0") && rs.getString(2).equals("0")) {
                            
                            return 2;
                        }
                    }
                    if (nquadrante == 2 && nquadrantesCampo > 2) {
                        if (rs.getString(1).equals("0") && rs.getString(3).equals("0")) {
                            
                            return 2;
                        }
                        if (rs.getString(2).equals("0") && rs.getString(4).equals("0")) {
                            
                            return 2;
                        }
                        if (rs.getString(3).equals("0") && rs.getString(4).equals("0")) {
                            
                            return 2;
                        }
                        if (rs.getString(1).equals("0") && rs.getString(4).equals("0")) {
                            
                            return 2;
                        }

                    }
                }
                
                return i;
            } else {
                return -1;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public int LimpaOcupacao() throws SQLException {
        
        try {
            Statement stm = conn.createStatement();
            String sql="DELETE  FROM Ocupacao";
            int i=stm.executeUpdate(sql);
             sql="INSERT INTO Ocupacao(cod_campo,data_jogo,quadrante1,quadrante2,quadrante3,Quandrante4)select * from ocupacao1";
           i=stm.executeUpdate(sql);
         sql= "Drop table Ocupacao1";
           i=stm.executeUpdate(sql);
           stm.close();
           return i;
         } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }
    
    public void removeCopia(){
        
        try {
            Statement stm = conn.createStatement();
        String sql= "Drop table Ocupacao1";
         int  i=stm.executeUpdate(sql);
          stm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
    }
}
