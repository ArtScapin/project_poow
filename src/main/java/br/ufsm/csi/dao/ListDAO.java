package br.ufsm.csi.dao;

import br.ufsm.csi.model.List;
import br.ufsm.csi.model.Workspace;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListDAO {

    private String sql;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    public ArrayList<List> getLists(Workspace workspace) {
        ArrayList<List> lists = new ArrayList<>();

        try(Connection connection = new ConnectionDB().getConexao()) {
            sql = "SELECT * FROM lists WHERE id_workspace = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, workspace.getId());
            resultSet = preparedStatement.executeQuery();

            while (this.resultSet.next()){
                List list = new List();
                list.setId(resultSet.getInt("id"));
                list.setName(resultSet.getString("name"));
                list.setWorkspace(workspace);

                lists.add(list);
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }

        return lists;
    }
    public List getList(int id, Workspace workspace) {
        try(Connection connection = new ConnectionDB().getConexao()) {
            sql = "SELECT * FROM lists WHERE id_workspace = ? AND id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, workspace.getId());
            preparedStatement.setInt(2, id);
            resultSet = preparedStatement.executeQuery();

            while (this.resultSet.next()){
                List list = new List();
                list.setId(resultSet.getInt("id"));
                list.setName(resultSet.getString("name"));
                list.setWorkspace(workspace);

                return list;
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }

        return null;
    }

    public boolean create(List list) {
        try(Connection connection = new ConnectionDB().getConexao()) {
            sql = "INSERT INTO lists (name, id_workspace) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, list.getName());
            preparedStatement.setInt(2, list.getWorkspace().getId());

            preparedStatement.execute();

            return true;
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return false;
    }
}
