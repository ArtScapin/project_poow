package br.ufsm.csi.dao;

import br.ufsm.csi.model.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    private String sql;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();

        try(Connection connection = new ConnectionDB().getConexao()) {
            sql = "SELECT * FROM users";
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (this.resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));

                users.add(user);
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }

        return users;
    }

    public User getUser(String email){
        try(Connection connection = new ConnectionDB().getConexao()) {
            sql = "SELECT * FROM users WHERE email = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);

            resultSet = preparedStatement.executeQuery();

            User user = new User();
            while (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
            return user;
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return null;
    }

    public User getUser(int id){
        try(Connection connection = new ConnectionDB().getConexao()) {
            sql = "SELECT * FROM users WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            User user = new User();
            while (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
            return user;
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return null;
    }

    public boolean create(User user) {
        try(Connection connection = new ConnectionDB().getConexao()) {
            sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());

            preparedStatement.execute();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            if(resultSet.getInt(1) > 0){
                user.setId(resultSet.getInt(1));
            }

            return true;
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return false;
    }
    public boolean update(User user){
        try(Connection connection = new ConnectionDB().getConexao()) {
            sql = """
                    UPDATE users
                    SET name = ?, email = ?, password = ?
                    WHERE id = ?
                    """;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.execute();

            return true;
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return false;
    }
    public boolean delete(User user){
        try(Connection connection = new ConnectionDB().getConexao()) {
            sql = """
                    DELETE FROM users
                    WHERE id = ?
                        """;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.execute();

            return true;
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return false;
    }
}
