/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuonghn.DAO;

import cuonghn.jaxb.Film;
import cuonghn.jaxb.FilmEvalute;
import cuonghn.jaxb.ListFilm;
import cuonghn.jaxb.ListFilmEvalute;
import cuonghn.jaxb.Website;
import cuonghn.utils.DBUtil;
import cuonghn.utils.TextUtilities;
import cuonghn.utils.XMLUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nhatc
 */
public class FilmDAO {

    public Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;
    boolean autoAcceptChange = false;

    public FilmDAO() {
    }

    public FilmDAO(boolean autoAcceptChange) {
        this.autoAcceptChange = autoAcceptChange;
    }

    public void masterInsertDB(ListFilm listFilm) {
        List<Film> list = listFilm.getFilm();
        for (int i = 0; i < list.size(); i++) {
            Film film = list.get(i);
            insertNewWebsite(film);
            insertNewKindOfFim(film);
            insertNewFilm(film);
            insertFilmDetail(film);
            insertNewFilmRateDescription(film);
        }
    }

    public boolean insertNewFilm(Film film) {
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "insert into film_tbl (IDFilm, filmName,time, duration, limitAge, imgURL,engName, searchContent) values ( ?, ? ,? ,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, film.getFilmID().toLowerCase());
                preparedStatement.setString(2, film.getFilmName());
                preparedStatement.setInt(3, film.getTime());
                int duration = (film.getDuration() == null) ? 0 : film.getDuration();
                preparedStatement.setInt(4, duration);
                String limitname = (film.getLimitAge() == null) ? "unknow" : film.getLimitAge();
                preparedStatement.setString(5, limitname);
                preparedStatement.setString(6, film.getImageURL());
                preparedStatement.setString(7, film.getEngName());
                String searchContent = film.getFilmName() + film.getEngName() + film.getDescription() + film.getKindOfFilms().toString();
                searchContent = TextUtilities.removeSpecialCharacter(searchContent);
                preparedStatement.setString(8, searchContent.toLowerCase());
                int result = preparedStatement.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            if (e.getMessage().contains("duplicate key")) {
                System.out.println("Error: This Film existed");
            } else {
                System.out.println(e.getMessage());
            }
        } finally {
            closeConnection();
        }
        return false;
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
                String des = (film.getDescription() == null) ? "" : film.getDescription();
                preparedStatement.setString(4, des);
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

    public boolean insertNewKindOfFim(Film film) {
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                for (int i = 0; i < film.getKindOfFilms().size(); i++) {
                    String kindOfFilm = film.getKindOfFilms().get(i);
                    String sql = "insert into kinkOfFilm_tbl (kind) values (?)";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, kindOfFilm);

                    try {
                        int result = preparedStatement.executeUpdate();
//                        if (result < 0) {
//                            return false;
//                        }
                    } catch (Exception e) {
                        System.out.println("Error: This kind existed");
                    }
                }
                return true;
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

    public boolean insertFilmDetail(Film film) {
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                for (int i = 0; i < film.getKindOfFilms().size(); i++) {
                    String kindOfFilm = film.getKindOfFilms().get(i);
                    String sql = "insert into filmDetail ( filmID, kindOfFilmID, director, actors) values (?,?,?,?)";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, film.getFilmID().toLowerCase());
                    preparedStatement.setString(2, film.getKindOfFilms().get(i));
                    String director = film.GetStringDirector();
                    preparedStatement.setString(3, director);
                    String actors = film.getStringActor();
                    preparedStatement.setString(4, actors);
                    int result = preparedStatement.executeUpdate();
//                    if (result < 0) {
//                        return false;
//                    }
                }
                return true;
            }
        } catch (Exception e) {
            if (e.getMessage().contains("duplicate key")) {
                System.out.println("Error: This kind existed");
            } else {
                System.out.println(e.getMessage());
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean insertNewDefaultRecommandation(String filmID) {
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "insert into recommandation_tbl (filmID) values ( ?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, filmID);
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

    public boolean insertNewWebsite(Film film) {
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "insert into website_tbl (websiteName,host) values (?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, film.getWebsiteName());
                preparedStatement.setString(2, film.getWebsiteURL());
                int result = preparedStatement.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            if (e.getMessage().contains("duplicate key")) {
                // System.out.println("Error: This Film existed");
            } else {
                System.out.println(e.getMessage());
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean insertNewWebsite(String webName, String host) {
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "insert into website_tbl (websiteName,host) values (?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, webName);
                preparedStatement.setString(2, host);
                int result = preparedStatement.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            if (e.getMessage().contains("duplicate key")) {
                // System.out.println("Error: This Film existed");
            } else {
                System.out.println(e.getMessage());
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public ListFilm getAllFilmTemp() {
        ListFilm result = null;
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "select FilmXML  from Film_temp";
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

    public ListFilm getAllFilmIncomming() {
        ListFilm result = null;
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "select FilmXML  from Film_wait_to_add";
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

    public ListFilm getAllFilmWillUpdate() {
        ListFilm result = null;
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "select FilmXML  from Film_wait_to_update";
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

    public ListFilm getAllFilmWillDel() {
        ListFilm result = null;
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "select FilmXML  from Film_wait_to_del";
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

    public ListFilm getAllFilmRateDesciption() {
        ListFilm result = null;
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "select filmID, websitename, rate, description, filmURL, numberOfView  from film_rate_description";
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String filmID = resultSet.getString("filmID");
                    String websitename = resultSet.getString("websitename");
                    float rate = resultSet.getFloat("rate");
                    String description = resultSet.getString("description");
                    String filmURL = resultSet.getString("filmURL");
                    int numberOfView = resultSet.getInt("numberOfView");
                    if (result == null) {
                        result = new ListFilm();
                    }
                    Film film = new Film();
                    film.setFilmID(filmID);
                    film.setWebsiteName(websitename);
                    film.setRate(rate);
                    film.setDescription(description);
                    film.setFilmURL(filmURL);
                    film.setNumberOfView(numberOfView);
                    result.addFilm(film);
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

    public ListFilm getAllFilm() {
        ListFilm result = null;
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "select filmName, IDFilm, imgURL, time,limitAge, duration, engName, searchContent from film_tbl";
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String filmName = resultSet.getString("filmName");
                    String filmID = resultSet.getString("IDFilm");
                    String imgURL = resultSet.getString("imgURL");
                    int time = resultSet.getInt("time");
                    String limitAge = resultSet.getString("limitAge");
                    int duration = resultSet.getInt("duration");
                    String engName = resultSet.getString("engName");
                    String searchContent = resultSet.getString("searchContent");
                    Film f = new Film(filmID, filmName, imgURL);
                    f.setEngName(engName);
                    f.setTime(time);
                    f.setLimitAge(limitAge);
                    f.setDuration(duration);
                    f.setSearchContent(searchContent);
                    sql = "select k.kind FROM kinkOfFilm_tbl k, filmDetail fd where fd.filmID=? and fd.kindOfFilmID=k.kind";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setNString(1, filmID);
                    ResultSet rs = preparedStatement.executeQuery();
                    while (rs.next()) {
                        String kindOfFilm = rs.getString("kind");
                        f.getKindOfFilms().add(kindOfFilm);
                    }
                    rs.close();
                    if (result == null) {
                        result = new ListFilm();
                    }
                    result.addFilm(f);
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

    public ListFilm getDefaultRecommend() {
        ListFilm result = null;
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "select filmName, IDFilm, imgURL, time,limitAge, duration from film_tbl f, recommandation_tbl r where r.filmID=f.IDFilm";
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String filmName = resultSet.getString("filmName");
                    String filmID = resultSet.getString("IDFilm");
                    String imgURL = resultSet.getString("imgURL");
                    int time = resultSet.getInt("time");
                    String limitAge = resultSet.getString("limitAge");
                    int duration = resultSet.getInt("duration");
                    Film f = new Film(filmID, filmName, imgURL);
                    f.setTime(time);
                    f.setLimitAge(limitAge);
                    f.setDuration(duration);
                    if (result == null) {
                        result = new ListFilm();
                    }
                    result.addFilm(f);

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

    public Film getFilmDetail(String filmID) {
        Film result = null;
        List<String> kinds = new ArrayList<>();
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "select filmID,engName, kindOfFilmID, director, actors, filmName, time, duration, limitAge, imgURL "
                        + "from film_tbl f, filmDetail fd "
                        + "where f.IDFilm=fd.filmID and fd.filmID=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setNString(1, filmID);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String filmName = resultSet.getString("filmName");
                    String imgURL = resultSet.getString("imgURL");
                    int time = resultSet.getInt("time");
                    String limitAge = resultSet.getString("limitAge");
                    int duration = resultSet.getInt("duration");
                    String kindOfFilm = resultSet.getString("kindOfFilmID");
                    String director = resultSet.getString("director");
                    String actors = resultSet.getString("actors");
                    String engName = resultSet.getString("engName");
                    if (result == null) {
                        List<String> directorlist = new ArrayList<>();
                        List<String> actorList = new ArrayList<>();
                        result = new Film();
                        result.setFilmID(filmID);
                        result.setFilmName(filmName);
                        result.setImageURL(imgURL);
                        result.setTime(time);
                        result.setLimitAge(limitAge);
                        result.setDuration(duration);
                        result.setEngName(engName);
                        kinds.add(kindOfFilm);
                        String[] arr = director.split(",");
                        for (String string : arr) {
                            directorlist.add(string);
                        }
                        arr = actors.split(",");
                        for (String string : arr) {
                            actorList.add(string);
                        }
                        result.setDirectors(directorlist);
                        result.setActors(actorList);
                    } else {
                        kinds.add(kindOfFilm);
                    }
                }
                result.setKindOfFilms(kinds);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public void clearDB() {
        System.out.println("Clear DB...");
        try {
            truncateTable("film_tbl");
            truncateTable("filmDetail");
            truncateTable("film_rate_description");

            truncateTable("kinkOfFilm_tbl");
            truncateTable("website_tbl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean clearDefaultRecommandationTable() {

        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "delete from recommandation_tbl";
                preparedStatement = connection.prepareStatement(sql);
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

    public void clearTemptable() {
        try {
            truncateTable("Film_temp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearTempAddtable() {
        try {
            truncateTable("Film_wait_to_add");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearTempUpdatetable() {
        try {
            truncateTable("Film_wait_to_update");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearTempDeltable() {
        try {
            truncateTable("Film_wait_to_del");

        } catch (Exception e) {
            e.printStackTrace();
        }
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
