/*
 * ClientThread.java
 * 
 * Created on Nov 4, 2007, 2:11:49 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cravott1;

import com.bookingrate.BookingDay;
import com.bookingrate.Rates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

public class ClientThread extends Thread {
    private final Socket socket;

    public ClientThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(20004);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 20004.");
            System.exit(1);
        }

        Socket clientSocket = null;
        while (true) {
            clientSocket = serverSocket.accept();
            ClientThread thread = new ClientThread(clientSocket);
            thread.start();
        }
    }

    public void run () {
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String outputLine = null;

            while (!socket.isClosed()) {
                outputLine = in.readLine();

                if (outputLine == null) {
                    break;
                } else {
                    // print results from cost calculation to user
                    out.println(getCost(outputLine));
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /** Calculate total cost of hike
     */
    private String getCost(String input) {

        //begin_year:begin_month:begin_day:hike:duration
        String parts[] = input.split(":");

        // missing arguments
        if (parts.length != 5) {
            return "-0.01:Invalid number of arguments";
        }

        // ensure all input parts are integers
        for (int i = 0; i < 5; i++) {
            Integer tmp = 0;

            try {
                tmp = Integer.parseInt(parts[i]);
            } catch (Exception ex) {
                return "-0.01:Invalid argument. Error of: " + ex.getMessage();
            }

            // do not except negative values
            if (tmp < 0) {
                return "-0.01:Invalid argument. All inputs must be positive integers.";
            }
        }

        // init variables
        Integer begin_year = Integer.parseInt(parts[0]);
        Integer begin_month = Integer.parseInt(parts[1]);
        Integer begin_day = Integer.parseInt(parts[2]);
        Integer hike = Integer.parseInt(parts[3]);
        Integer duration = Integer.parseInt(parts[4]);

        // get instance to use for parsing the start date
        Calendar cal = Calendar.getInstance();

        Rates rates = new Rates(Rates.HIKE.values()[hike]);

        // try to parse the user's start date entered
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            cal.setTime(sdf.parse(begin_month + "/" + begin_day + "/" + begin_year));
        }
        // invalid date format
        catch (Exception ex) {
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
            boolean success = rates.setDuration(duration);
            if (rates.isValidDates() && success) {
                // print out final total
                return rates.getCost() + ":Quoted Rate";
            } else {
                return "-0.01:Invalid time range. " + rates.getDetails();
            }
        }
    }
}
