/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Competicao.Campo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class DAOCampo implements Map<String,Campo>{
    public Connection conn;

    public DAOCampo() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            this.conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/orcl", "BASEDADOS", "bd");
        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        }
    }
    @Override
    public int hashCode() {
		int lHashCode = 0;
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

    @Override
	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof DAOCampo) {
			DAOCampo lDAOCampoObject = (DAOCampo) aObject;
			boolean lEquals = true;
			return lEquals;
		}
		return false;
	}

    @Override
    public int size() {
       Statement stm = null;
        try {
            int i = 0;
           stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT Cod_Campo FROM Campo");
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
            ResultSet rs = stm.executeQuery("SELECT Cod_Campo FROM Campo");
            
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
            String sql = "SELECT Cod_Campo FROM Campo WHERE Cod_Campo='" + (String) key + "'";
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
    public Campo get(Object key) {
        try {
            Campo al = null;
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM Campo WHERE Cod_Campo='" + (String) key + "'";
            ResultSet rs = stm.executeQuery(sql);
            
            if (rs.next()) {
                al = new Campo(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5));
            }
            rs.close();
            return al;
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    @Override
    public Campo put(String key, Campo value) {
        try {
            int i;
            Campo al = null;
            try (Statement stm = conn.createStatement()) {
                i=stm.executeUpdate("Update Campo set Cod_campo='"+value.getCodigo()+"',Nome='"+value.getNome()+"',Localidade='"+value.getLocalidade()+"',Quadrantes=to_number('"+value.getQuadrantes()+"'),Cod_Escola='"+value.getCod_Escola()+"' WHERE Cod_Campo='" + key + "'");
            }
            if(i==0){
            Statement stm = this.conn.createStatement();

            String sql = "INSERT INTO Campo(Cod_Campo,Nome,Localidade,Quadrantes,Cod_Escola) VALUES('" +value.getCodigo()+ "','"+value.getNome()+"','"+value.getLocalidade()+"',to_number('"+value.getQuadrantes()+"'),'"+value.getCod_Escola()+"')";

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
      public Campo remove(Object key) {
        try {
            Campo al = this.get(key);
            Statement stm = conn.createStatement();
            String sql = "DELETE FROM Campo where cod_campo='" + (String) key + "'";
            int i = stm.executeUpdate(sql);
            stm.close();
            return al;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

    @Override
    public void putAll(Map<? extends String, ? extends Campo> m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM Campo");
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
    public Collection<Campo> values() {
        try {
            Collection<Campo> col = new HashSet<Campo>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Campo");
            
            for (; rs.next();) {

                col.add(new Campo(rs.getString(1), rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5)));
            }
            stm.close();
            return col;
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }
    
    public Collection<Campo> values(String Escola) {
        try {
            Collection<Campo> col = new HashSet<Campo>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Campo where cod_escola='"+Escola+"'");
            
            for (; rs.next();) {

                col.add(new Campo(rs.getString(1), rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5)));
            }
            rs.close();
            return col;
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

    @Override
    public Set<Entry<String, Campo>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
