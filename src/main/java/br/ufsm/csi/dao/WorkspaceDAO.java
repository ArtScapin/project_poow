package br.ufsm.csi.dao;

import br.ufsm.csi.model.User;
import br.ufsm.csi.model.Workspace;

import java.sql.*;
import java.util.ArrayList;

public class WorkspaceDAO {
    private String sql;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    public ArrayList<Workspace> getWorkspaces(User user) {
        ArrayList<Workspace> workspaces = new ArrayList<>();

        try(Connection connection = new ConnectionDB().getConexao()) {
            sql = "SELECT * FROM workspaces WHERE id_user = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());
            resultSet = preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Workspace workspace = new Workspace();
                workspace.setId(resultSet.getInt("id"));
                workspace.setName(resultSet.getString("name"));

                workspaces.add(workspace);
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }

        return workspaces;
    }

    public boolean create(Workspace workspace) {
        try(Connection connection = new ConnectionDB().getConexao()) {
            sql = "INSERT INTO workspaces (name, id_user) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, workspace.getName());
            preparedStatement.setInt(2, workspace.getUser().getId());

            preparedStatement.execute();

            return true;
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return false;
    }
}
