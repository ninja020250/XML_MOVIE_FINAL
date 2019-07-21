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
import java.util.List;

/**
 *
 * @author nhatc
 */
public class TempDataDAO {

    public Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    public int masterInsertRawData(ListFilm listFilm) {
        int countSuccess = 0;
        List<Film> list = listFilm.getFilm();
        for (int i = 0; i < list.size(); i++) {
            Film film = list.get(i);
            boolean result = insertRawData(film);
            if (result) {
                countSuccess++;
            } else {
                System.out.println("");
            }
        }
        return countSuccess;
    }

    public int masterInsertTemp(ListFilm listFilm) {
        int countSuccess = 0;
        List<Film> list = listFilm.getFilm();
        for (int i = 0; i < list.size(); i++) {
            Film film = list.get(i);
            boolean result = insertFilmtemp(film);
            if (result) {
                countSuccess++;
            } else {
                System.out.println("");
            }
        }
        return countSuccess;
    }

    public int masterInsertUpdateTemp(ListFilm listFilm) {
        int countSuccess = 0;
        List<Film> list = listFilm.getFilm();
        for (int i = 0; i < list.size(); i++) {
            Film film = list.get(i);
            boolean result = insertFilmWaitToUpdate(film);
            if (result) {
                countSuccess++;
            }
        }
        return countSuccess;
    }

    public int masterInsertAddTemp(ListFilm listFilm) {
        int countSuccess = 0;
        List<Film> list = listFilm.getFilm();
        for (int i = 0; i < list.size(); i++) {
            Film film = list.get(i);
            boolean result = insertFilmWaitToAdd(film);
            if (result) {
                countSuccess++;
            }
        }
        return countSuccess;
    }

    public int masterInsertDel(ListFilm listFilm) {
        int countSuccess = 0;
        List<Film> list = listFilm.getFilm();
        for (int i = 0; i < list.size(); i++) {
            Film film = list.get(i);
            boolean result = insertFilmWaitToDelete(film);
            if (result) {
                countSuccess++;
            }
        }
        return countSuccess;
    }

    public boolean clearRawData() {
        ListFilm result = null;
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "delete from raw_data_tbl";
                preparedStatement = connection.prepareStatement(sql);
                int rs = preparedStatement.executeUpdate();
                while (rs > 0) {
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

    public ListFilm getAllRawData() {
        ListFilm result = null;
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "select FilmXML  from raw_data_tbl";
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String FilmXML = resultSet.getString("FilmXML");
                    Film film = XMLUtilities.unmarshallingFilm(FilmXML);

                    if (film != null) {
                        if (result == null) {
                            result = new ListFilm();
                        }
                        result.addFilm(film);
                    }

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

    public boolean insertFilmWaitToDelete(Film film) {
        try {
            String filmXML = XMLUtilities.marsalData(film);

            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "insert into Film_wait_to_del (FilmXML) values (?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, filmXML);
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

    public boolean insertRawData(Film film) {
        try {
            String filmXML = XMLUtilities.marsalData(film);

            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "insert into raw_data_tbl (FilmXML) values (?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, filmXML);
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

    public boolean insertFilmtemp(Film film) {
        try {
            String filmXML = XMLUtilities.marsalData(film);

            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "insert into Film_temp (FilmXML) values (?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, filmXML);
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

    public boolean insertFilmWaitToUpdate(Film film) {
        try {
            String filmXML = XMLUtilities.marsalData(film);

            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "insert into Film_wait_to_update (FilmXML) values (?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, filmXML);
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

    public boolean insertFilmWaitToAdd(Film film) {
        try {
            String filmXML = XMLUtilities.marsalData(film);

            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "insert into Film_wait_to_add (FilmXML) values (?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, filmXML);
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
