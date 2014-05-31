/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Gestao.Jogador;
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
public class DAOJogador implements Map<String, Jogador> {

    public Connection conn;

    public DAOJogador() {
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
            ResultSet rs = stm.executeQuery("SELECT Cod_Jogador FROM Jogador");
            
            for (; rs.next(); i++);
            rs.close();
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
            ResultSet rs = stm.executeQuery("SELECT Cod_Jogador FROM Jogador");
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
            String sql = "SELECT Cod_Jogador FROM Jogador WHERE Cod_Jogador='" + (String) key + "'";
            ResultSet rs = stm.executeQuery(sql);
            
            return rs.next();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public Jogador get(Object key) {
        try {
            Jogador al = null;
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM Jogador WHERE cod_jogador='" + (String) key + "'";
            ResultSet rs = stm.executeQuery(sql);
            
            if (rs.next()) {
                String aux[] = rs.getString(3).split("-");
                al = new Jogador(rs.getString(1), rs.getString(2), aux[0], rs.getString(4));
            }
            rs.close();
            return al;
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    @Override
    public Jogador put(String key, Jogador value) {
        int i = 0;
        Jogador al = null;
        try (Statement stm = conn.createStatement()) {
            i = stm.executeUpdate("Update Jogador set Cod_Jogador='" + value.getCodJogador() + "',Nome='" + value.getNome() + "',Datadenascimento=to_date('" + value.getDataNascimento() + "','yyyy'),Cod_Equipa='" + value.getCod_Equipa() + "' WHERE Cod_Jogador='" + key + "'");
            if (i == 0) {
                String sql = "INSERT INTO Jogador(Cod_Jogador,Nome,Datadenascimento,Cod_Equipa) VALUES('" + value.getCodJogador() + "','" + value.getNome() + "',to_date('" + value.getDataNascimento() + "','yyyy'),'" + value.getCod_Equipa() + "')";
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
    public Jogador remove(Object key) {
        try {
            Jogador al = this.get(key);
            Statement stm = conn.createStatement();
            String sql = "DELETE  FROM Jogador where cod_Jogador='" + (String) key + "'";
            int i = stm.executeUpdate(sql);
            stm.close();
            return al;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }
    
     public Jogador removeAll(Object key) {
        try {
            Jogador al = this.get(key);
            Statement stm = conn.createStatement();
            String sql = "DELETE  FROM Jogador where cod_equipa='" + (String) key + "'";
            int i = stm.executeUpdate(sql);
            stm.close();
            return al;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

    @Override
    public void putAll(Map<? extends String, ? extends Jogador> m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM Jogador");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Set<String> keySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<Jogador> values() {
        try {
            Collection<Jogador> col = new HashSet<Jogador>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Jogador");
            
            for (; rs.next();) {
                String aux[] = rs.getString(3).split("-");
                col.add(new Jogador(rs.getString(1), rs.getString(2), aux[0], rs.getString(4)));
            }
            rs.close();
            return col;
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

    public Collection<Jogador> values(String cod_equipa) {
        try {
            Collection<Jogador> col = new HashSet<Jogador>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Jogador where cod_equipa='" + cod_equipa + "'");
            
            for (; rs.next();) {
                String aux[] = rs.getString(3).split("-");
                col.add(new Jogador(rs.getString(1), rs.getString(2), aux[0], rs.getString(4)));
            }
            rs.close();
            return col;
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

    @Override
    public Set<Entry<String, Jogador>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
