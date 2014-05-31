/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

/**
 *
 * @author David
 */
import Gestao.Gestor;
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
public class DAOGestor implements Map<String,Gestor>{
    
    public Connection conn;

    public DAOGestor() {
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
            ResultSet rs = stm.executeQuery("SELECT username FROM Gestor");
            
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
            ResultSet rs = stm.executeQuery("SELECT * FROM Gestor where username='"+username+"'and pass='"+password+"'");
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
            String sql = "SELECT username FROM Gestor WHERE username='" + (String) key + "'";
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
    public Gestor get(Object key) {
        try {
            Gestor al = null;
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM Gestor WHERE username='" + (String) key + "'";
            ResultSet rs = stm.executeQuery(sql);
            
            if (rs.next()) {
                 String aux[] = rs.getString(3).split(" ");
                al = new Gestor(rs.getString(1), rs.getString(2),rs.getString(3));
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
    public Gestor put(String key, Gestor value) {
        Gestor al = null;
        try (Statement stm = conn.createStatement()) {

            int i = stm.executeUpdate("Update Gestor set username='" + value.getUsername() + "',pass='"+value.getPassword()+"',nome='"+value.getNome()+"' WHERE username='" + key + "'");
            if (i == 0) {
                String sql = "INSERT INTO Gestor(username,pass,nome) VALUES('" + value.getUsername() + "','" + value.getPassword() + "','"+value.getNome()+"')";
                i = stm.executeUpdate(sql);
                
                return value;
            } else {
                return value;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    @Override
    public Gestor remove(Object key) {
        try {
            Gestor al = this.get(key);
            Statement stm = conn.createStatement();
            String sql = "DELETE  FROM Gestor where username='" + (String) key + "'";
            int i = stm.executeUpdate(sql);
            
            return al;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

    @Override
    public void putAll(Map<? extends String, ? extends Gestor> m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM Gestor");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Set<String> keySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<Gestor> values() {
        try {
            Collection<Gestor> col = new HashSet<Gestor>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Gestor");
            
            for (; rs.next();) {
                 String aux[] = rs.getString(3).split(" ");
                col.add(new Gestor(rs.getString(1), rs.getString(2),rs.getString(3)));
            }
            return col;
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

    @Override
    public Set<Entry<String,Gestor>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
