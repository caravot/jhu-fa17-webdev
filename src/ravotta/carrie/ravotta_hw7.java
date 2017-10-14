package ravotta.carrie;

import bookingrate.Rates;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@WebServlet(name = "MyFormServlet", urlPatterns = {"/MyFormServlet"})
public class ravotta_hw7 extends HttpServlet {
    // Create class level variables
    List<Rates.HIKE> enumValues = new ArrayList<Rates.HIKE>(EnumSet.allOf(Rates.HIKE.class));

    // web server socket information
    private final String SERVER_HOSTNAME = "web6.jhuep.com";
    private final int SERVER_PORT = 20025;

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
        String startDate = null;
        String duration = null;

        // message to insert into body tag
        String message = null;

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
            out.println("<p><strong>Hike Name:</strong> " + hikeName + "</p>");
            out.println("<p><strong>Start Date:</strong> " + startDate + "</p>");
            out.println("<p><strong>Duration:</strong> " + duration + "</p>");
            out.println("<p>&nbsp;</p>");
            out.println(message);
            out.println("<p>&nbsp;</p>");
            out.println("<p><a href=\"/\">Go back to resubmit</a></p>");
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
        String result = null;

        // get instance to use for parsing the start date
        Calendar cal = Calendar.getInstance();

        // get what hike is selected
        int hikeId = Rates.HIKE.valueOf(hikeName).ordinal();

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
        } finally {
            try {
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH) + 1;
                int day = cal.get(Calendar.DAY_OF_MONTH);

                result = openSocket(hikeId, year, month, day, selectedDuration);
                System.out.println("Result: " + result);
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * Open web server socket to get booking rates from
     * @param hike
     * @param year
     * @param month
     * @param day
     * @param duration
     * @return String in format VALUE:MESSAGE
     *          VALUE = -0.01 indicates an error
     *          VALUE != -0.01 for quoted rate
     *          MESSAGE = Error message or "Quoted Rate"
     * @throws IOException
     */
    private String openSocket(int hike, int year, int month, int day, int duration) throws IOException {
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String echo = "";
        String result = "";

        // create final string to pass to socket
        String data = hike + ":" + year + ":" + month + ":" + day + ":" + duration;

        // open socket connection to web server
        try {
            echoSocket = new Socket(SERVER_HOSTNAME, SERVER_PORT);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: "  + SERVER_HOSTNAME);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + SERVER_HOSTNAME);
            System.exit(1);
        }

        // ensure data string is populated
        while (data != null) {
            out.println(data);
            echo = in.readLine();
            if (echo == null) {
                break;
            } else {
                result += echo;
            }
        }

        // close connections; readers; sockets; buffers
        out.close();
        in.close();
        echoSocket.close();

        return result;
    }
}
