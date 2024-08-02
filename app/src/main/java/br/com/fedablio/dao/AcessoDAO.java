package br.com.fedablio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import br.com.fedablio.model.Acesso;

public class AcessoDAO {

    private static Connection conn;
    private PreparedStatement stmt;
    private Statement st;
    private ResultSet rs;

    public AcessoDAO(){
        conn = new ConnectionFactory().getConexao();
    }

    public void registrar(Acesso ace){
        String sql = "INSERT INTO acesso (data_acesso, hora_acesso) VALUES (?,?)";
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setDate(1, ace.getData());
            stmt.setTime(2, ace.getHora());
            stmt.execute();
            stmt.close();
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }

}
