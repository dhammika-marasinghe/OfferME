package com.illusionbox.offerme.control;

import com.illusionbox.offerme.manager.DB;
import com.illusionbox.offerme.manager.OfferMeHibernateUtil;
import com.illusionbox.offerme.model.Offer;
import java.io.IOException;
import java.io.PrintWriter;
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
public class ActivateOffer extends HttpServlet {

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
            if ((request.getParameter("id") != null) && (request.getParameter("active") != null)
                    && (request.getSession().getAttribute("User") != null)) {
                /*
                 Session hbSession = OfferMeHibernateUtil.getSessionFactory().openSession();
                 Transaction hbTransaction = hbSession.beginTransaction();

                 Offer o = (Offer) hbSession.load(Offer.class, Integer.parseInt(request.getParameter("id")));
                 out.println(o.getTitle());
                 if (request.getParameter("active").equals("YES")) {
                 o.setValid(true);
                 } else if (request.getParameter("active").equals("NO")) {
                 o.setValid(false);
                 }
                 out.println(o.getValid());
                 hbSession.update(o);
                 hbTransaction.commit();
                 */
                int valid = 0;
                if (request.getParameter("active").equals("YES")) {
                    valid = 1;
                } else if (request.getParameter("active").equals("NO")) {
                    valid = 0;
                }
                DB.update("update offer set valid=" + valid + " where idoffer=" + request.getParameter("id") + "");
            }
            response.sendRedirect("dashboard.jsp");
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
