package br.ufsm.csi.dao;

import br.ufsm.csi.model.List;
import br.ufsm.csi.model.Item;
import br.ufsm.csi.model.Workspace;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAO {

    private String sql;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    public ArrayList<Item> getItems(List list) {
        ArrayList<Item> items = new ArrayList<>();

        try(Connection connection = new ConnectionDB().getConexao()) {
            sql = "SELECT * FROM items WHERE id_list = ? ORDER BY id ASC";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, list.getId());
            resultSet = preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Item item = new Item();
                item.setId(resultSet.getInt("id"));
                item.setName(resultSet.getString("name"));
                item.setStatus(resultSet.getBoolean("status"));
                item.setList(list);

                items.add(item);
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }

        return items;
    }
    public Item getItem(int id, List list) {
        try(Connection connection = new ConnectionDB().getConexao()) {
            sql = "SELECT * FROM items WHERE id_list = ? AND id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, list.getId());
            preparedStatement.setInt(2, id);
            resultSet = preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Item item = new Item();
                item.setId(resultSet.getInt("id"));
                item.setName(resultSet.getString("name"));
                item.setStatus(resultSet.getBoolean("status"));
                item.setList(list);

                return item;
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }

        return null;
    }

    public boolean create(Item item) {
        try(Connection connection = new ConnectionDB().getConexao()) {
            sql = "INSERT INTO items (name, status, id_list) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setBoolean(2, item.getStatus());
            preparedStatement.setInt(3, item.getList().getId());

            preparedStatement.execute();

            return true;
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return false;
    }
    public boolean changeStatus(Item item) {
        try(Connection connection = new ConnectionDB().getConexao()) {
            sql = "UPDATE items SET status = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, !item.getStatus());
            preparedStatement.setInt(2, item.getId());

            preparedStatement.execute();

            return true;
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return false;
    }
}
