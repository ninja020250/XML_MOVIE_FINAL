/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuonghn.servlet;

import cuonghn.DAO.QuizDAO;
import cuonghn.quiz.Answer;
import cuonghn.quiz.Quiz;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nhatc
 */
@WebServlet(name = "QuizServlet", urlPatterns = {"/QuizServlet"})
public class QuizServlet extends HttpServlet {

    private String question = "";
    private String a1 = "";
    private String a2 = "";
    private String a3 = "";
    private String a4 = "";
    private String k1 = "";
    private String k2 = "";
    private String k3 = "";
    private String k4 = "";

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
            String quizBtn = request.getParameter("quizBtn");
            RequestDispatcher rd = null;
            QuizDAO dao = null;
            List<Quiz> lq = null;
            switch (quizBtn) {
                case "addQuiz":
                    addQuiz(request);
                    response.sendRedirect("quiz.jsp");
                    break;
                case "viewQuiz":
                    dao = new QuizDAO();
                    lq = dao.getAllQuiz();
                    if (lq != null && lq.size() > 0) {
                        request.setAttribute("LISTQUIZS", lq);
                    }
                    rd = request.getRequestDispatcher("quiz.jsp");
                    rd.forward(request, response);

                    break;
                case "delQuiz":
                    dao = new QuizDAO();
                    String quizID =  request.getParameter("txtQuizID");
                    dao.deleteAnswer(quizID);
                    dao.deleteQuiz(quizID);
                    lq = dao.getAllQuiz();
                    if (lq != null && lq.size() > 0) {
                        request.setAttribute("LISTQUIZS", lq);
                    }
                    rd = request.getRequestDispatcher("quiz.jsp");
                    rd.forward(request, response);
                default:
                    response.sendRedirect("quiz.jsp");
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            RequestDispatcher rd =  request.getRequestDispatcher("quiz.jsp");
//            rd.forward(request, response);

            out.close();
        }
    }

    private boolean validateData() {
        boolean isValid = true;
        if ("".equals(a1) || "".equals(k1)) {
            isValid = false;

        }
        if ("".equals(a2) || "".equals(k2)) {

            isValid = false;

        }
        if ("".equals(a3) || "".equals(k3)) {

            isValid = false;

        }
        if ("".equals(a4) || "".equals(k4)) {
            isValid = false;

        }
        return isValid;
    }

    private boolean addQuiz(HttpServletRequest request) {
        boolean valid = false;
        QuizDAO dao = new QuizDAO();
        question = request.getParameter("txtQuestion");
        a1 = request.getParameter("txtAnswer1");
        a2 = request.getParameter("txtAnswer2");
        a3 = request.getParameter("txtAnswer3");
        a4 = request.getParameter("txtAnswer4");
        k1 = request.getParameter("keyword1");
        k2 = request.getParameter("keyword2");
        k3 = request.getParameter("keyword3");
        k4 = request.getParameter("keyword4");
        valid = validateData();
        if (valid) {
            Quiz q = new Quiz();
            q.setQuestion(question);
            Answer an1 = new Answer(a1, k1);
            Answer an2 = new Answer(a2, k2);
            Answer an3 = new Answer(a3, k3);
            Answer an4 = new Answer(a4, k4);
            q.getAnswer().add(an1);
            q.getAnswer().add(an2);
            q.getAnswer().add(an3);
            q.getAnswer().add(an4);
            dao.inserQuiz(q);
            return true;
        }
        return false;
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
