package ravotta.carrie;

import bookingrate.BookingDay;
import bookingrate.Rates;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;

public class ravotta_hw11 extends HttpServlet {
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
        PrintWriter out = response.getWriter();
        String hikeName = null;
        String partyNumber = null;
        String startDate = null;
        String duration = null;

        // message to insert into body tag
        String message;

        // get user submitted variables
        if (request.getParameter("hikeName") != null) {
            hikeName = request.getParameter("hikeName");
        }

        if (request.getParameter("startDate") != null) {
            startDate = request.getParameter("startDate");
        }

        if (request.getParameter("duration") != null) {
            duration = request.getParameter("duration");
        }

        if (request.getParameter("partyNumber") != null) {
            partyNumber = request.getParameter("partyNumber");
        }

        if (hikeName == null || startDate == null || duration == null) {
            message = "<p>Error! Missing fields</p>";
        } else {
            // get total cost
            String result = getCost(hikeName, duration, startDate);

            // split results into [VALUE:MESSAGE]
            String[] parts = result.split(":");

            // if VALUE in return is -0.01 display error
            if (Double.parseDouble(parts[0]) != -0.01) {
                // print out final total
                message = "<p><strong>Total Cost:</strong> " + String.format("$%,.2f", Double.parseDouble(parts[0]));
            } else {
                // display STRING result
                message = "<p>Error! " + parts[1] + "</p>";
            }
        }

        try {
            hikeName = request.getParameter("hikeName");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Beartooth Hiking Company</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Beartooth Hiking Company Final Hike Rate</h1>");
            out.println("<p><strong>Number in Party:</strong> " + partyNumber + "</p>");
            out.println("<p><strong>Hike Name:</strong> " + hikeName + "</p>");
            out.println("<p><strong>Start Date:</strong> " + startDate + "</p>");
            out.println("<p><strong>Duration:</strong> " + duration + "</p>");
            out.println("<p>&nbsp;</p>");
            out.println(message);
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
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
        String hikeName = request.getParameter("hikeName");

        // ensure the hike name is valid
        if (hikeName != null) {
            Rates rates = new Rates(Rates.HIKE.valueOf(hikeName));
            List<String> durations = new ArrayList<String>();

            // loop over selected hike's duration day options
            for (int i : rates.getDurations()) {
                durations.add(String.valueOf(i));
            }

            // write durations in list form
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(String.join(",", durations));
        }
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

    /** Calculate total cost of hike
     */
    private String getCost(String hikeName, String duration, String startDate) {
        // get instance to use for parsing the start date
        Calendar cal = Calendar.getInstance();

        Rates rates = new Rates(Rates.HIKE.valueOf(hikeName));

        // get what duration is selected
        Integer selectedDuration = Integer.parseInt(duration);

        // try to parse the user's start date entered
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            cal.setTime(sdf.parse(startDate));
        }
        // invalid date format
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "-0.01:Invalid date format. Error of: " + ex.getMessage();
        }

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        // set the book day start; add one to the month to compensate for the BookingDay offset
        BookingDay startDay = new BookingDay(year, month, day);

        if (!startDay.isValidDate()) {
            return "-0.01:Invalid time range. " + rates.getDetails();
        } else {
            // add selected duration days to the start date
            rates.setBeginDate(startDay);

            // validate the range is valid
            boolean success = rates.setDuration(selectedDuration);
            if (rates.isValidDates() && success) {
                // print out final total
                return rates.getCost() + ":quoted rate";
            } else {
                return "-0.01:Invalid time range. " + rates.getDetails();
            }
        }
    }
}
