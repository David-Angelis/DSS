/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Competicao.Jogo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class DAOJogo implements Map<String, Jogo> {

    public Connection conn;

    public DAOJogo() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            this.conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/orcl", "BASEDADOS", "bd");
        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        }
    }

    @Override
    public int size() {
        try {
            int i = 0;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT Cod_Jogo FROM Jogo");
            
            for (; rs.next(); i++);
            return i;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    @Override
    public boolean isEmpty() {
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT Cod_Jogo FROM Jogo");
            
            return !rs.next();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    @Override
    public boolean containsKey(Object key) throws NullPointerException {
        try {
            Statement stm = conn.createStatement();
            String sql = "SELECT Cod_Jogo FROM Jogo WHERE Cod_Jogo='" + (String) key + "'";
            ResultSet rs = stm.executeQuery(sql);
            
            return rs.next();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Jogo get(Object key) {
        try {
            Jogo al = null;
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM Jogo WHERE cod_jogo='" + (String) key + "'";
            ResultSet rs = stm.executeQuery(sql);
            stm.cancel();
            if (rs.next()) {
                al = new Jogo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8));
            }
            rs.close();
            stm.close();
            return al;
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
     public Jogo get(String cod_competicao,String cod_equipa1,String cod_Equipa2,String cod_jornada) {
        try {
            Jogo al = null;
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM Jogo WHERE cod_equipa1='" + (String)cod_equipa1 + "'and cod_jornada='"+cod_jornada+"'and equipa2='" + (String)cod_Equipa2+ "'and Cod_competicao='"+cod_competicao+"'";
            ResultSet rs = stm.executeQuery(sql);
            
            if (rs.next()) {
                al = new Jogo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8));
            }
            rs.close();
            stm.close();        
            return al;
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    @Override
    public Jogo put(String key, Jogo value) {
        int i = 0;
        Jogo al = null;
        try (Statement stm = conn.createStatement()) {
            i = stm.executeUpdate("Update Jogo set Cod_Competicao='" + value.getCod_Competicao() + "',Golo1=to_number('" + value.getGolo1() + "'),Golo2=to_number('" + value.getGolo2() + "'), Cod_Equipa1='" + value.getEquipa1() + "' ,Equipa2='" + value.getEquipa2() + "',Cod_Campo='" + value.getCod_Campo() + "' WHERE Cod_Jogo='" + key + "'");
            if (i == 0) {
                String sql = "INSERT INTO Jogo(Cod_Jogo,Cod_Jornada,Cod_Competicao,Golo1,Golo2,Cod_Equipa1,Equipa2,Cod_Campo) VALUES('" + value.getCod_Jogo() + "','" + value.getCod_Jornada() + "','" + value.getCod_Competicao() + "',to_number('" + value.getGolo1() + "'),to_number('" + value.getGolo2() + "'),'" + value.getEquipa1() + "','" + value.getEquipa2() + "','" + value.getCod_Campo() + "')";
                i = stm.executeUpdate(sql);
                stm.close();
                return value;
            } else {
                stm.close();
                return value;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    @Override
    public Jogo remove(Object key) {
        try {
            Jogo al = this.get(key);
            Statement stm = conn.createStatement();
            String sql = "DELETE  FROM Jogo where cod_Jogo='" + (String) key + "'";
            int i = stm.executeUpdate(sql);
            stm.close();
            return al;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
        
         public void remove(String cod_competicao) {
        try {
           
            Statement stm = conn.createStatement();
            String sql = "DELETE  FROM Jogo where cod_Competicao='" + cod_competicao + "'";
            int i = stm.executeUpdate(sql);
            stm.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
          
        }

         }
         
         public boolean ExisteJogo(String cod_equipa,String date) throws SQLException{
              Statement stm = conn.createStatement();
            String sql = "select Jogo.* from jornada,jogo where to_char(jornada.datajornada,'dd-mm-yyyy')='"+date+"'and (jogo.cod_equipa1='"+cod_equipa+"' or jogo.equipa2='"+cod_equipa+"')and jornada.cod_jornada = jogo.cod_jornada";
            ResultSet rs = stm.executeQuery(sql);
            return rs.next();
         }

    @Override
    public void putAll(Map<? extends String, ? extends Jogo> m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM Jogo");
            stm.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Set<String> keySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<Jogo> values() {
        try {
            Collection<Jogo> col = new HashSet<Jogo>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Jogo");
            
            for (; rs.next();) {

                col.add(new Jogo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
            rs.close();
            stm.close();
            return col;
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

    public Collection<Jogo> values(String cod_competicao) {
        try {
            Collection<Jogo> col = new ArrayList<Jogo>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Jogo where cod_competicao='" + cod_competicao + "'order by cod_jogo asc");
            
            for (; rs.next();) {
                col.add(new Jogo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
            rs.close();
            stm.close();
            return col;
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }
    
    public Collection<Jogo> values(String cod_competicao,String cod_jornada) {
        try {
            Collection<Jogo> col = new ArrayList<Jogo>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Jogo where cod_competicao='" + cod_competicao + "'and Cod_Jornada='"+cod_jornada+"'order by cod_jogo asc");
            
            for (; rs.next();) {
                col.add(new Jogo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
            rs.close();
            stm.close();
            return col;
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }
    
    public int size(String cod_jornada) {
       try {
           int i = 0;
           Statement stm = conn.createStatement();
           ResultSet rs = stm.executeQuery("SELECT Cod_Jogo FROM Jogo where cod_jornada='"+cod_jornada+"'");
           
           for (; rs.next(); i++);
           return i;
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
           return -1;
       }
   }

    @Override
    public Set<Entry<String, Jogo>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
