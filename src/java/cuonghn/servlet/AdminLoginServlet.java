/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuonghn.servlet;

import cuonghn.DAO.UserDAO;
import cuonghn.DTO.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nhatc
 */
public class AdminLoginServlet extends HttpServlet {

    private final String INVALID = "invalid.html";
    private final String ADMIN_PAGE = "adminPage.jsp";

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
        String nextPage = INVALID;
        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            HttpSession session = request.getSession();
            UserDAO userdao = new UserDAO();
//            UserDTO userCookie = cookieAutoLogin(request);
            UserDTO result = userdao.checkLogin(new UserDTO(username, password));
            if (result != null && result.getRoleName().equals("admin")) {

                session.setAttribute("ADMIN", result);
                nextPage = ADMIN_PAGE;
            }
//            RequestDispatcher rd = request.getRequestDispatcher(nextPage);
//            rd.forward(request, response);
            response.sendRedirect(nextPage);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    private UserDTO cookieAutoLogin(HttpServletRequest request) {
        UserDTO u = null;
        String username = "";
        String password = "";
        Cookie ck[] = request.getCookies();
        for (int i = 0; i < ck.length; i++) {
            if (ck[i].getName().equals("username")) {
                username = ck[i].getValue();
            }
            if (ck[i].getName().equals("password")) {
                password = ck[i].getValue();
            }
            if (!username.equals("") && !password.equals("")) {
                u = new UserDTO(username, password);
            }
        }
        return u;
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
