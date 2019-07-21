/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuonghn.servlet;

import cuonghn.DAO.DataHandler;
import cuonghn.DAO.FilmDAO;
import cuonghn.DAO.QuizDAO;
import cuonghn.DTO.ReportDTO;
import cuonghn.crawler.CrawlerMaster;
import cuonghn.crawler.PhePhimCrawler;
import cuonghn.crawler.KhoaiTV;
import cuonghn.jaxb.ListFilm;
import cuonghn.utils.Constant;
import cuonghn.utils.XMLUtilities;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nhatc
 */
@WebServlet(name = "CrawlServlet", urlPatterns = {"/CrawlServlet"})
public class CrawlServlet extends HttpServlet {

    private int imcommingChange = 0;
    private int willUpdated = 0;
    private int dismounted = 0;
    private String messageAlert = "";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String realPath = request.getServletContext().getRealPath("/");
            String button = request.getParameter("adminBtn");
//            HttpSession session = request.getSession();
            DataHandler dth = new DataHandler();
            PhePhimCrawler phephim = null;
            KhoaiTV khoai = null;
            ReportDTO report = null;
            switch (button) {
//                case "phim33Crawl":
//                    phephim = new PhePhimCrawler(realPath);
//                    phephim.run();
//                    report = new ReportDTO();
//                    report.setTotalCrawled(phephim.getTotalCrawled());
//                    report.setTotalValid(phephim.getTotalValidData());
//                    report.setTotalInvalid(phephim.getTotalInvalidData());
//                    report.setTotalInsetTemp(phephim.getTotalInserTemp());
//                    request.setAttribute("REPORT", report);
//
//                    break;
//                case "phim33validate":
//                    phephim = new PhePhimCrawler(realPath);
//                    phephim.testValidateAndInsertDB();
//                    report = new ReportDTO();
//                    report.setTotalCrawled(phephim.getTotalCrawled());
//                    report.setTotalValid(phephim.getTotalValidData());
//                    report.setTotalInvalid(phephim.getTotalInvalidData());
//                    report.setTotalInsetTemp(phephim.getTotalInserTemp());
//                    request.setAttribute("REPORT", report);
//                    break;
//                case "khoaitvCrawl":
//                    khoai = new KhoaiTV(realPath);
//                    khoai.run();
//                    report = new ReportDTO();
//                    report.setTotalCrawled(khoai.getTotalCrawled());
//                    report.setTotalValid(khoai.getTotalValidData());
//                    report.setTotalInvalid(khoai.getTotalInvalidData());
//                    request.setAttribute("REPORT", report);
//                    break;
//                case "khoaitvValidate":
//                    khoai.testValidateAndInsertDB();
//                    report = new ReportDTO();
//                    khoai = new KhoaiTV(realPath);
//                    khoai.testValidateAndInsertDB();
//                    report = new ReportDTO();
//                    report.setTotalCrawled(khoai.getTotalCrawled());
//                    report.setTotalValid(khoai.getTotalValidData());
//                    report.setTotalInvalid(khoai.getTotalInvalidData());
//                    request.setAttribute("REPORT", report);
//                    break;
                case "clearDB":
                    FilmDAO filmDao = new FilmDAO();
                    filmDao.clearDB();
                    break;

                case "masterCrawl":
                    CrawlerMaster master = new CrawlerMaster(realPath);
                    report = new ReportDTO();
                    master.run();
                    report.setTotalCrawled(master.getTotalCrawled());
                    report.setTotalValid(master.getTotalValidData());
                    report.setTotalInvalid(master.getTotalInvalidData());
                    report.setTotalInsetTemp(master.getTotalInserTemp());
                    request.setAttribute("REPORT", report);
                    break;
                case "handlingNewData":
                    HandlingIncommingData();

                    request.setAttribute("INCOMMING", this.imcommingChange);

                    request.setAttribute("WILLUPDATE", this.willUpdated);

                    request.setAttribute("DISMOUNTED", this.dismounted);

                    request.setAttribute("MESSAGE", messageAlert);
                    break;

                case "adminHandlingResultData":
                    String[] names = request.getParameterValues("handleResultData");
                    dth = new DataHandler();
                    for (int i = 0; i < names.length; i++) {
                        String action = names[i];
                        if (action.equals("update")) {
                            dth.handleUpdateData();
                        }
                        if (action.equals("del")) {
                            dth.handleDelData();
                        }
                        if (action.equals("add")) {
                            //add
                            dth.handleAddIncommingData();
                        }
                    }

                    break;
                case "recommand":
                    dth = new DataHandler();
                    dth.genarateRecommandationDefault();
                    break;
//                case "asynQuestion":
//                    QuizDAO qDao = new QuizDAO(realPath);
//                    qDao.AsynQuestion();
            }
            HttpSession session = request.getSession();
            if (session.getAttribute(Constant.SESSIONID_FILM_RECOMMANDATION) != null) {
                session.removeAttribute(Constant.SESSIONID_FILM_RECOMMANDATION);
            }
            if (session.getAttribute(Constant.SESSIONID_DATA) != null) {
                session.removeAttribute(Constant.SESSIONID_DATA);
            }
            if (session.getAttribute(Constant.SESSIONID_CATEGORY) != null) {
                session.removeAttribute(Constant.SESSIONID_CATEGORY);
            }
            if (session.getAttribute(Constant.SESSIONID_QUIZ) != null) {
                session.removeAttribute(Constant.SESSIONID_QUIZ);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(Constant.ADMIN_PAGE);
            rd.forward(request, response);
        }
    }

    private void HandlingIncommingData() {
        DataHandler DThandler = new DataHandler();
        DThandler.compareOldAndNewData();
        ListFilm imcommingChange2 = DThandler.getListFilmIncommingChange();
        if (imcommingChange2 != null) {
            imcommingChange = imcommingChange2.getFilm().size();
        }

        ListFilm willUpdated2 = DThandler.getListFilmWillUpdated();
        if (willUpdated2 != null) {
            willUpdated = willUpdated2.getFilm().size();
        }
        ListFilm dismounted2 = DThandler.getFilmDismounted();
        if (dismounted2 != null) {
            dismounted = dismounted2.getFilm().size();
        }
        messageAlert = DThandler.getMessage();
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
