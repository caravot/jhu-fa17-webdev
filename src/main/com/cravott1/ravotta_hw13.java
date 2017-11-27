package com.cravott1;

import com.bookingrate.BookingDay;
import com.bookingrate.Rates;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Path("/rsvp")
public class ravotta_hw13 extends HttpServlet {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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

    @GET
    @Path("/record")
    @Produces(MediaType.APPLICATION_JSON)
    public HikeReservation getReservation() {
        HikeReservation hr = new HikeReservation();
        hr.setPartyNumber("5");
        hr.setDuration("2");
        hr.setStartDate("07/05/2017");
        hr.setHikeName("GARDINER");

        return hr;
    }

    /** Calculate total cost of hike
     */
    @POST
    @Path("/cost")
    @Produces("text/html;charset=UTF-8")
    public Response getCost(@FormParam("hikeName") String hikeName,
                          @FormParam("duration") String duration,
                          @FormParam("startDate") String startDate,
                          @FormParam("partyNumber") String partyNumber) {
        // message to return to user
        String msg;

        // default status return of server error
        Integer statusCode;

        HikeReservation hikeReservation = new HikeReservation();

        // get instance to use for parsing the start date
        Calendar cal = Calendar.getInstance();

        // get user submitted variables
        if (hikeName != null) {
            hikeReservation.setHikeName(hikeName);
        }

        if (startDate != null) {
            hikeReservation.setStartDate(startDate);
        }

        if (duration != null) {
            hikeReservation.setDuration(duration);
        }

        if (partyNumber != null) {
            hikeReservation.setPartyNumber(partyNumber);
        }

        if (!hikeReservation.isValid()) {
            msg = "<div style=\"color: red;\"><strong>Invalid form values. You may be missing fields or have incorrect values.</strong></div>";
            statusCode = 400;
        } else {
            try {
                Rates rates = new Rates(Rates.HIKE.valueOf(hikeName));
            } catch (Exception e) {
                msg = "<div style=\"color: red;\"><strong>Invalid hike name.</strong></div>";
                return Response.status(400).entity(msg).build();
            }

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
                msg = "<div style=\"color: red;\"><strong>Invalid date format. Error of: " +
                        ex.getMessage() +
                        "</strong></div>";
                statusCode = 400;
            }

            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int day = cal.get(Calendar.DAY_OF_MONTH);

            // set the book day start; add one to the month to compensate for the BookingDay offset
            BookingDay startDay = new BookingDay(year, month, day);

            if (!startDay.isValidDate()) {
                msg = "<div style=\"color: red;\"><strong>Invalid time range. Details: " +
                        rates.getDetails() +
                        "</strong></div>";
                statusCode = 400;
            } else {
                // add selected duration days to the start date
                rates.setBeginDate(startDay);

                // validate the range is valid
                boolean success = rates.setDuration(selectedDuration);
                if (rates.isValidDates() && success) {
                    // format cost as USD
                    NumberFormat formatter = NumberFormat.getCurrencyInstance();

                    // print out final total
                    msg = "<div style=\"color: green;\"><strong>Quoted Rate: " +
                            formatter.format(rates.getCost()) +
                            "</strong></div>";
                    statusCode = 200;
                } else {
                    msg = "<div style=\"color: red;\"><strong>Invalid time range. Details: " +
                            rates.getDetails() +
                            "</strong></div>";
                    statusCode = 400;
                }
            }
        }

        // return message to user
        return Response.status(statusCode).entity(msg).build();
    }
}
