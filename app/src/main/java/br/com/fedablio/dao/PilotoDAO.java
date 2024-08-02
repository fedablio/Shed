package br.com.fedablio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import br.com.fedablio.model.Piloto;
import br.com.fedablio.utility.Porta;

public class PilotoDAO {

    private static Connection conn;
    private PreparedStatement stmt;
    private Statement st;
    private ResultSet rs;
    Porta porta = new Porta();

    public PilotoDAO(){
        conn = new ConnectionFactory().getConexao();
    }

    public void inserir(Piloto pil){
        String sql = "INSERT INTO piloto (senha_piloto, nome_piloto, canal_piloto, banda_piloto) VALUES (?,?,?,?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pil.getSenha_piloto());
            stmt.setString(2, pil.getNome_piloto());
            stmt.setString(3, pil.getCanal_piloto());
            stmt.setString(4, pil.getBanda_piloto());
            stmt.execute();
            stmt.close();
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }

    public void alterar(Piloto pil){
        String sql = "UPDATE piloto SET nome_piloto = ?, canal_piloto = ?, banda_piloto = ?, senha_piloto = ? WHERE id_piloto = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pil.getNome_piloto());
            stmt.setString(2, pil.getCanal_piloto());
            stmt.setString(3, pil.getBanda_piloto());
            stmt.setString(4, pil.getSenha_piloto());
            stmt.setInt(5, pil.getId_piloto());
            stmt.execute();
            stmt.close();
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }

    public boolean excluir(int id, String senha){
        boolean resultado = false;
        String sql = "DELETE FROM piloto WHERE id_piloto = '"+id+"' AND senha_piloto = '"+senha+"' ";
        try {
            st = conn.createStatement();
            if(st.execute(sql)){
                resultado = true;
            }
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
        return resultado;
    }

    public ArrayList<Piloto> listarPilotoSenha(String senha, String banda) {
        ArrayList<Piloto> lista = new ArrayList<>();
        String sql = "";
        if(banda.equals(porta.fecha("Your band"))){
            sql = "SELECT * FROM piloto WHERE senha_piloto = '"+senha+"' ";
        }else{
            sql = "SELECT * FROM piloto WHERE senha_piloto = '"+senha+"' AND banda_piloto = '"+banda+"' ";
        }
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Piloto pil = new Piloto();
                pil.setId_piloto(rs.getInt("id_piloto"));
                pil.setSenha_piloto(rs.getString("senha_piloto"));
                pil.setNome_piloto(porta.abre(rs.getString("nome_piloto")));
                pil.setCanal_piloto(porta.abre(rs.getString("canal_piloto")));
                pil.setBanda_piloto(porta.abre(rs.getString("banda_piloto")));
                lista.add(pil);
            }
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
        return lista;
    }

}