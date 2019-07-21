/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuonghn.DAO;

import cuonghn.jaxb.Film;
import cuonghn.jaxb.ListFilm;
import cuonghn.utils.XMLUtilities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nhatc
 */
public class DataHandler {
    
    ListFilm filmDismounted = null;
    ListFilm ListFilmIncommingChange = null;
    ListFilm ListFilmWillUpdated = null;
    ListFilm listFilmRecommandDefault = null;
    ListFilm oldData = null;
    ListFilm tempData = null;
    ListFilm acceptedData = null;
    String message = "";
    
    public void addAllNew(ListFilm list) {
        FilmDAO filmDao = new FilmDAO();
        TempDataDAO tempDAO = new TempDataDAO();
//        tempData = filmDao.getAllFilmTemp();
        filmDao.clearTempAddtable();
        filmDao.clearTempDeltable();
        filmDao.clearTempUpdatetable();
        tempDAO.masterInsertAddTemp(list);
    }
    
    public void compareOldAndNewData() {
        FilmDAO filmDao = new FilmDAO();
        oldData = filmDao.getAllFilmRateDesciption();
        tempData = filmDao.getAllFilmTemp();
        ListFilmIncommingChange = new ListFilm();
        ListFilmWillUpdated = new ListFilm();
        filmDismounted = new ListFilm();
        TempDataDAO tempDAO = new TempDataDAO();
        try {
            if (oldData == null) {
                ListFilmIncommingChange = tempData;
                addAllNew(tempData);
            } else {
                if (oldData != null && tempData != null) {
                    List<Film> listOldData = oldData.getFilm();
                    List<Film> listTempData = tempData.getFilm();
                    if (listTempData.size() > 0 && listOldData.size() > 0) {
                        for (int i = 0; i < listOldData.size(); i++) {
                            Film oldFilm = listOldData.get(i);
                            boolean filmOutOfDate = true;
                            for (int j = 0; j < listTempData.size(); j++) {
                                Film filmTemp = listTempData.get(j);
                                boolean isnew = true;
                                
                                if (oldFilm.getFilmID().equalsIgnoreCase(filmTemp.getFilmID())
                                        && oldFilm.getWebsiteName().equalsIgnoreCase(filmTemp.getWebsiteName())) {
                                    filmOutOfDate = false;
//                                ListFilmIncommingChange.addFilm(filmTemp);
                                    ListFilmWillUpdated.addFilm(filmTemp);
                                    
                                }
                            }
                            
                            if (filmOutOfDate) {
                                filmDismounted.addFilm(oldFilm);
                            }
                        }
                        message = "Thanh Cong";
                    } else {
                        Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, "khong the so sanh du lieu null");
                    }
                    // get real new film
                    for (int j = 0; j < listTempData.size(); j++) {
                        Film filmTemp = listTempData.get(j);
                        boolean isNew = true;
                        for (int i = 0; i < listOldData.size(); i++) {
                            Film oldFilm = listOldData.get(i);
                            if (oldFilm.getFilmID().equalsIgnoreCase(filmTemp.getFilmID())
                                    && oldFilm.getWebsiteName().equalsIgnoreCase(filmTemp.getWebsiteName())) {
                                isNew = false;
                            }
                        }
                        if (isNew) {
                            ListFilmIncommingChange.addFilm(filmTemp);
                        }
                    }
                } else {
                    message = "khong the so sanh du lieu null, vui long cao";
                }
                filmDao.clearTempAddtable();
                filmDao.clearTempDeltable();
                filmDao.clearTempUpdatetable();
                tempDAO.masterInsertAddTemp(ListFilmIncommingChange);
                tempDAO.masterInsertUpdateTemp(ListFilmWillUpdated);
                tempDAO.masterInsertDel(filmDismounted);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void genarateRecommandationDefault() {
        FilmDAO dao = new FilmDAO();
        ListFilm listFilmInDB = dao.getAllFilmRateDesciption();
        List<Film> lf = null;
        if (listFilmInDB != null) {
            lf = listFilmInDB.getFilm();
            Collections.sort(lf);
        }
        dao.clearDefaultRecommandationTable();
        int limit = 0;
        if (lf.size() >= 10) {
            limit = 10;
        } else {
            limit = lf.size();
        }
        for (int i = 0; i < limit; i++) {
            String FilmID = lf.get(i).getFilmID();
            dao.insertNewDefaultRecommandation(FilmID);
        }
    }
    
    public void handleAddIncommingData() {
        try {
            FilmDAO dao = new FilmDAO();
            ListFilm incomming = dao.getAllFilmIncomming();
            if (incomming != null) {
//                for (int i = 0; i < incomming.getFilm().size(); i++) {
//                    Film f = incomming.getFilm().get(i);
//                    dao.insertNewFilm(f);
//                    dao.insertNewFilmRateDescription(f);
//                }
                dao.masterInsertDB(incomming);
                dao.clearTempAddtable();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public ListFilm filterByKindOfFilm(List<Film> list, String kind) {
        List<Film> result = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Film film = list.get(i);
                List<String> kinds = film.getKindOfFilms();
                boolean inClude = false;
                for (int j = 0; j < kinds.size(); j++) {
                    String kindOfFilm = kinds.get(j);
                    if (kindOfFilm.equals(kind)) {
                        inClude = true;
                    }
                    
                }
                if (inClude) {
                    result.add(film);
                }
            }
            
        }
        if (result.size() <= 0) {
            return null;
        } else {
            return new ListFilm(result);
        }
        
    }
    
    public void handleDelData() {
        try {
            FilmDAO dao = new FilmDAO();
            EvaluateDAO edao = new EvaluateDAO();
            ListFilm dels = dao.getAllFilmWillDel();
            if (dels != null) {
                for (int i = 0; i < dels.getFilm().size(); i++) {
                    Film f = dels.getFilm().get(i);
                    edao.deleteFilmRateDescription(f);
                    // del data
                }
                dao.clearTempDeltable();
            }
            
        } catch (Exception e) {
        }
        
    }
    
    public void handleUpdateData() {
        try {
            EvaluateDAO edao = new EvaluateDAO();
            FilmDAO dao = new FilmDAO();
            ListFilm updates = dao.getAllFilmWillUpdate();
            if (updates != null) {
                for (int i = 0; i < updates.getFilm().size(); i++) {
                    
                    Film f = updates.getFilm().get(i);
                    edao.updateFilmRateDescription(f);
                }
                dao.clearTempUpdatetable();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public ListFilm getFilmDismounted() {
        return filmDismounted;
    }
    
    public ListFilm getListFilmIncommingChange() {
        return ListFilmIncommingChange;
    }
    
    public ListFilm getListFilmWillUpdated() {
        return ListFilmWillUpdated;
    }
    
    public ListFilm getOldData() {
        return oldData;
    }
    
    public ListFilm getTempData() {
        return tempData;
    }
    
    public ListFilm getAcceptedData() {
        return acceptedData;
    }
    
    public String getMessage() {
        return message;
    }
    
}
