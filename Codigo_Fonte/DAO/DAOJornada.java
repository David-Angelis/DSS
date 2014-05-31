/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Competicao.Jornada;
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
public class DAOJornada implements Map<String,Jornada>{
    
    public Connection conn;

    public DAOJornada() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            this.conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/orcl", "BASEDADOS", "bd");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
     public int size(String cod) {
        try {
            int i = 0;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT Cod_Competicao FROM Jornada where cod_competicao='"+cod+"'");

            for (; rs.next(); i++);
            rs.close();
            return i;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }
    @Override
    public int size() {
        try {
            int i = 0;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT Cod_Jornada FROM Jornada");
            
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
            ResultSet rs = stm.executeQuery("SELECT Cod_Jornada FROM Jornada");
            
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
            String sql = "SELECT Cod_Jornada FROM Jornada WHERE Cod_Jornada='" + (String) key + "'";
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
    public Jornada get(Object key) {
        try {
            Jornada al = null;
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM Jornada WHERE cod_Jornada='" + (String) key + "'";
            ResultSet rs = stm.executeQuery(sql);
            
            if (rs.next()) {
                 String aux[] = rs.getString(3).split(" ");
                al = new Jornada(rs.getString(1), rs.getString(2),aux[0]);
            }
            rs.close();
            stm.close();
            return al;
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
     public Jornada get(String cod_competicao,String date) {
        try {
            Jornada al = null;
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM Jornada WHERE DataJornada=to_date('" + (String) date + "','dd-mm-yyyy')and cod_competicao=cod_competicao";
            ResultSet rs = stm.executeQuery(sql);
            
            if (rs.next()) {
                 String aux[] = rs.getString(3).split(" ");
                al = new Jornada(rs.getString(1), rs.getString(2),aux[0]);
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
    public Jornada put(String key, Jornada value) {
        Jornada al = null;
        try (Statement stm = conn.createStatement()) {

            int i = stm.executeUpdate("Update Jornada set Cod_Jornada='" + value.getCod_Jornada() + "',cod_competicao='"+value.getCod_Competicao()+"',DataJornada=to_date('"+value.getDate()+"','dd-mm-yyyy') WHERE Cod_Jornada='" + key + "'");
            if (i == 0) {
                String sql = "INSERT INTO Jornada(Cod_Jornada,Cod_Competicao,DataJornada) VALUES('" + value.getCod_Jornada() + "','" + value.getCod_Competicao() + "',to_date('"+value.getDate()+"','dd-mm-yyyy'))";
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
    public Jornada remove(Object key) {
        try {
            Jornada al = this.get(key);
            Statement stm = conn.createStatement();
            String sql = "DELETE  FROM Jornada where cod_Jornada='" + (String) key + "'";
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
            String sql = "DELETE  FROM Jornada where cod_Competicao='" + cod_competicao + "'";
            int i = stm.executeUpdate(sql);
            stm.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
           
        }
     }

    @Override
    public void putAll(Map<? extends String, ? extends Jornada> m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM Jornada");
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
    public Collection<Jornada> values() {
        try {
            Collection<Jornada> col = new HashSet<Jornada>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Jornada");
            
            for (; rs.next();) {
                 String aux[] = rs.getString(3).split(" ");
                col.add(new Jornada(rs.getString(1), rs.getString(2),aux[0]));
            }
            rs.close();
            stm.close();
            return col;
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }
    
    public Collection<Jornada> values(String cod_Competicao) {
        try {
            Collection<Jornada> col = new ArrayList<Jornada>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Jornada where cod_competicao='"+cod_Competicao+"' ORDER BY cod_jornada asc ");
            
            for (; rs.next();) {
                 String aux[] = rs.getString(3).split(" ");
                col.add(new Jornada(rs.getString(1), rs.getString(2),aux[0]));
            }
            rs.close();
            stm.close();
            return col;
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

    @Override
    public Set<Entry<String, Jornada>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
