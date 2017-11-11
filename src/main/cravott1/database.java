package cravott1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class database extends HttpServlet {
    private static final String USERNAME = "johncolter";
    private static final String PASSWORD = "LetMeIn";
    private static final String HOST = "web6.jhuep.com";
    private static final String DB = "mysql";
    private static final int PORT = 3306;
    private static final String DB_NAME = "class";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    }

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
        Connection connection = null;
        ResultSet resultSet = null;
        List<Reservation> reservationList = new ArrayList<Reservation>();

        // get user submitted variables
        if (request.getParameter("startDate") != null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException cnfe) {
                System.out.println("Error loading driver " + cnfe.getMessage());
            }

            try {
                // open database connection
                String dbURL = "jdbc:" + DB + "://" + HOST + ":" + PORT + "/" + DB_NAME;
                connection = DriverManager.getConnection(dbURL, USERNAME, PASSWORD);

                try {
                    String queryString = "SELECT CONCAT(r.First, ' ', r.Last) AS guestName, r.StartDay, " +
                            "CONCAT(g.First, ' ', g.Last) AS guideName, l.location, " +
                            "DATE_FORMAT(r.StartDay, '%m/%d/%Y') AS st " +
                            "FROM reservation r INNER JOIN guides g ON r.guide = g.idguides " +
                            "INNER JOIN locations l ON r.location = l.idlocations " +
                            "WHERE r.StartDay = STR_TO_DATE(?, '%m/%d/%Y') " +
                            "ORDER BY r.StartDay DESC";

                    PreparedStatement pStatement = connection.prepareStatement(queryString);
                    pStatement.setString(1, request.getParameter("startDate"));
                    resultSet = pStatement.executeQuery();

                    // loop over result(s) and add to final ArrayList
                    while (resultSet.next()) {
                        Reservation reservation = new Reservation();

                        reservation.setGuestName(resultSet.getString("guestName"));
                        reservation.setGuideName(resultSet.getString("guideName"));
                        reservation.setLocation(resultSet.getString("location"));
                        reservation.setStartDay(resultSet.getString("startDay"));

                        reservationList.add(reservation);
                    }
                } catch (SQLException sqle) {
                    System.err.println("SQL error: " + sqle.getMessage());
                } finally {
                    // cleanup connection after use
                    try {
                        connection.close();
                    } catch (Exception e) {
                        connection = null;
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        // redirect back to JSP page
        request.getSession().setAttribute("queryResults", reservationList);
        response.sendRedirect("reservations.jsp");
    }

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
        return "Get hike duration information using GET or displays the hike final rate using POST.";
    }
}
