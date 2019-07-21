/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuonghn.listener;

import cuonghn.DAO.FilmDAO;
import cuonghn.DAO.KindOfFilmDAO;
import cuonghn.DAO.QuizDAO;
import cuonghn.crawler.PhePhimCrawler;
import cuonghn.jaxb.Category;
import cuonghn.jaxb.ListFilm;
import cuonghn.quiz.ListQuiz;
import cuonghn.quiz.Quiz;
import cuonghn.utils.Constant;
import cuonghn.utils.XMLUtilities;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Web application lifecycle listener.
 *
 * @author nhatc
 */
public class myRequestListener implements ServletRequestListener {

    private ListFilm listFilm = null;
    private List<String> category = null;

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        String listFilmString = "";
        String listRecommandation = "";
        ServletRequest request = sre.getServletRequest();
        ServletContext context = sre.getServletContext();
        HttpServletRequest servletRequest = (HttpServletRequest) sre.getServletRequest();
        HttpSession session = servletRequest.getSession();
        FilmDAO Filmdao = new FilmDAO();
        KindOfFilmDAO kindDao = new KindOfFilmDAO();
        // end declare http 

        String realPath = servletRequest.getServletContext().getRealPath("/");
        // handle recommandation
        listRecommandation = (String) session.getAttribute(Constant.SESSIONID_FILM_RECOMMANDATION);
        if (listRecommandation == null || listRecommandation.equals("")) {
            ListFilm listFilmRecommandation = Filmdao.getDefaultRecommend();
            if (listFilmRecommandation != null) {
                listRecommandation = XMLUtilities.marsalData(listFilmRecommandation);
                session.setAttribute(Constant.SESSIONID_FILM_RECOMMANDATION, listRecommandation);
            }
        }

        // handle loading data for searching
        listFilmString = (String) session.getAttribute(Constant.SESSIONID_DATA);
        if (listFilmString == null || listFilmString.equals("")) {
            listFilm = Filmdao.getAllFilm();
            if (listFilm != null) {
                listFilmString = XMLUtilities.marsalData(listFilm);
                session.setAttribute(Constant.SESSIONID_DATA, listFilmString);

            }
        } else {
            try {
                listFilm = XMLUtilities.unmarshallData(listFilmString, ListFilm.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //handle Category for filter
        if (listFilm != null) {
            category = kindDao.getAllKindOfFilm();
            if (category != null) {
                Category listCategory = new Category(category);
                String categoryXML = XMLUtilities.marsalData(listCategory);
                session.setAttribute(Constant.SESSIONID_CATEGORY, categoryXML);
            }
        }
        //end  handle Category for filter

//        //handle Quiz for recommandation 
        String listQuizXML = (String) session.getAttribute(Constant.SESSIONID_QUIZ);
//        if (listQuizXML == null) {
        QuizDAO quizDao = new QuizDAO(realPath);
        List<Quiz> allLQ = quizDao.getAllQuiz();
        List<Quiz> lq = quizDao.getRandom5Quiz(allLQ);
        if (lq != null) {
            ListQuiz lqo = new ListQuiz(lq);
            listQuizXML = XMLUtilities.marsalData(lqo);
            session.setAttribute(Constant.SESSIONID_QUIZ, listQuizXML);
        }
//        }
        session.setMaxInactiveInterval(10 * 60);
//        //end handle Quiz for recommandation 
    }

}
