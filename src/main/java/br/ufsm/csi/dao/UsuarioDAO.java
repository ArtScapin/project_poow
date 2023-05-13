package br.ufsm.csi.dao;

import br.ufsm.csi.model.Permissao;
import br.ufsm.csi.model.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO {
    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;
    public ArrayList<Usuario> getUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql = """
                    SELECT * FROM usuario u
                    JOIN permissao_usuario pu ON pu.id_usuario = u.id_usuario
                    JOIN permissao p ON p.id_permissao = pu.id_permissao
                    """;
            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(sql);
            while (this.resultSet.next()){
                Usuario usuario = new Usuario();
                usuario.setId(this.resultSet.getInt("id_usuario"));
                usuario.setNome(this.resultSet.getString("nome"));
                usuario.setEmail(this.resultSet.getString("email"));
                usuario.setAtivo(this.resultSet.getBoolean("ativo"));

                Permissao permissao = new Permissao();
                permissao.setId(this.resultSet.getInt("id_us_perm"));
                permissao.setNome(this.resultSet.getString("nome_permissao"));
                usuario.setPermissao(permissao);

                usuarios.add(usuario);
            }

        } catch (SQLException error) {
            error.printStackTrace();
        }

        return usuarios;
    }

    public String cadastrar(Usuario usuario) {
        try(Connection connection = new ConectaDB().getConexao()) {
            connection.setAutoCommit(false);
            this.sql = "INSERT INTO usuario (nome, email, senha, data_cadastro, ativo) VALUES (?, ?, ?, current_timestamp, ?)";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, usuario.getNome());
            this.preparedStatement.setString(2, usuario.getEmail());
            this.preparedStatement.setString(3, usuario.getSenha());
            this.preparedStatement.setBoolean(4, usuario.isAtivo());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();
            if(this.resultSet.getInt(1) > 0){
                usuario.setId(this.resultSet.getInt(1));
                this.status = "OK";
            }

            if(this.status.equals("OK")) {
                this.sql = "INSERT INTO permissao_usuario (id_usuario, id_permissao) VALUES (?, ?)";
                this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
                this.preparedStatement.setInt(1, usuario.getId());
                this.preparedStatement.setInt(2, usuario.getPermissao().getId());
                this.preparedStatement.execute();
                this.resultSet = this.preparedStatement.getGeneratedKeys();
                this.resultSet.next();

                if(this.resultSet.getInt(1) > 0){
                    connection.commit();
                    this.status = "OK";
                }
            }
        } catch (SQLException error) {
            this.status = "PROBLEMA";
            error.printStackTrace();
        }
        return null;
    }

    public Usuario getUsuario(String email){
        Usuario usuario = new Usuario();
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql = """
                    SELECT * FROM usuario u
                    JOIN permissao_usuario pu ON pu.id_usuario = u.id_usuario
                    JOIN permissao p ON p.id_permissao = pu.id_permissao
                    WHERE u.email = ?
                    """;
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, email);

            this.resultSet = this.preparedStatement.executeQuery();
            while (this.resultSet.next()){
                System.out.println("nome: " + this.resultSet.getString("nome"));
                System.out.println("email: " + this.resultSet.getString("email"));
                System.out.println("data_cadastro: " + this.resultSet.getDate("data_cadastro"));
                System.out.println("permissao: " + this.resultSet.getString("nome_permissao"));
            }


        } catch (SQLException error) {
            this.status = "PROBLEMA";
            error.printStackTrace();
        }
        return usuario;
    }

    public Usuario getUsuario(int id){
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql = """
                    SELECT * FROM usuario u
                    JOIN permissao_usuario pu ON pu.id_usuario = u.id_usuario
                    JOIN permissao p ON p.id_permissao = pu.id_permissao
                    WHERE u.id_usuario = ?
                    """;
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);

            this.resultSet = this.preparedStatement.executeQuery();
            while (this.resultSet.next()){
                Usuario usuario = new Usuario();
                usuario.setId(this.resultSet.getInt("id_usuario"));
                usuario.setNome(this.resultSet.getString("nome"));
                usuario.setEmail(this.resultSet.getString("email"));
                usuario.setSenha(this.resultSet.getString("senha"));
                usuario.setData_cadastro(this.resultSet.getDate("data_cadastro"));
                usuario.setAtivo(this.resultSet.getBoolean("ativo"));

                Permissao permissao = new Permissao(this.resultSet.getInt("id_permissao"), this.resultSet.getString("nome_permissao"));
                usuario.setPermissao(permissao);
                return usuario;
            }

        } catch (SQLException error) {
            this.status = "PROBLEMA";
            error.printStackTrace();
        }
        return null;
    }
    public String atualizar(Usuario usuario){
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql = """
                    UPDATE usuario
                    SET nome = ?, email = ?, senha = ?, ativo = ?
                    WHERE id_usuario = ?
                    """;
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, usuario.getNome());
            this.preparedStatement.setString(2, usuario.getEmail());
            this.preparedStatement.setString(3, usuario.getSenha());
            this.preparedStatement.setBoolean(4, usuario.isAtivo());
            this.preparedStatement.setInt(5, usuario.getId());
            this.preparedStatement.execute();
            this.status = "OK";
        } catch (SQLException error) {
            this.status = "PROBLEMA";
            error.printStackTrace();
        }
        return this.status;
    }
    public String deletar(Usuario usuario){
        try(Connection connection = new ConectaDB().getConexao()) {
            connection.setAutoCommit(false);
            this.sql = """
                        DELETE FROM permissao_usuario
                        WHERE id_usuario = ?
                        """;
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, usuario.getId());
            this.preparedStatement.execute();
            this.sql = """
                        DELETE FROM usuario
                        WHERE id_usuario = ?
                        """;
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, usuario.getId());
            this.preparedStatement.execute();
            connection.commit();
            this.status = "OK";
        } catch (SQLException error) {
            this.status = "PROBLEMA";
            error.printStackTrace();
        }
        return this.status;
    }
}
