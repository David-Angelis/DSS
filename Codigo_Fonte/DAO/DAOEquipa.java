/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Gestao.Equipa;
import Gestao.Escola;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class DAOEquipa implements Map<String,Equipa>{
    public Connection conn;

    public DAOEquipa() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            this.conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/orcl", "BASEDADOS", "bd");
        } catch (ClassNotFoundException| SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    @Override
    public int size() {
        try {
            int i = 0;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT Cod_Equipa FROM Equipa");
            
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
            ResultSet rs = stm.executeQuery("SELECT Cod_Equipa FROM Equipa");
            
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
            String sql = "SELECT Cod_Equipa FROM Equipa WHERE Cod_Equipa='" + (String) key + "'";
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
    public Equipa get(Object key) {
        try {
            Equipa al = null;
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM equipa WHERE cod_equipa='" + (String) key + "'";
            ResultSet rs = stm.executeQuery(sql);
            
            if (rs.next()) {
                al = new Equipa(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4));
            }
            rs.close();
            return al;
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    @Override
    public Equipa put(String key, Equipa value) {
        int i=0;
            Equipa al = null;
            try (Statement stm = conn.createStatement()) { 
                i=stm.executeUpdate("Update Equipa set Cod_Equipa='" + value.getCodEquipa() + "',Nome='"+value.getNome()+"',Escalao='"+value.getEscalao().toString()+"',Cod_Escola='"+value.getCod_Escola()+"'  WHERE Cod_Equipa='" + key + "'");
                if(i==0){
                    String sql = "INSERT INTO Equipa(Cod_Equipa,Nome,Escalao,Cod_Escola) VALUES('" + value.getCodEquipa() + "','"+value.getNome()+"','"+value.getEscalao().toString()+"','"+value.getCod_Escola()+"')";
                    i = stm.executeUpdate(sql);
                   stm.close();
                    return value;
                }else return value;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    @Override
    public Equipa remove(Object key) {
        try {
            Equipa al = this.get(key);
            Statement stm = conn.createStatement();
            String sql = "DELETE  FROM Equipa where cod_Equipa='" + (String) key + "'";
            int i = stm.executeUpdate(sql);
            stm.close();
            return al;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

    @Override
    public void putAll(Map<? extends String, ? extends Equipa> m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM Equipa");
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
     public Collection<Equipa> values() {
        try {
            Collection<Equipa> col = new HashSet<Equipa>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Equipa");
            
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
     public Collection<Equipa> values(String escalao) {
        try {
            Collection<Equipa> col = new HashSet<Equipa>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Equipa where escalao='"+escalao+"'");
            
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
     public Collection<Equipa> values(String escalao,String cod_escola) {
        try {
            Collection<Equipa> col = new HashSet<Equipa>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Equipa where escalao='"+escalao+"'and Cod_Escola='"+cod_escola+"'");
            
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
     
     public Collection<Equipa> values(Escola escola) {
        try {
            Collection<Equipa> col = new HashSet<Equipa>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Equipa where Cod_Escola='"+escola.getCod_Escola()+"'");
            
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
     public Collection<Equipa> values(Object escola) {
        try {
            Collection<Equipa> col = new HashSet<Equipa>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Equipa where Cod_Escola='"+(String)escola+"'");
            
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
    @Override
    public Set<Entry<String, Equipa>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
