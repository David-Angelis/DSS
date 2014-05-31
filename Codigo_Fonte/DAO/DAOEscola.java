/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
public class DAOEscola implements Map<String, Escola> {

    public Connection conn;

    public DAOEscola() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            this.conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/orcl", "BASEDADOS", "bd");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int hashCode() {
        int lHashCode = 0;
        if (lHashCode == 0) {
            lHashCode = super.hashCode();
        }
        return lHashCode;
    }

    public boolean equals(Object aObject) {
        if (this == aObject) {
            return true;
        } else if (aObject instanceof DAOEscola) {
            DAOEscola lDAOEscolaObject = (DAOEscola) aObject;
            boolean lEquals = true;
            return lEquals;
        }
        return false;
    }

    @Override
    public int size() {
        try {
            int i = 0;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT Cod_Escola FROM Escola");
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
            ResultSet rs = stm.executeQuery("SELECT Cod_Escola FROM Escola");
            
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
            String sql = "SELECT Cod_Escola FROM Escola WHERE Cod_Escola='" + (String) key + "'";
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
        public Escola get(Object key) {
        try {
            Escola al = null;
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM escola WHERE cod_escola='" + (String) key + "'";
            ResultSet rs = stm.executeQuery(sql);
            
            if (rs.next()) {
                al = new Escola(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
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
    public Escola put(String key, Escola value) {
        Escola al = null;
        try (Statement stm = conn.createStatement()) {

            int i = stm.executeUpdate("Update Escola set Cod_Escola='" + value.getCod_Escola() + "',nome='" + value.getNome() + "',endereco='" + value.getEndereco() + "',cidade='" + value.getLocalidade() + "' WHERE Cod_Escola='" + key + "'");
            if (i == 0) {
                String sql = "INSERT INTO Escola(Cod_Escola,nome,endereco,cidade) VALUES('" + value.getCod_Escola() + "','" + value.getNome() + "','" + value.getEndereco() + "','" + value.getLocalidade() + "')";
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
    public Escola remove(Object key) {
        try {
            String sql;
            int i;
            Escola al = this.get(key);
            Statement stm = conn.createStatement();
           /* String sql = "DELETE  FROM Jogador where cod_Escola='" + (String) key + "'";
            int i = stm.executeUpdate(sql);
             */
            sql = "DELETE  FROM Equipa where cod_Escola='" + (String) key + "'";
            i = stm.executeUpdate(sql);
            sql = "DELETE  FROM Escola where cod_Escola='" + (String) key + "'";
            i = stm.executeUpdate(sql);
            stm.close();
            return al;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

    @Override
    public void putAll(Map<? extends String, ? extends Escola> m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        try {
            Statement stm = conn.createStatement();
            stm.executeQuery("DELETE FROM JOGADOR");
            stm.executeQuery("DELETE FROM EQUIPA");
            stm.executeUpdate("DELETE FROM Escola");
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
    public Collection<Escola> values() {
        try {
            Collection<Escola> col = new HashSet<Escola>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Escola");
            
            for (; rs.next();) {

                col.add(new Escola(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            stm.close();
            return col;
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

    @Override
    public Set<Entry<String, Escola>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


   
}
