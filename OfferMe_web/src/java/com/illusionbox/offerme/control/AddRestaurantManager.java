package com.illusionbox.offerme.control;

import com.illusionbox.offerme.manager.OfferMeHibernateUtil;
import com.illusionbox.offerme.model.Administrator;
import com.illusionbox.offerme.model.RestaurantManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author 3rd EYE
 */
public class AddRestaurantManager extends HttpServlet {

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
            Session hbSession = OfferMeHibernateUtil.getSessionFactory().openSession();
            Transaction hbTransaction = hbSession.beginTransaction();

            Administrator a = (Administrator) hbSession.load(Administrator.class, "admin@offerme.lk");
            
            RestaurantManager rm = new RestaurantManager(
                    request.getParameter("email"),
                    a,
                    request.getParameter("password"),
                    request.getParameter("name"),
                    request.getParameter("address"),
                    request.getParameter("telephone"),
                    "New",
                    request.getParameter("message"),
                    null);

            hbSession.save(rm);
            hbTransaction.commit();
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
