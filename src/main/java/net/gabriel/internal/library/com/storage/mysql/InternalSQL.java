package net.gabriel.internal.library.com.storage.mysql;

import org.bukkit.Bukkit;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
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

    public List<Object> getAllValuesFromField(String table, String field) {
        if (isConnected()) {
            try {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM ?");
                ResultSet rs = ps.executeQuery();
                final List<Object> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(rs.getObject("field"));
                }
                return list;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void disconnect() {
        if (isConnected()) {
            con = null;
            print("§cConexão MySQL interrompida com sucesso!");
        } else {
            print("§cA conexão é inexistente!");
        }
    }

    public boolean isConnected() {
        return con != null;
    }
    private void print(String s){ Bukkit.getConsoleSender().sendMessage(s);}

}