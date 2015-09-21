package com.illusionbox.offerme.control;

import com.illusionbox.offerme.model.RestaurantManager;
import com.illusionbox.offerme.manager.OfferMeHibernateUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author 3rd EYE
 */
public class LoginCheck extends HttpServlet {

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
        try {
            if ((request.getParameter("username") != null) && (request.getParameter("password") != null)) {
                /*
                 ResultSet rs = DB.search("select * from restaurant_manager "
                 + "where email='" + request.getParameter("username") + "' and "
                 + "password='" + request.getParameter("password") + "'");

                 if (rs.next()) {
                 request.getSession().setAttribute("User", request.getParameter("username"));
                 } else {
                 response.sendRedirect("index.jsp?msg=Login Error.\n Please check your username & password.");
                 }*/

                String hql = "from RestaurantManager "
                        + "where email='" + request.getParameter("username") + "' and "
                        + "password='" + request.getParameter("password") + "'";

                Session hbSession = OfferMeHibernateUtil.getSessionFactory().openSession();
                Query q = hbSession.createQuery(hql);

                List<RestaurantManager> managers = q.list();
                if (managers.isEmpty()) {
                    response.sendRedirect("index.jsp?msg=Login Error.\n Please check your username & password.");
                } else {
                    request.getSession().setAttribute("User", request.getParameter("username"));
                    response.sendRedirect("dashboard.jsp");
                }

            } else {
                response.sendRedirect("index.jsp?msg=Login Error.\n Please provide your username & password.");
            }
        } catch (Exception e) {
            throw new ServletException(e);
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
