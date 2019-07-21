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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nhatc
 */
@WebServlet(name = "UserLoginServlet", urlPatterns = {"/UserLoginServlet"})
public class UserLoginServlet extends HttpServlet {

    private String nextPage = "userLogin.jsp";

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
            UserDTO userCookie = cookieAutoLogin(request);
            String btnUser = request.getParameter("btnUser");
            UserDAO dao = new UserDAO();
            if (btnUser != null) {
                switch (btnUser) {
                    case "navigateLogin":
                        nextPage = "userLogin.jsp";
                        response.sendRedirect(nextPage);
                        break;
                    case "navigateSignUp":
                        nextPage = "userSignUp.jsp";
                        response.sendRedirect(nextPage);
                        break;
                    case "submitSignUp":
                        boolean signUpResult = signUp(request);
                        if (signUpResult) {
                            nextPage = "userLogin.jsp";
                        } else {
                            request.setAttribute("SIGNUP_ERROR", "username existed");
                            nextPage = "userSignUp.jsp";
                        }
                        RequestDispatcher rdSignUp = request.getRequestDispatcher(nextPage);
                        rdSignUp.forward(request, response);
                        break;
                    case "userLogin":
                        String username = request.getParameter("txtUsername");
                        String password = request.getParameter("txtPassword");
                        UserDTO result = dao.checkLogin(new UserDTO(username, password));
                        if (result != null && result.getRoleName().equals("user")) {
                            HttpSession session = request.getSession();
                            session.setAttribute("USER", result);
                            nextPage = "homePage.jsp";
                            Cookie usernameCookie = new Cookie("username", username);
                            Cookie passwordCookie = new Cookie("password", password);
                            response.addCookie(usernameCookie);
                            response.addCookie(passwordCookie);
                            response.sendRedirect(nextPage);
                        } else {
                            nextPage = "userLogin.jsp";
                            request.setAttribute("MESSAGE", "Invalid username or password! try again");
                            RequestDispatcher rd = request.getRequestDispatcher(nextPage);
                            rd.forward(request, response);

                        }
                        break;
                }
            } else {
                if (userCookie != null) {
                    UserDTO user = dao.checkLogin(userCookie);
                    if (user != null && user.getRoleName().equals("user")) {
                        HttpSession session = request.getSession();
                        session.setAttribute("USER", user);
                    }
                }
                nextPage = "homePage.jsp";
                response.sendRedirect(nextPage);
            }

        } catch (Exception e) {
        } finally {
            out.close();
        }
    }

    private boolean signUp(HttpServletRequest request) {
        UserDTO u = null;
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String name = request.getParameter("txtName");
        u = new UserDTO(username, password);
        u.setName(name);
        UserDAO dao = new UserDAO();
        boolean signUp = dao.signUp(u);
        return signUp;
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
