/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuonghn.DAO;

import cuonghn.jaxb.Film;
import cuonghn.jaxb.ListFilm;
import cuonghn.utils.DBUtil;
import cuonghn.utils.XMLUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nhatc
 */
public class KindOfFilmDAO {

    public Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

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

    public List<String> getAllKindOfFilm() {
        List<String> result = null;
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "select kind  from kinkOfFilm_tbl";
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String kind = resultSet.getString("kind");
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(kind);
                }
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }
}
