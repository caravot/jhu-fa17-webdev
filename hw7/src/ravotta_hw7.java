import bookingrate.Rates;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MyFormServlet", urlPatterns = {"/MyFormServlet"})
public class ravotta_hw7 extends HttpServlet {
    // Create class level variables
    private final JComboBox<Rates.HIKE> hikeComboBox = new JComboBox<Rates.HIKE>(Rates.HIKE.values());
    private final JComboBox<Integer> hikeRatesComboBox = new JComboBox<Integer>();
    private final JLabel hikeName = new JLabel("Hike Name: ");
    private final JLabel hikeDuration = new JLabel("Hike Duration (days): ");
    private final JLabel startDate = new JLabel("Start Date (MM/DD/YYYY): ");
    private final JLabel totalCost = new JLabel("Total Cost: ");
    private final JButton submitBtn = new JButton("Submit");
    private final JLabel totalCostField = new JLabel("");
    private MaskFormatter mask;
    private JFormattedTextField startDateValue;
    private final GridBagConstraints gbc = new GridBagConstraints();

    // web server socket information
    private final String SERVER_HOSTNAME = "web6.jhuep.com";
    private final int SERVER_PORT = 20025;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //new ravotta_hw7();

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
        PrintWriter out = response.getWriter();
        String firstName = null;
        String lastName = null;
        try {
            firstName = request.getParameter("firstName");
            if (firstName == null) {
                firstName = "<none entered>";
            }
            lastName = request.getParameter("lastName");
            if (lastName == null) {
                lastName = "<none entered>";
            }
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SimpleForm</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SimpleForm at " +
                    request.getContextPath() + "</h1>");
            out.println("Welcome " + firstName + " " + lastName);
            out.println("</body>");
            out.println("</html>");

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
