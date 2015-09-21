package com.illusionbox.offerme.control;

import com.illusionbox.offerme.model.Restaurant;
import com.illusionbox.offerme.manager.OfferMeHibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
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
public class FetchRestaurant extends HttpServlet {

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
            if ((request.getParameter("latitude") != null)
                    && (request.getParameter("longitude") != null)
                    && (request.getParameter("radius") != null)) {

                //Position, decimal degrees
                double latitude = Double.parseDouble(request.getParameter("latitude"));
                double longitude = Double.parseDouble(request.getParameter("longitude"));
                //offsets in meters
                double radius = Double.parseDouble(request.getParameter("radius"));

                //Earth’s radius, sphere
                int R = 6378137;

                //Coordinate offsets in radians -> OffsetPosition, decimal degrees
                double dLat = (radius / R) * 180 / Math.PI;
                double dLon = (radius / (R * Math.cos(Math.PI * latitude / 180))) * 180 / Math.PI;

                String hql = "from Restaurant "
                        + "where latitude between " + (latitude - dLat) + " and " + (latitude + dLat) + " and "
                        + "longitude between " + (longitude - dLon) + " and " + (longitude + dLon) + "";

                Session hbSession = OfferMeHibernateUtil.getSessionFactory().openSession();
                Query q = hbSession.createQuery(hql);
                
                List<Restaurant> restaurants = q.list();

                out.println(Restaurant.csvHeader);
                for (Restaurant restaurant : restaurants) {
                    out.println(restaurant.toCSVLine());
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        } finally {
            out.close();
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
