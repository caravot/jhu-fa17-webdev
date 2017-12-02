package com.cravott1;

import com.bookingrate.BookingDay;
import com.bookingrate.Rates;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Path("/rsvp")
public class ravotta_hw13 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    }

    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReservation(HikeReservation hr) {
        String message = "";
        ResponseData responseData = new ResponseData();
        responseData.setHikeReservation(hr);

        // ensure valid json input
        if (hr == null || !hr.isValid()) {
            message = "Please submit all reservation fields";
        } else {
            message = getCost(hr);
        }

        // set response message
        responseData.setMessage(message);

        return Response.status(200).entity(responseData).build();
    }

    /**
     * Calculate total cost of hike
     */
    public String getCost(HikeReservation hr) {
        // message to return to user
        String msg = "";

        // get instance to use for parsing the start date
        Calendar cal = Calendar.getInstance();

        try {
            Rates rates = new Rates(Rates.HIKE.valueOf(hr.getHike()));
        } catch (Exception e) {
            return "Invalid hike name.";
        }

        Rates rates = new Rates(Rates.HIKE.valueOf(hr.getHike()));

        // get what duration is selected
        Integer selectedDuration = hr.getDuration();

        // try to parse the user's start date entered
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            cal.setTime(sdf.parse(hr.getDate()));
        }
        // invalid date format
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            msg = "Invalid date format. Error of: " + ex.getMessage();
        }

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        // set the book day start; add one to the month to compensate for the BookingDay offset
        BookingDay startDay = new BookingDay(year, month, day);

        if (!startDay.isValidDate()) {
            msg = "Invalid start date. Details: " + rates.getDetails();
        } else {
            // add selected duration days to the start date
            rates.setBeginDate(startDay);

            // validate the range is valid
            boolean success = rates.setDuration(selectedDuration);
            if (rates.isValidDates() && success) {
                // format cost as USD
                NumberFormat formatter = NumberFormat.getCurrencyInstance();

                // print out final total
                msg = "Quoted Rate: " + formatter.format(rates.getCost() * hr.getParty());
            } else {
                msg = "Invalid time range. Details: " + rates.getDetails();
            }
        }

        return msg;
    }
}
