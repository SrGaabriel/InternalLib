package net.gabriel.internal.library.com.storage.mysql;

import org.bukkit.Bukkit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InternalSQL {

    private String ip;
    private String port;
    private String database;
    private String user;
    private String password;

    private Connection con;

    public InternalSQL(String ip, String porta, String database, String user, String password) {
        this.ip = ip;
        this.port = porta;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    public void connect() {
        if (!(isConnected())) {
            try {
                Class.forName("java.sql.DriverManager");
                con = DriverManager.getConnection(
                        "jdbc:mysql://" + ip + ":" + port + "/" + database, user, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            print("§cThe connection is already opened!");
        }
    }

    // On this method you can execute any updates you want to.
    public void executeUpdate(String sql) {
        if (isConnected()) {
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.executeUpdate();
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Returns a Object from the selected field.
    public Object getObject(String sql, String field) {
        if (isConnected()) {
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                rs.next();
                Object returnValue = rs.getObject(field);
                rs.close();
                ps.close();
                return returnValue;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // This method return all values from a field.
    public List<Object> getFieldValues(String table, String field) {
        if (isConnected()) {
            try {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM ?");
                ps.setString(1, table);
                ResultSet rs = ps.executeQuery();
                final List<Object> list = new ArrayList<>();
                // Get all results from the ResultSet and put then into the list.
                while (rs.next()) {
                    list.add(rs.getObject(field));
                }
                rs.close();
                ps.close();
                return list;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                con.close();
                con = null;
                print("§cConexão MySQL interrompida com sucesso!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            print("§cA conexão é inexistente!");
        }
    }

    public boolean isConnected() {
        return con != null;
    }

    private void print(String s){ Bukkit.getConsoleSender().sendMessage(s);}

}