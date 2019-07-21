/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuonghn.DAO;

import cuonghn.jaxb.Film;
import cuonghn.jaxb.FilmEvalute;
import cuonghn.jaxb.ListFilmEvalute;
import cuonghn.jaxb.Website;
import cuonghn.utils.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nhatc
 */
public class EvaluateDAO {

    public Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    public EvaluateDAO() {
    }

    public boolean insertNewFilmRateDescription(Film film) {
        boolean isUpdate = false;
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "insert into film_rate_description(filmID, rate,websitename,description, filmURL, numberOfView) values (?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, film.getFilmID().toLowerCase());
                preparedStatement.setFloat(2, film.getRate());
                preparedStatement.setString(3, film.getWebsiteName());
                preparedStatement.setString(4, film.getDescription());
                preparedStatement.setString(5, film.getFilmURL());
                preparedStatement.setInt(6, film.getNumberOfView());
                int result = preparedStatement.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            if (e.getMessage().contains("duplicate key")) {
//                isUpdate = true;
            } else {
                System.out.println(e.getMessage());
            }
        } finally {
            closeConnection();
        }
//        if (isUpdate && autoAcceptChange) {
//            updateFilmRateDescription(film);
//        }
        return false;
    }

    public boolean deleteFilmRateDescription(Film film) {
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "DELETE film_rate_description  where filmID = ? and websitename=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, film.getFilmID());
                preparedStatement.setString(2, film.getWebsiteName());
                int result = preparedStatement.executeUpdate();
                if (result > 0) {
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

    public boolean updateFilmRateDescription(Film film) {
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "update film_rate_description set rate =? ,description = ? where filmID = ? and websitename=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setFloat(1, film.getRate());
                preparedStatement.setString(2, film.getDescription());
                preparedStatement.setString(3, film.getFilmID());
                preparedStatement.setString(4, film.getWebsiteName());
                int result = preparedStatement.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            if (e.getMessage().contains("duplicate key")) {
            } else {
                System.out.println(e.getMessage());
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public ListFilmEvalute getFilmEvalute(String filmID) {
        ListFilmEvalute listFilmEvalute = null;
        List<FilmEvalute> fe = null;
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "select websitename, rate, description, filmURL, numberOfView from film_rate_description where filmID=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setNString(1, filmID);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (fe == null) {
                        fe = new ArrayList<>();
                    }
                    String websiteName = resultSet.getString("websitename");
                    float rate = resultSet.getFloat("rate");
                    String description = resultSet.getString("description");
                    String filmURL = resultSet.getString("filmURL");
                    int numberOfView = resultSet.getInt("numberOfView");
                    FilmEvalute f = new FilmEvalute();
                    f.setWebsite(new Website(websiteName));
                    f.setRated(rate);
                    f.setDescription(description);
                    f.setUrl(filmURL);
                    f.setNumberOfView(numberOfView);
                    fe.add(f);
                }
                listFilmEvalute = new ListFilmEvalute();
                listFilmEvalute.setFilmEvalute(fe);
                return listFilmEvalute;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return listFilmEvalute;
    }

    public void truncateTable(String tableName) throws Exception {

        try {
            String sql
                    = "truncate table " + tableName;
            connection = DBUtil.makeDBConnection();
            preparedStatement = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (Exception e) {
//            Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection();
        }
    }

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
}
