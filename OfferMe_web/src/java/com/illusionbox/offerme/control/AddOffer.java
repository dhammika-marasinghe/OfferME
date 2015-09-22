package com.illusionbox.offerme.control;

import com.illusionbox.offerme.manager.DB;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 3rd EYE
 */
public class AddOffer extends HttpServlet {

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
            if (request.getSession().getAttribute("User") != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                /*
                 Session hbSession = OfferMeHibernateUtil.getSessionFactory().openSession();
                 Transaction hbTransaction = hbSession.beginTransaction();

                 int idres = Integer.parseInt(request.getParameter("idres"));
                 Restaurant r = (Restaurant) hbSession.load(Restaurant.class, idres);

                 Offer o = new Offer(
                 r,
                 request.getParameter("title"),
                 request.getParameter("desc"),
                 sdf.parse(request.getParameter("startdate")),
                 sdf.parse(request.getParameter("enddate")),
                 "No",
                 true,
                 request.getParameter("image"),
                 request.getParameter("type"),
                 request.getParameter("offercode"));

                 hbSession.save(o);
                 hbTransaction.commit();
                 */
                DB.update("INSERT INTO `offer` (`title`, `description`, `start_date`, `end_date`, `repeat`, `valid`, `image_url`, `type`, `offer_code`, `restaurant_idrestaurant`) VALUES\n"
                        + "('" + request.getParameter("title") + "', "
                        + "'" + request.getParameter("desc") + "', "
                        + "'" + request.getParameter("startdate") + "', "
                        + "'" + request.getParameter("enddate") + "', "
                        + "'YES', "
                        + "1, "
                        + "'" + request.getParameter("image") + "', "
                        + "'" + request.getParameter("type") + "', "
                        + "'" + request.getParameter("offercode") + "', "
                        + "'" + request.getParameter("idres") + "')");

                response.sendRedirect("dashboard.jsp?msg=Offer successfully added.");
            } else {
                response.sendRedirect("index.jsp");
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
