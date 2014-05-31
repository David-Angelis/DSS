/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Competicao.Campeonato;
import Competicao.Competicao;
import Competicao.EliminatoriaDupla;
import Competicao.Eliminatorias;
import Competicao.Torneio;
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
public class DAOCompeticao implements Map<String, Competicao> {

    public Connection conn;

    public DAOCompeticao() {
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
            ResultSet rs = stm.executeQuery("SELECT Cod_Competicao FROM Competicao where cod_competicao='"+cod+"'");

            for (; rs.next(); i++);
            rs.close();
            return i;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return -1;
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
        } else if (aObject instanceof DAOCompeticao) {
            DAOCompeticao lDAOCompeticaoObject = (DAOCompeticao) aObject;
            boolean lEquals = true;
            return lEquals;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT Cod_Compeonato FROM Competicao");

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
            String sql = "SELECT Cod_Competicao FROM Competicao WHERE Cod_Competicao='" + (String) key + "'";
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
    public Competicao get(Object key) {
        try {
            Competicao al = null;
            Statement stm = conn.createStatement();
            String sql = "SELECT Competicao.*,Torneio.* FROM Torneio,competicao WHERE Torneio.Cod_Torneio='" + (String) key + "'and competicao.cod_competicao=torneio.cod_torneio";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()){ 
                if(rs.getInt("tipotorneio")==0) {
                al = new Eliminatorias(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                rs.close();
                return al;
            } else {
                if(rs.getInt("tipotorneio")==1){
                 al = new EliminatoriaDupla(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)); 
                 rs.close();
                 return al;
                }
                }
            }
                sql = "SELECT Competicao.*,Campeonato.* FROM Campeonato,competicao WHERE Campeonato.Cod_Campeonato='" + (String) key + "'and competicao.cod_competicao=Campeonato.cod_campeonato";
                rs = stm.executeQuery(sql);
                if (rs.next()) {
                    al = new Campeonato(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                    rs.close();
                }
            
            
            return al;
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    @Override
    public Competicao put(String key, Competicao value) {
        try {
            int i = 0;
            Competicao al = null;
            try (Statement stm = conn.createStatement()) {

                if (value.getClass().getName().equals("Competicao.Eliminatorias")) {
                    i = stm.executeUpdate("Update  Torneio set Cod_Torneio='" + value.getCod_competicao() + "' WHERE Cod_Torneio='" + key + "'");
                    i += stm.executeUpdate("Update  Competicao set Cod_Competicao='" + value.getCod_competicao() + "',Nome='" + value.getNome() + "',Escalao='" + value.getEscalao().toString() + "', Ano=to_number('" + value.getAno() + "') WHERE Cod_Competicao='" + key + "'");
                    if (i == 0) {
                        String sql = "INSERT INTO Competicao(Cod_Competicao,Nome,Escalao,Ano) VALUES('" + value.getCod_competicao() + "','" + value.getNome() + "','" + value.getEscalao().toString() + "',to_number('" + value.getAno() + "'))";
                        i = stm.executeUpdate(sql);
                        sql = "INSERT INTO Torneio(Cod_Torneio,tipoCompeticao,tipotorneio) VALUES('" + value.getCod_competicao() + "',to_number('1'),to_number('0'))";
                        stm.executeUpdate(sql);
                        stm.close();
                        return value;
                    } else {
                        return value;
                    }
                } else {
                    if (value.getClass().getName().equals("Competicao.EliminatoriaDupla")) {
                        i = stm.executeUpdate("Update  Torneio set Cod_Torneio='" + value.getCod_competicao() + "' WHERE Cod_Torneio='" + key + "'");
                        i += stm.executeUpdate("Update  Competicao set Cod_Competicao='" + value.getCod_competicao() + "',Nome='" + value.getNome() + "',Escalao='" + value.getEscalao().toString() + "', Ano=to_number('" + value.getAno() + "') WHERE Cod_Competicao='" + key + "'");
                        if (i == 0) {
                            String sql = "INSERT INTO Competicao(Cod_Competicao,Nome,Escalao,Ano) VALUES('" + value.getCod_competicao() + "','" + value.getNome() + "','" + value.getEscalao().toString() + "',to_number('" + value.getAno() + "'))";
                            i = stm.executeUpdate(sql);
                            sql = "INSERT INTO Torneio(Cod_Torneio,tipoCompeticao,tipotorneio) VALUES('" + value.getCod_competicao() + "',to_number('1'),to_number('1'))";
                            stm.executeUpdate(sql);
                            stm.close();
                            return value;
                        } else {
                            return value;
                        }
                    }
                    i = stm.executeUpdate("Update  Campeonato set Cod_Campeonato='" + value.getCod_competicao() + "' WHERE Cod_Campeonato='" + key + "'");
                    i += stm.executeUpdate("Update  Competicao set Cod_Competicao='" + value.getCod_competicao() + "',Nome='" + value.getNome() + "',Escalao='" + value.getEscalao().toString() + "',Ano=to_number('" + value.getAno() + "') WHERE Cod_Competicao='" + key + "'");
                    
                    if (i == 0) {
                        String sql1 = "INSERT INTO Competicao(Cod_Competicao,Nome,Escalao,Ano) VALUES('" + value.getCod_competicao() + "','" + value.getNome() + "','" + value.getEscalao().toString() + "',to_number('" + value.getAno() + "'))";
                        i = stm.executeUpdate(sql1);
                        sql1 = "INSERT INTO Campeonato(Cod_Campeonato,TipoCompeticao) VALUES('" + value.getCod_competicao() + "',to_number('0'))";
                        stm.executeUpdate(sql1);
                        stm.close();
                        return value;
                    } else {
                        return value;
                    }

                }

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    @Override
    public Competicao remove(Object key) {
        try {
            Competicao al = this.get((String) key);
            Statement stm = conn.createStatement();
            if (al.getClass().getName().equals("Competicao.Eliminatorias") || al.getClass().getName().equals("Competicao.EliminatoriaDupla")) {

                String sql = "DELETE  FROM EquipaTorneio where cod_competicao='" + (String) key + "'";
                int i = stm.executeUpdate(sql);
                sql = "DELETE  FROM Marcadores where cod_competicao='" + (String) key + "'";
                i = stm.executeUpdate(sql);
                sql = "DELETE  FROM Torneio where cod_torneio='" + (String) key + "'";
                i = stm.executeUpdate(sql);
                sql = "DELETE  FROM Competicao where cod_competicao='" + (String) key + "'";
                i = stm.executeUpdate(sql);
                stm.close();
                return al;
            } else {
                String sql = "DELETE  FROM Campeonato where cod_campeonato='" + (String) key + "'";
                int i = stm.executeUpdate(sql);
                sql = "DELETE  FROM EquipaCampeonato where cod_competicao='" + (String) key + "'";
                i = stm.executeUpdate(sql);
                sql = "DELETE  FROM Marcadores where cod_competicao='" + (String) key + "'";
                i = stm.executeUpdate(sql);
                sql = "DELETE  FROM Competicao where cod_competicao='" + (String) key + "'";
                i = stm.executeUpdate(sql);
                stm.close();
                return al;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

    @Override
    public void putAll(Map<? extends String, ? extends Competicao> m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM Torneio");
            stm.executeUpdate("DELETE FROM CAMPEONATO");
            stm.executeQuery("DELETE FROM COMPETICAO");
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
    public Collection<Competicao> values() {
        try {
            Collection<Competicao> col = new HashSet<Competicao>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Competicao,Torneio where  torneio.cod_torneio=competicao.cod_competicao");

            for (; rs.next();) {
                if (rs.getString(7).equals("0")) {
                    col.add(new Eliminatorias(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
                }else if(rs.getString(7).equals("1")){
                    col.add(new EliminatoriaDupla(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
                }
            }
            rs = stm.executeQuery("SELECT * FROM Competicao,Campeonato where  Campeonato.cod_campeonato=competicao.cod_competicao");
            for (; rs.next();) {

                col.add(new Campeonato(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            rs.close();
            return col;
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

    @Override
    public Set<Entry<String, Competicao>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removerJogos(String cod_competicao) {
        this.get(cod_competicao).removerJogos(cod_competicao);
    }

    public void removerJornadas(String cod_competicao) {
        this.get(cod_competicao).removerJornadas(cod_competicao);
    }

    @Override
    public int size() {
         try {
            int i = 0;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT Cod_Competicao FROM Competicao");

            for (; rs.next(); i++);
            rs.close();
            return i;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

}
