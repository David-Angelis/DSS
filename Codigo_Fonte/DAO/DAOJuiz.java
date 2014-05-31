/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Competicao.Juiz;
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
public class DAOJuiz implements Map<String,Juiz>{
    
    public Connection conn;

    public DAOJuiz() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            this.conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/orcl", "BASEDADOS", "bd");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int size() {
        try {
            int i = 0;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT username FROM Juiz");
            
            for (; rs.next(); i++);
            return i;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    
    public boolean login(String username,String password) {
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Juiz where username='"+username+"'and pass='"+password+"'");
            return rs.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    @Override
    public boolean containsKey(Object key) throws NullPointerException {
        try {
            Statement stm = conn.createStatement();
            String sql = "SELECT username FROM Juiz WHERE username='" + (String) key + "'";
            ResultSet rs = stm.executeQuery(sql);
            
            return rs.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Juiz get(Object key) {
        try {
            Juiz al = null;
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM Juiz WHERE username='" + (String) key + "'";
            ResultSet rs = stm.executeQuery(sql);
            
            if (rs.next()) {
                 String aux[] = rs.getString(3).split(" ");
                al = new Juiz(rs.getString(1), rs.getString(2),rs.getString(3));
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
    public Juiz put(String key, Juiz value) {
        Juiz al = null;
        try (Statement stm = conn.createStatement()) {

            int i = stm.executeUpdate("Update Juiz set username='" + value.getUsername() + "',pass='"+value.getPassword()+"',nome='"+value.getNome()+"' WHERE username='" + key + "'");
            if (i == 0) {
                String sql = "INSERT INTO Juiz(username,pass,nome) VALUES('" + value.getUsername() + "','" + value.getPassword() + "','"+value.getNome()+"')";
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
    public Juiz remove(Object key) {
        try {
            Juiz al = this.get(key);
            Statement stm = conn.createStatement();
            String sql = "DELETE  FROM Juiz where username='" + (String) key + "'";
            int i = stm.executeUpdate(sql);
            stm.close();
            return al;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

    @Override
    public void putAll(Map<? extends String, ? extends Juiz> m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM Juiz");
            stm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Set<String> keySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<Juiz> values() {
        try {
            Collection<Juiz> col = new HashSet<Juiz>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Juiz");
            
            for (; rs.next();) {
                 String aux[] = rs.getString(3).split(" ");
                col.add(new Juiz(rs.getString(1), rs.getString(2),rs.getString(3)));
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
    public Set<Entry<String, Juiz>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
