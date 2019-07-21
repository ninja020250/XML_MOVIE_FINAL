/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuonghn.servlet;

import cuonghn.DAO.EvaluateDAO;
import cuonghn.DAO.FilmDAO;
import cuonghn.DAO.UserDAO;
import cuonghn.DTO.UserDTO;
import cuonghn.jaxb.Film;
import cuonghn.jaxb.FilmEvalute;
import cuonghn.jaxb.ListFilm;
import cuonghn.jaxb.ListFilmEvalute;
import cuonghn.utils.Constant;
import cuonghn.utils.XMLUtilities;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "FilmDetailServlet", urlPatterns = {"/FilmDetailServlet"})
public class FilmDetailServlet extends HttpServlet {

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
        String nextPage = Constant.ERROR_PAGE;
        try {
            HttpSession session = request.getSession();
            String FilmID = request.getParameter("filmID");
            FilmDAO filmDao = new FilmDAO();
            EvaluateDAO eDAO = new EvaluateDAO();
            if (FilmID != null) {
                Film film = filmDao.getFilmDetail(FilmID);
                ListFilmEvalute lfe = eDAO.getFilmEvalute(FilmID);
                lfe.setFilm(film);
                if (lfe != null) {
                    UserDTO dto = (UserDTO) session.getAttribute("USER");
                    if (dto != null) {
                        dto.setHistory(film.getKindOfFilms());
                        UserDAO userDao = new UserDAO();
                        userDao.userUpdateSearchHistory(dto);
                    }

                    String FilmXML = XMLUtilities.marsalData(film);
                    String lfeXML = XMLUtilities.marsalData(lfe);
                    request.setAttribute(Constant.SESSIONID_FILM_DETAIL, lfeXML);
//                    request.setAttribute("LFE", lfeXML);
                    nextPage = Constant.FILM_DETAIL_PAGE;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(nextPage);
            rd.forward(request, response);
        }
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
