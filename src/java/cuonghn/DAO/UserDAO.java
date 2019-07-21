/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuonghn.DAO;

import cuonghn.DTO.UserDTO;
import cuonghn.jaxb.Film;
import cuonghn.utils.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author nhatc
 */
public class UserDAO {

    public Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;
    boolean autoAcceptChange = false;

    private void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            System.out.println("some things happen prevent close connection");
        } finally {
        }
    }

    public UserDTO checkLogin(UserDTO user) {
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "select name, history,role_name, role from user_tbl u , role_tbl r where r.role_id = u.role and username= ? and password=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String roleName = resultSet.getString("role_name");
                    int role = resultSet.getInt("role");
                    String history = resultSet.getString("history");
                    return new UserDTO(user.getUsername(), name, roleName, history);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    public boolean userUpdateSearchHistory(UserDTO user) {
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "update user_tbl set history=? where userName = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, user.getHistory());
                preparedStatement.setString(2, user.getUsername());
                int rs = preparedStatement.executeUpdate();
                if (rs > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean signUp(UserDTO user) {
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "insert into user_tbl(userName, password, role,name) values (?,?,?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setInt(3, 2);
                preparedStatement.setString(4, user.getName());
                int rs = preparedStatement.executeUpdate();
                if (rs > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }
}
