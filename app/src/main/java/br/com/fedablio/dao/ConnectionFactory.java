package br.com.fedablio.dao;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    public static String url = "";
    public static String por = "";
    public static String ban = "";
    public static String use = "";
    public static String pas = "";

    public Connection getConexao() {
        Connection conexao = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://"+url+":"+por+"/"+ban, use, pas);
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
        return conexao;
    }

}